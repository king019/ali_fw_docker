package com.k.docker.docker.amd.softmix.proxy.squid.single.config.host;

import com.google.common.collect.Lists;
import com.k.docker.docker.base.docker.wlan.base.DockerHostBase;

import java.util.List;

public class SquidHost extends DockerHostBase {
    @Override
    public List<String> docker_all() {
        List<String> hosts = Lists.newArrayList();
        hosts.addAll(squid_single());
        trans(hosts);
        return hosts;
    }

    public List<String> squid_single() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("sq.sg.1");
        hosts.add("myotherproxy");
        trans(hosts);
        return hosts;
    }
}
