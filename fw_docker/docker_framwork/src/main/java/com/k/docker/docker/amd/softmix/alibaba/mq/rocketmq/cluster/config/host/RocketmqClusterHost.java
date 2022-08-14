package com.k.docker.docker.amd.softmix.alibaba.mq.rocketmq.cluster.config.host;

import com.google.common.collect.Lists;
import com.k.docker.docker.base.docker.wlan.base.DockerHostBase;

import java.util.List;

public class RocketmqClusterHost extends DockerHostBase {
    @Override
    public List<String> docker_all() {
        List<String> hosts = Lists.newArrayList();
        hosts.addAll(rocketmq_nameServer());
        hosts.addAll(rocketmq_master());
        hosts.addAll(rocketmq_slave());
        hosts.addAll(rocketmq_web());
        trans(hosts);
        return hosts;
    }

    public List<String> rocketmq_nameServer() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("rk.ns.1");
        hosts.add("rk.ns.2");
        // hosts.add("rk.ns.3");
        trans(hosts);
        return hosts;
    }

    public List<String> rocketmq_master() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("rk.ms.1");
        hosts.add("rk.ms.2");
        hosts.add("rk.ms.3");
        trans(hosts);
        return hosts;
    }

    public List<String> rocketmq_slave() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("rk.sv.1");
        hosts.add("rk.sv.2");
        hosts.add("rk.sv.3");
        trans(hosts);
        return hosts;
    }

    public List<String> rocketmq_web() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("rk.wb.1");
        trans(hosts);
        return hosts;
    }
}
