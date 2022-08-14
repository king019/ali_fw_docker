package com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.distributed.config.host;

import com.google.common.collect.Lists;
import com.k.docker.docker.base.docker.wlan.base.DockerHostBase;

import java.util.List;

public class HadoopDistributedHost extends DockerHostBase {
    @Override
    public List<String> docker_all() {
        List<String> hosts = Lists.newArrayList();
        hosts.addAll(hadoop_master());
        hosts.addAll(hadoop_slave());
        return hosts;
    }

    public List<String> hadoop_master() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("master");
        trans(hosts);
        return hosts;
    }

    public List<String> hadoop_slave() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("slave1");
        hosts.add("slave2");
        trans(hosts);
        return hosts;
    }
}
