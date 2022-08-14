package com.k.docker.docker.amd.softmix.rabbitmq.cluster.scan;

import com.k.docker.docker.amd.softmix.rabbitmq.cluster.config.host.RabbitmqClusterHost;
import com.k.docker.docker.base.softmix.exec.DockerExecBase;
import com.k.docker.docker.util.docker.DockerVersion;

import java.util.Map;

public class RabbitmqClusterWlanScan extends DockerExecBase {
    @Override
    public Map<String, String> hostIps() {
        RabbitmqClusterHost dockerHost = new RabbitmqClusterHost();
        return dockerHost.docker_host_map();
    }

    @Override
    public void setDockerVersion() {
        dockerVersion = DockerVersion.rabbitmqVersion;
    }
}
