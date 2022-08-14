package com.k.docker.dockerhub.soft.amd.proxy.littleproxy.littleproxy;

import com.k.docker.dockerhub.base.back.DockerhubCallBack;
import org.junit.Before;

import java.util.List;

public class LittleProxyDockerInstall extends DockerhubCallBack {
    String host = "littleproxy";
    String dockerVersion = "registry.cn-hangzhou.aliyuncs.com/king019/littleproxy";

    @Before
    public void bf1() {
        hosts.add(host);
    }

    @Override
    public void writeDockerLines(List<String> lines, List<String> hosts) {
        lines.add("docker run -h=" + host + "  --name=" + host + "  -d " + dockerVersion);
    }
}
