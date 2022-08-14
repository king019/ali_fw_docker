package com.k.docker.docker.amd.softmix.alibaba.jstorm.jstorm.config.host;

import com.google.common.collect.Lists;
import com.k.docker.docker.base.docker.wlan.base.DockerHostBase;

import java.util.List;

public class JstormHost extends DockerHostBase {
    @Override
    public List<String> docker_all() {
        List<String> hosts = Lists.newArrayList();
        hosts.addAll(jstorm_zookeeper());
        hosts.addAll(jstorm_nimbus());
        hosts.addAll(jstorm_supervisor());
        hosts.addAll(jstorm_ui());
        trans(hosts);
        return hosts;
    }

    public List<String> jstorm_zookeeper() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("zk.1");
        hosts.add("zk.2");
        hosts.add("zk.3");
        trans(hosts);
        return hosts;
    }

    public List<String> jstorm_nimbus() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("jm.nm.1");
        hosts.add("jm.nm.2");
        // hosts.add("jm.nm.3");
        trans(hosts);
        return hosts;
    }

    public List<String> jstorm_supervisor() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("jm.sv.1");
        hosts.add("jm.sv.2");
        hosts.add("jm.sv.3");
        hosts.add("jm.sv.4");
        hosts.add("jm.sv.5");
        hosts.add("jm.sv.6");
        trans(hosts);
        return hosts;
    }

    public List<String> jstorm_ui() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("jm.ui.1");
        // hosts.add("jm.ui.2");
        // hosts.add("jm.ui.3");
        trans(hosts);
        return hosts;
    }
}
