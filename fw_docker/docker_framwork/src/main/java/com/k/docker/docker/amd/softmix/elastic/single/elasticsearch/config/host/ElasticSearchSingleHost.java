package com.k.docker.docker.amd.softmix.elastic.single.elasticsearch.config.host;

import com.google.common.collect.Lists;
import com.k.docker.docker.base.docker.wlan.base.DockerHostBase;

import java.util.List;

public class ElasticSearchSingleHost extends DockerHostBase {
    @Override
    public List<String> docker_all() {
        List<String> hosts = Lists.newArrayList();
        hosts.addAll(elk_e());
        hosts.addAll(elk_l());
        hosts.addAll(elk_k());
        trans(hosts);
        return hosts;
    }

    public List<String> elk_e() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("elk.e.1");
        trans(hosts);
        return hosts;
    }

    public List<String> elk_l() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("elk.l.1");
        trans(hosts);
        return hosts;
    }

    public List<String> elk_k() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("elk.k.1");
        trans(hosts);
        return hosts;
    }
}
