package com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.distributed.scan;

import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.distributed.config.host.HadoopDistributedHost;
import com.k.docker.docker.base.softmix.exec.DockerExecBase;

import java.util.Map;

public class HadoopDistributedWlanScan extends DockerExecBase {
    @Override
    public Map<String, String> hostIps() {
        HadoopDistributedHost dockerHost = new HadoopDistributedHost();
        return dockerHost.docker_host_map();
    }
}
