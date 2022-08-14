package com.k.docker.docker.amd.softmix.cache.redis.cluster.redis.config.host;

import com.google.common.collect.Lists;
import com.k.docker.docker.base.docker.wlan.base.DockerHostBase;

import java.util.List;

public class RedisClusterHost extends DockerHostBase {
    @Override
    public List<String> docker_all() {
        List<String> hosts = Lists.newArrayList();
        hosts.addAll(dredis_cluster());
        trans(hosts);
        return hosts;
    }

    public List<String> dredis_cluster() {
        List<String> hosts = Lists.newArrayList();
        for (int port = 7001; port < 7017; port++) {
            hosts.add("rd.cu." + port);
        }
        trans(hosts);
        return hosts;
    }
}
