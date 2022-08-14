package com.k.docker.docker.amd.softmix.cache.middleware.codis.single.scan;

import com.k.docker.docker.amd.softmix.cache.redis.cluster.redis.config.host.RedisClusterHost;
import com.k.docker.docker.base.softmix.exec.DockerExecBase;

import java.util.Map;

public class CodisSingleScan extends DockerExecBase {
    @Override
    public Map<String, String> hostIps() {
        RedisClusterHost dockerHost = new RedisClusterHost();
        return dockerHost.docker_host_map();
    }
}
