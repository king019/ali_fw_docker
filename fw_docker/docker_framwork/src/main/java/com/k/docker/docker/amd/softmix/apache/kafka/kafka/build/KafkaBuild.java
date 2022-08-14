package com.k.docker.docker.amd.softmix.apache.kafka.kafka.build;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.apache.kafka.cluster.install.KafkaInstall;
import com.k.docker.docker.amd.soft.jdk.JdkInstall;
import com.k.docker.docker.amd.softmix.apache.kafka.kafka.config.host.KafkaHost;
import com.k.docker.docker.base.soft.base.DockerBase;
import com.k.docker.docker.base.softmix.adapter.SoftMixAdapter;

import java.util.List;

public class KafkaBuild extends SoftMixAdapter {
    @Override
    public List<DockerBase> queryDocks() {
        final KafkaHost dockerHost = new KafkaHost();
        this.dockerBases = Lists.newArrayList();
        this.dockerBases.add(new JdkInstall());
        this.dockerBases.add(new KafkaInstall(dockerHost.kafka_zookeeper()));
        return this.dockerBases;
    }
}
