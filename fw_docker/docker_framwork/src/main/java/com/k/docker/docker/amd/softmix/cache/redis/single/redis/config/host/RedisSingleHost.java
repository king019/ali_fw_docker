package com.k.docker.docker.amd.softmix.cache.redis.single.redis.config.host;

import com.google.common.collect.Lists;
import com.k.docker.docker.base.docker.wlan.base.DockerHostBase;

import java.util.List;

public class RedisSingleHost extends DockerHostBase {
    @Override
    public List<String> docker_all() {
        List<String> hosts = Lists.newArrayList();
        hosts.addAll(dredis_single());
        trans(hosts);
        return hosts;
    }

    public List<String> dredis_single() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("rd.sg.1");
        //hosts.add("rd.sg.2");
        //hosts.add("rd.sg.3");
        trans(hosts);
        return hosts;
    }
}
