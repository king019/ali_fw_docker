package com.k.docker.docker.amd.softmix.apache.hadoop.hbase.pseudo_distributed.hbase.config.host;

import com.google.common.collect.Lists;
import com.k.docker.docker.base.docker.wlan.base.DockerHostBase;

import java.util.List;

public class HbasePseudoDistributedHost extends DockerHostBase {
    @Override
    public List<String> docker_all() {
        List<String> hosts = Lists.newArrayList();
        hosts.addAll(hbase_single());
        hosts.addAll(hbase_zookeeper());
        return hosts;
    }

    public List<String> hbase_single() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("hb.hb.sg.1");
        // hosts.add("hb.hb.sg.2");
        // hosts.add("hb.hb.sg.3");
        trans(hosts);
        return hosts;
    }

    public List<String> hbase_zookeeper() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("zk.1");
        hosts.add("zk.2");
        hosts.add("zk.3");
        trans(hosts);
        return hosts;
    }
}
