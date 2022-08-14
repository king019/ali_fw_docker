package com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.single.hadoop.scan;

import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.single.hadoop.config.host.HadoopSingleHost;
import com.k.docker.docker.base.softmix.exec.DockerExecBase;

import java.util.Map;

public class HadoopSingleWlanScan extends DockerExecBase {
    @Override
    public Map<String, String> hostIps() {
        HadoopSingleHost dockerHost = new HadoopSingleHost();
        return dockerHost.docker_host_map();
    }
}
