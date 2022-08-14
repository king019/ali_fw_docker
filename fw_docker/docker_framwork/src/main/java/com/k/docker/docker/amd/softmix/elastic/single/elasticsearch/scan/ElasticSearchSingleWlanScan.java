package com.k.docker.docker.amd.softmix.elastic.single.elasticsearch.scan;

import com.k.docker.docker.amd.softmix.elastic.single.elasticsearch.config.host.ElasticSearchSingleHost;
import com.k.docker.docker.base.softmix.exec.DockerExecBase;

import java.util.Map;

public class ElasticSearchSingleWlanScan extends DockerExecBase {
    @Override
    public Map<String, String> hostIps() {
        ElasticSearchSingleHost dockerHost = new ElasticSearchSingleHost();
        return dockerHost.docker_host_map();
    }
}
