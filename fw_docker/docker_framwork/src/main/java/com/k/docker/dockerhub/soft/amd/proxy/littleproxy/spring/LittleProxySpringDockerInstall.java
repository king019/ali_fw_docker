package com.k.docker.dockerhub.soft.amd.proxy.littleproxy.spring;

import com.k.docker.dockerhub.base.back.DockerhubCallBack;
import org.junit.Before;

import java.util.List;

public class LittleProxySpringDockerInstall extends DockerhubCallBack {
    private int num = 1;
    String host = "littleproxy";
    String dockerVersion = "registry.cn-hangzhou.aliyuncs.com/king019/littleproxyspring";

    @Before
    public void bf1() {
        for (int i = 0; i <= num; i++) {
            hosts.add(host + i);
        }
    }

    @Override
    public void writeDockerLines(List<String> lines, List<String> hosts) {
        for (int i = 0; i <= num; i++) {
            String localHost = host + i;
            String cmd = "littleproxy" + (i + 1);
            if (i == num) {
                cmd = "";
            }
            lines.add("docker run -e TZ='Asia/Shanghai' -h=" + localHost + "  --name=" + localHost + "  -d " + dockerVersion + "  /docker.sh " + cmd);
        }
    }
}
