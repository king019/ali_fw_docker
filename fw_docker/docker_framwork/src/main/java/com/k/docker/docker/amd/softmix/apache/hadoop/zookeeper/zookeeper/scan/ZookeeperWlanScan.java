package com.k.docker.docker.amd.softmix.apache.hadoop.zookeeper.zookeeper.scan;

import com.k.docker.docker.amd.softmix.apache.hadoop.zookeeper.zookeeper.config.host.ZookeeperHost;
import com.k.docker.docker.base.softmix.exec.DockerExecBase;

import java.util.Map;

public class ZookeeperWlanScan extends DockerExecBase {
    @Override
    public Map<String, String> hostIps() {
        ZookeeperHost dockerHost = new ZookeeperHost();
        return dockerHost.docker_host_map();
    }
}
