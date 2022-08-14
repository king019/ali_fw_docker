package com.k.docker.dockerhub.soft.amd.elk.elasticsearch;

import com.k.docker.dockerhub.base.back.DockerhubCallBack;

import java.util.List;

public class Elasticsearch extends DockerhubCallBack {
    @Override
    public void writeDockerLines(List<String> lines, List<String> hosts) {
        String host = "elasticsearch";
        lines.add("docker run -h=" + host + "  --name=" + host + " -p 9200:9200 -p 9300:9300  -d registry.cn-hangzhou.aliyuncs.com/king019/elk:elasticsearch");
    }
}