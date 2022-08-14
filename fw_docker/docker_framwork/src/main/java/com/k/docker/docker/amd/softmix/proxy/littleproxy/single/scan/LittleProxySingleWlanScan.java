package com.k.docker.docker.amd.softmix.proxy.littleproxy.single.scan;

import com.k.docker.docker.amd.softmix.proxy.littleproxy.single.config.host.LittleProxyHost;
import com.k.docker.docker.base.softmix.exec.DockerExecBase;

import java.util.Map;

public class LittleProxySingleWlanScan extends DockerExecBase {
    @Override
    public Map<String, String> hostIps() {
        LittleProxyHost dockerHost = new LittleProxyHost();
        return dockerHost.docker_host_map();
    }
}
