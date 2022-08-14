package com.k.docker.docker.amd.softmix.cache.redis.single.redis.run;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.cache.redis.single.install.RedisSingleInstall;
import com.k.docker.docker.amd.softmix.cache.redis.single.redis.build.RedisSingleBuild;
import com.k.docker.docker.amd.softmix.cache.redis.single.redis.config.host.RedisSingleHost;
import com.k.docker.docker.amd.softmix.cache.redis.single.redis.image.create.RedisSingleImageCreate;
import com.k.docker.docker.base.docker.run.DockerRunFileBase;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.docker.run.base.impl.DockerRunBaseImpl;
import com.k.docker.docker.util.docker.DockerVersion;
import com.k.docker.docker.util.file.FileWriteUtil;
import org.junit.Test;

import java.util.List;

public class RedisSingleRun extends DockerRunFileBase {

    @Test
    public void test() throws Exception {
        String redisVersion = DockerVersion.redisSingleVersion;
        DockerRunBase dockerRun = new DockerRunBaseImpl();
        RedisSingleHost hostConfig = new RedisSingleHost();
        List<String> lines = Lists.newArrayList();
        String param = null;
        {
            FileWriteUtil.writeCdClassandCopySoft(lines, RedisSingleBuild.class);
        }
        {
            FileWriteUtil.writeDnsAndImage(lines, RedisSingleImageCreate.class);
        }
        RedisSingleInstall.dockerRun(RedisSingleBuild.class, lines, redisVersion, dockerRun, hostConfig, param);
        FileWriteUtil.write(shFile, lines);
    }
}
