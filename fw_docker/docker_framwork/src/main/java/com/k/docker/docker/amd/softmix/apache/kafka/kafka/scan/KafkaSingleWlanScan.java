package com.k.docker.docker.amd.softmix.apache.kafka.kafka.scan;

import com.k.docker.docker.amd.softmix.apache.kafka.kafka.config.host.KafkaHost;
import com.k.docker.docker.base.softmix.exec.DockerExecBase;

import java.util.Map;

public class KafkaSingleWlanScan extends DockerExecBase {
    @Override
    public Map<String, String> hostIps() {
        final KafkaHost dockerHost = new KafkaHost();
        return dockerHost.docker_host_map();
    }
}
