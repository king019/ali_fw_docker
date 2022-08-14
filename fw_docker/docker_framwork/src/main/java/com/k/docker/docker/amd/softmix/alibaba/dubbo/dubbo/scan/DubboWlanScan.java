package com.k.docker.docker.amd.softmix.alibaba.dubbo.dubbo.scan;

import com.k.docker.docker.amd.softmix.alibaba.dubbo.dubbo.config.host.DubboHost;
import com.k.docker.docker.base.softmix.exec.DockerExecBase;

import java.util.Map;

public class DubboWlanScan extends DockerExecBase {
    @Override
    public Map<String, String> hostIps() {
        DubboHost dockerHost = new DubboHost();
        return dockerHost.docker_host_map();
    }
}
