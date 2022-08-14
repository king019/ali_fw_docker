package com.k.docker.docker.amd.softmix.alibaba.rocketmq.rocketmq.scan;

import com.k.docker.docker.amd.softmix.alibaba.rocketmq.rocketmq.config.host.RocketmqHost;
import com.k.docker.docker.base.softmix.exec.DockerExecBase;

import java.util.Map;

public class RocketMqWlanScan extends DockerExecBase {
    @Override
    public Map<String, String> hostIps() {
        final RocketmqHost dockerHost = new RocketmqHost();
        return dockerHost.docker_host_map();
    }
}
