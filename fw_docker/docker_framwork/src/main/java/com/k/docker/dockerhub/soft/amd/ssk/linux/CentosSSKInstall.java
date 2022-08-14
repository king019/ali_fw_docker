package com.k.docker.dockerhub.soft.amd.ssk.linux;

import com.k.docker.dockerhub.base.back.DockerhubCallBack;
import org.junit.Before;

import java.util.List;

public class CentosSSKInstall extends DockerhubCallBack {
    @Before
    public void bf1() {
        hosts.add("hk1010");
        hosts.add("jp1015");
        hosts.add("us1002");
        hosts.add("us2004");
        hosts.add("hk3002");
    }

    @Override
    public void writeDockerLines(List<String> lines, List<String> hosts) {
        String version = "registry.cn-hangzhou.aliyuncs.com/king019/ssk";
        //sslocal -s $1 -p $2 -k $3 -m $4
        for (String ssk : hosts) {
            lines.add("docker run -v /opt/soft/config/ssk:/opt/soft/config -p 1080:1080 -h=" + ssk + "  --name=" + ssk + "  -d " + version + "  /docker.sh " + ssk);
        }
    }
}
