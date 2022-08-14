package com.k.docker.docker.amd.softmix.rabbitmq.single.scan;

import com.k.docker.docker.amd.softmix.rabbitmq.single.config.host.RabbitmqHost;
import com.k.docker.docker.base.softmix.exec.DockerExecBase;
import com.k.docker.docker.util.docker.DockerVersion;

import java.util.Map;

public class RabbitmqWlanScan extends DockerExecBase {
    @Override
    public Map<String, String> hostIps() {
        RabbitmqHost dockerHost = new RabbitmqHost();
        return dockerHost.docker_host_map();
    }

    @Override
    public void setDockerVersion() {
        dockerVersion = DockerVersion.rabbitmqVersion;
    }
}
