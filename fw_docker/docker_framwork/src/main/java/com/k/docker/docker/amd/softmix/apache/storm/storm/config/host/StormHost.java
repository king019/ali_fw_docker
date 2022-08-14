package com.k.docker.docker.amd.softmix.apache.storm.storm.config.host;

import com.google.common.collect.Lists;
import com.k.docker.docker.base.docker.wlan.base.DockerHostBase;

import java.util.List;

public class StormHost extends DockerHostBase {
    @Override
    public List<String> docker_all() {
        List<String> hosts = Lists.newArrayList();
        hosts.addAll(storm_zookeeper());
        hosts.addAll(storm_nimbus());
        hosts.addAll(storm_supervisor());
        hosts.addAll(storm_ui());
        return hosts;
    }

    public List<String> storm_zookeeper() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("storm.zookeeper.1");
        hosts.add("storm.zookeeper.2");
        hosts.add("storm.zookeeper.3");
        return hosts;
    }

    public List<String> storm_nimbus() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("storm.storm.nimbus.1");
        hosts.add("storm.storm.nimbus.2");
        hosts.add("storm.storm.nimbus.3");
        return hosts;
    }

    public List<String> storm_supervisor() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("storm.storm.supervisor.1");
        hosts.add("storm.storm.supervisor.2");
        hosts.add("storm.storm.supervisor.3");
        return hosts;
    }

    public List<String> storm_ui() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("storm.storm.ui.1");
        hosts.add("storm.storm.ui.2");
        hosts.add("storm.storm.ui.3");
        return hosts;
    }
}
