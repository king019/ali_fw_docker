package com.k.docker.docker.amd.softmix.apache.kafka.kafka.config.host;

import com.google.common.collect.Lists;
import com.k.docker.docker.base.docker.wlan.base.DockerHostBase;

import java.util.List;

public class KafkaHost extends DockerHostBase {
    @Override
    public List<String> docker_all() {
        final List<String> hosts = Lists.newArrayList();
        hosts.addAll(kafka_zookeeper());
        hosts.addAll(kafka_kafka());
        return hosts;
    }

    public List<String> kafka_zookeeper() {
        final List<String> hosts = Lists.newArrayList();
        hosts.add("zk.1");
        hosts.add("zk.2");
        hosts.add("zk.3");
        trans(hosts);
        return hosts;
    }

    public List<String> kafka_kafka() {
        final List<String> hosts = Lists.newArrayList();
        hosts.add("ka.ka.1");
        hosts.add("ka.ka.2");
        hosts.add("ka.ka.3");
        trans(hosts);
        return hosts;
    }
}
