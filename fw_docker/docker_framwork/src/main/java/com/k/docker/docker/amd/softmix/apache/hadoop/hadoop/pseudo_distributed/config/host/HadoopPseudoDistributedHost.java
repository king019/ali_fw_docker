package com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.pseudo_distributed.config.host;

import com.google.common.collect.Lists;
import com.k.docker.docker.base.docker.wlan.base.DockerHostBase;

import java.util.List;

public class HadoopPseudoDistributedHost extends DockerHostBase {
    @Override
    public List<String> docker_all() {
        List<String> hosts = Lists.newArrayList();
        hosts.addAll(hadoop_single());
        return hosts;
    }

    public List<String> hadoop_single() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("hp.hp.pd.1");
        trans(hosts);
        return hosts;
    }
}
