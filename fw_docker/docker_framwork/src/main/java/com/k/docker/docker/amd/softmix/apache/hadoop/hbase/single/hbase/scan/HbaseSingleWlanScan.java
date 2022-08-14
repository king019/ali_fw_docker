package com.k.docker.docker.amd.softmix.apache.hadoop.hbase.single.hbase.scan;

import com.k.docker.docker.amd.softmix.apache.hadoop.hbase.single.hbase.config.host.HbaseSingleHost;
import com.k.docker.docker.base.softmix.exec.DockerExecBase;

import java.util.Map;

public class HbaseSingleWlanScan extends DockerExecBase {
    @Override
    public Map<String, String> hostIps() {
        HbaseSingleHost dockerHost = new HbaseSingleHost();
        return dockerHost.docker_host_map();
    }
}
