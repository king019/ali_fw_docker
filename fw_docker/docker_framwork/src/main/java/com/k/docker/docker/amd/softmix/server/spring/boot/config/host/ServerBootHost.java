package com.k.docker.docker.amd.softmix.server.spring.boot.config.host;

import com.google.common.collect.Lists;
import com.k.docker.docker.base.docker.wlan.base.DockerHostBase;

import java.util.List;

public class ServerBootHost extends DockerHostBase {
    @Override
    public List<String> docker_all() {
        List<String> hosts = Lists.newArrayList();
        hosts.addAll(show());
        trans(hosts);
        return hosts;
    }

    public List<String> show() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("show.1");
        trans(hosts);
        return hosts;
    }
}
