package com.k.docker.dockerhub.soft.amd.elk.filebeat;

import com.k.docker.dockerhub.base.back.DockerhubCallBack;

import java.util.List;


public class FilebeatInstall extends DockerhubCallBack {
    @Override
    public void writeDockerLines(List<String> lines, List<String> hosts) {
        String host = "eilebeat";
        lines.add("docker run -h=" + host + "  --name=" + host + "  -d registry.cn-hangzhou.aliyuncs.com/king019/elk:filebeat");
    }
}
