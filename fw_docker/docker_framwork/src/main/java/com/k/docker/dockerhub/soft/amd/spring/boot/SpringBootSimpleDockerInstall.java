package com.k.docker.dockerhub.soft.amd.spring.boot;

import com.k.docker.dockerhub.base.back.DockerhubCallBack;
import org.junit.Before;

import java.util.List;

public class SpringBootSimpleDockerInstall extends DockerhubCallBack {
    String host = "springboot";
    String dockerVersion = "registry.cn-hangzhou.aliyuncs.com/king019/spring_boot_simple";

    @Before
    public void bf1() {
        hosts.add(host);
    }

    @Override
    public void writeDockerLines(List<String> lines, List<String> hosts) {
        lines.add("docker run -e TZ='Asia/Shanghai' -p 8080:9999 -h=" + host + "  --name=" + host + "  -d " + dockerVersion + "  /docker.sh ");
    }
}
