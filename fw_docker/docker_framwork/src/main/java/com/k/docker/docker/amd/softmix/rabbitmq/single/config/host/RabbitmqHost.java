package com.k.docker.docker.amd.softmix.rabbitmq.single.config.host;

import com.google.common.collect.Lists;
import com.k.docker.docker.base.docker.wlan.base.DockerHostBase;

import java.util.List;

public class RabbitmqHost extends DockerHostBase {
    @Override
    public List<String> docker_all() {
        List<String> hosts = Lists.newArrayList();
        hosts.addAll(rabbitmq_host());
        trans(hosts);
        return hosts;
    }

    public List<String> rabbitmq_host() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("rb.mq.1");
        hosts.add("rb.mq.2");
        hosts.add("rb.mq.3");
        trans(hosts);
        return hosts;
    }
}
