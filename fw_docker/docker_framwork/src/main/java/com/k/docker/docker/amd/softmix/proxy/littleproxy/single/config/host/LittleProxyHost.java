package com.k.docker.docker.amd.softmix.proxy.littleproxy.single.config.host;

import com.google.common.collect.Lists;
import com.k.docker.docker.base.docker.wlan.base.DockerHostBase;

import java.util.List;

public class LittleProxyHost extends DockerHostBase {
    @Override
    public List<String> docker_all() {
        List<String> hosts = Lists.newArrayList();
        hosts.addAll(proxy_single());
        trans(hosts);
        return hosts;
    }

    public List<String> proxy_single() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("littleproxy");
        trans(hosts);
        return hosts;
    }
}
