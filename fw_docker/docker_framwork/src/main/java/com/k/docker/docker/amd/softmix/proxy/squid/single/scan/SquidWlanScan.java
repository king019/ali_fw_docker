package com.k.docker.docker.amd.softmix.proxy.squid.single.scan;

import com.k.docker.docker.amd.softmix.proxy.squid.single.config.host.SquidHost;
import com.k.docker.docker.base.softmix.exec.DockerExecBase;

import java.util.Map;

public class SquidWlanScan extends DockerExecBase {
    @Override
    public Map<String, String> hostIps() {
        SquidHost dockerHost = new SquidHost();
        return dockerHost.docker_host_map();
    }
}
