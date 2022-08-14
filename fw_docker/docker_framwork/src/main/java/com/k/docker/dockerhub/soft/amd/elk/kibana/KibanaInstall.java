package com.k.docker.dockerhub.soft.amd.elk.kibana;

import com.k.docker.dockerhub.base.back.DockerhubCallBack;

import java.util.List;

public class KibanaInstall extends DockerhubCallBack {
    @Override
    public void writeDockerLines(List<String> lines, List<String> hosts) {
        String host = "kibana";
        lines.add("docker run -h=" + host + "  --name=" + host + " -p 5601:5601 -d registry.cn-hangzhou.aliyuncs.com/king019/elk:kibana");
    }
}
