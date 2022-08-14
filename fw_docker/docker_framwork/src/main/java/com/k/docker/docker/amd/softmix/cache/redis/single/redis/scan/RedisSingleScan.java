package com.k.docker.docker.amd.softmix.cache.redis.single.redis.scan;

import com.k.docker.docker.amd.softmix.cache.redis.single.redis.config.host.RedisSingleHost;
import com.k.docker.docker.base.softmix.exec.DockerExecBase;

import java.util.Map;

public class RedisSingleScan extends DockerExecBase {
    @Override
    public Map<String, String> hostIps() {
        RedisSingleHost dockerHost = new RedisSingleHost();
        return dockerHost.docker_host_map();
    }
}
