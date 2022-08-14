package com.k.docker.docker.amd.softmix.alibaba.mq.rocketmq.cluster.scan;

import com.k.docker.docker.amd.softmix.alibaba.mq.rocketmq.cluster.config.host.RocketmqClusterHost;
import com.k.docker.docker.base.softmix.exec.DockerExecBase;

import java.util.Map;

public class RocketMqClusterWlanScan extends DockerExecBase {
    @Override
    public Map<String, String> hostIps() {
        RocketmqClusterHost dockerHost = new RocketmqClusterHost();
        return dockerHost.docker_host_map();
    }
}
