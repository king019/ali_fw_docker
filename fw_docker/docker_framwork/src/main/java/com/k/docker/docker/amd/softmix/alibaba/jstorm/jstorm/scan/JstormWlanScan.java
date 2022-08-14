package com.k.docker.docker.amd.softmix.alibaba.jstorm.jstorm.scan;

import com.k.docker.docker.amd.softmix.alibaba.jstorm.jstorm.config.host.JstormHost;
import com.k.docker.docker.base.softmix.exec.DockerExecBase;

import java.util.Map;

public class JstormWlanScan extends DockerExecBase {
    @Override
    public Map<String, String> hostIps() {
        JstormHost dockerHost = new JstormHost();
        return dockerHost.docker_host_map();
    }
}
