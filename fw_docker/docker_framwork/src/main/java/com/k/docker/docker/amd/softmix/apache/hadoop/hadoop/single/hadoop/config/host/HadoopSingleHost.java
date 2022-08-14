package com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.single.hadoop.config.host;

import com.google.common.collect.Lists;
import com.k.docker.docker.base.docker.wlan.base.DockerHostBase;

import java.util.List;

public class HadoopSingleHost extends DockerHostBase {
    @Override
    public List<String> docker_all() {
        final List<String> hosts = Lists.newArrayList();
        hosts.addAll(hadoop_single());
        return hosts;
    }

    public List<String> hadoop_single() {
        final List<String> hosts = Lists.newArrayList();
        hosts.add("hp.hp.ms.1");
        trans(hosts);
        return hosts;
    }
}
