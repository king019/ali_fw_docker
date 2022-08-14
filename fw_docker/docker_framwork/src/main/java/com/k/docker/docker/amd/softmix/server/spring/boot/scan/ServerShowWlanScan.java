package com.k.docker.docker.amd.softmix.server.spring.boot.scan;

import com.k.docker.docker.amd.softmix.server.spring.boot.config.host.ServerBootHost;
import com.k.docker.docker.base.softmix.exec.DockerExecBase;

import java.util.Map;

public class ServerShowWlanScan extends DockerExecBase {
    @Override
    public Map<String, String> hostIps() {
        ServerBootHost dockerHost = new ServerBootHost();
        return dockerHost.docker_host_map();
    }
}
