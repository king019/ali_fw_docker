package com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.pseudo_distributed.scan;

import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.pseudo_distributed.config.host.HadoopPseudoDistributedHost;
import com.k.docker.docker.base.softmix.exec.DockerExecBase;

import java.util.Map;

public class HadoopPseudoDistributedWlanScan extends DockerExecBase {
    @Override
    public Map<String, String> hostIps() {
        HadoopPseudoDistributedHost dockerHost = new HadoopPseudoDistributedHost();
        return dockerHost.docker_host_map();
    }
}
