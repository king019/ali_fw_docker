package com.k.docker.docker.amd.softmix.apache.hadoop.hbase.pseudo_distributed.hbase.scan;

import com.k.docker.docker.amd.softmix.apache.hadoop.hbase.pseudo_distributed.hbase.config.host.HbasePseudoDistributedHost;
import com.k.docker.docker.base.softmix.exec.DockerExecBase;

import java.util.Map;

public class HbasePseudoDistributedWlanScan extends DockerExecBase {
    @Override
    public Map<String, String> hostIps() {
        HbasePseudoDistributedHost dockerHost = new HbasePseudoDistributedHost();
        return dockerHost.docker_host_map();
    }
}
