package com.k.docker.docker.amd.softmix.cache.middleware.codis.single.run;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.cache.middleware.codis.install.CodisSingleInstall;
import com.k.docker.docker.amd.soft.cache.redis.single.install.RedisSingleInstall;
import com.k.docker.docker.amd.softmix.cache.middleware.codis.single.build.CodisSingleBuild;
import com.k.docker.docker.amd.softmix.cache.middleware.codis.single.config.host.CodisSingleHost;
import com.k.docker.docker.amd.softmix.cache.redis.single.redis.build.RedisSingleBuild;
import com.k.docker.docker.base.docker.run.DockerRunFileBase;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.docker.run.base.impl.DockerRunBaseImpl;
import com.k.docker.docker.util.docker.DockerVersion;
import com.k.docker.docker.util.file.FileWriteUtil;
import org.junit.Test;

import java.util.List;

public class CodisSingleRun extends DockerRunFileBase {

    @Test
    public void test() throws Exception {
        String codisVersion = DockerVersion.codisSingleVersion;
        String redisVersion = DockerVersion.redisSingleVersion;
        CodisSingleHost hostConfig = new CodisSingleHost();
        DockerRunBase dockerRun = new DockerRunBaseImpl();
        List<String> lines = Lists.newArrayList();
        String param = null;
        {
            {
                FileWriteUtil.writeCdClassandCopySoft(lines, CodisSingleBuild.class, RedisSingleBuild.class);
            }
            {
                FileWriteUtil.writeDnsAndImage(lines, RedisSingleInstall.class);
            }
            RedisSingleInstall.dockerRun(RedisSingleBuild.class, lines, redisVersion, dockerRun, hostConfig.codis_redis(), param);
            CodisSingleInstall.dockerRun(CodisSingleBuild.class, lines, codisVersion, dockerRun, hostConfig.codis_single(), param);
        }
        FileWriteUtil.write(shFile, lines);
    }
}
