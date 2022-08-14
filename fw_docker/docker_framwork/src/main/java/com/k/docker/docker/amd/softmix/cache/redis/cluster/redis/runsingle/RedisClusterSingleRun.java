package com.k.docker.docker.amd.softmix.cache.redis.cluster.redis.runsingle;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.softmix.cache.redis.cluster.redis.build.RedisClusterBuild;
import com.k.docker.docker.amd.softmix.cache.redis.cluster.redis.config.host.RedisClusterHost;
import com.k.docker.docker.base.docker.run.DockerRunFileBase;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.docker.run.base.impl.DockerRunBaseImpl;
import com.k.docker.docker.util.file.FileWriteUtil;
import com.k.docker.docker.util.path.FWPathUtil;
import org.junit.Test;

import java.util.List;

public class RedisClusterSingleRun extends DockerRunFileBase {

    @Test
    public void test() throws Exception {
        String redisVersion = "redis:cluster";
        DockerRunBase dockerRun = new DockerRunBaseImpl();
        RedisClusterHost hostConfig = new RedisClusterHost();
        List<String> lines = Lists.newArrayList();
        Lists.newArrayList();
        hostConfig.docker_host_map();
        String param = null;
        List<String> hosts;
        lines.add("cd " + FWPathUtil.getClassPath(RedisClusterBuild.class));
        {
            hosts = hostConfig.dredis_cluster();
            int num = 7001;
            for (String host : hosts) {
                param = num + "";
                num++;
                dockerRun.dockerRun(lines, redisVersion, host, param);
            }
        }
        FileWriteUtil.write(shFile, lines);
    }
}
