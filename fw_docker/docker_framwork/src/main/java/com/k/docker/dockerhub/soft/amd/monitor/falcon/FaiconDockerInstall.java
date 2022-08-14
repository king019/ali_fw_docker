package com.k.docker.dockerhub.soft.amd.monitor.falcon;

import com.k.docker.docker.util.props.PropsDockerUtil;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;
import org.junit.Before;

import java.util.List;

public class FaiconDockerInstall extends DockerhubCallBack {
    @Before
    public void bf1() {
        hosts.add("kfalcon");
    }

    @Override
    public void writeDockerLines(List<String> lines, List<String> hosts) {
        //用户名密码都是root
        String host = "kfalcon";

        String monitor = "registry.cn-hangzhou.aliyuncs.com/king019/falcon-plus";
        //monitor="openfalcon/falcon-plus:0.2.0";

        //   docker run -h=falcon  --name=falcon  -itd -p 8081:8081 -p 1988:1988 openfalcon/falcon-plus:0.2.0 bash /run.sh hbs
        //密码 root  root
        lines.add("docker run -m 500m -h=" + host + "  --name=" + host + "  -itd -p 8082:8081 -p 1988:1988 -v " + PropsDockerUtil.dockerAbsDataDir + host + "/var/lib/mysql/:/var/lib/mysql/ " + monitor + " bash /run.sh hbs");
    }
}
