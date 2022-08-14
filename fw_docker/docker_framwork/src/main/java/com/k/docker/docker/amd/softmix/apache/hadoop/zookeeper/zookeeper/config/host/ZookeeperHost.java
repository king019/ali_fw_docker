package com.k.docker.docker.amd.softmix.apache.hadoop.zookeeper.zookeeper.config.host;

import com.google.common.collect.Lists;
import com.k.docker.docker.base.docker.wlan.base.DockerHostBase;

import java.util.List;

public class ZookeeperHost extends DockerHostBase {
    @Override
    public List<String> docker_all() {
        List<String> hosts = Lists.newArrayList();
        hosts.addAll(zookeeper_zookeeper());
        hosts.addAll(zookeeper_ui());
        return hosts;
    }

    public List<String> zookeeper_zookeeper() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("zk.1");
        hosts.add("zk.2");
        hosts.add("zk.3");
        // hosts.add("hp.zk.4");
        // hosts.add("hp.zk.5");
        trans(hosts);
        return hosts;
    }

    public List<String> zookeeper_ui() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("zkui.1");
        trans(hosts);
        return hosts;
    }
}
