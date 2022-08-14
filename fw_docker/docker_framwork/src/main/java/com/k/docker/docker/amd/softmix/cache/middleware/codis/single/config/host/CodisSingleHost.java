package com.k.docker.docker.amd.softmix.cache.middleware.codis.single.config.host;

import com.google.common.collect.Lists;
import com.k.docker.docker.base.docker.wlan.base.DockerHostBase;

import java.util.List;

public class CodisSingleHost extends DockerHostBase {
    @Override
    public List<String> docker_all() {
        List<String> hosts = Lists.newArrayList();
        hosts.addAll(codis_single());
        trans(hosts);
        return hosts;
    }

    public List<String> codis_single() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("cds.sg.1");
        trans(hosts);
        return hosts;
    }

    public List<String> codis_redis() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("cds.rd.1");
        hosts.add("cds.rd.2");
        hosts.add("cds.rd.3");
        trans(hosts);
        return hosts;
    }
}
