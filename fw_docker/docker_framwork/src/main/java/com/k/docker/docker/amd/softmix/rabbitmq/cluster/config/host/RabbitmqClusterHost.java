package com.k.docker.docker.amd.softmix.rabbitmq.cluster.config.host;

import com.google.common.collect.Lists;
import com.k.docker.docker.base.docker.wlan.base.DockerHostBase;

import java.util.List;

public class RabbitmqClusterHost extends DockerHostBase {
    @Override
    public List<String> docker_all() {
        final List<String> hosts = Lists.newArrayList();
        hosts.addAll(rabbitmq_host());
        trans(hosts);
        return hosts;
    }

    public List<String> rabbitmq_host() {
        final List<String> hosts = Lists.newArrayList();
        hosts.add("rb.cluster.1");
        hosts.add("rb.cluster.2");
        hosts.add("rb.cluster.3");
        // hosts.add("rb.cluster.4");
        // hosts.add("rb.cluster.5");
        // hosts.add("rb.cluster.6");
        // hosts.add("rb.cluster.7");
        // hosts.add("rb.cluster.8");
        // hosts.add("rb.cluster.9");
        // hosts.add("rb.cluster.10");
        // hosts.add("rb.cluster.11");
        // hosts.add("rb.cluster.12");
        // hosts.add("rb.cluster.13");
        // hosts.add("rb.cluster.14");
        // hosts.add("rb.cluster.15");
        // hosts.add("rb.cluster.16");
        // hosts.add("rb.cluster.17");
        // hosts.add("rb.cluster.18");
        // hosts.add("rb.cluster.19");
        // hosts.add("rb.cluster.20");
        trans(hosts);
        return hosts;
    }
}
