package com.k.docker.dockerhub.soft.amd.apache.mq.rocketmq;

import com.k.docker.docker.util.props.PropsDockerUtil;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;

import java.util.List;

public class RocketmqInstall extends DockerhubCallBack {
    @Override
    public void writeDockerLines(List<String> lines, List<String> hosts) {
        //https://hub.docker.com/r/sonatype/nexus/
        lines.add("http://maven.aliyun.com/nexus/content/groups/public/");
        lines.add("mkdir -p /opt/soft/docker/data/nexus");
        lines.add("chmod -R 777 /opt/soft/docker/data");
        lines.add("docker start nexus");
        lines.add("docker run --name rocketmq -d -p 8080:80 -p 9876:9876 -p 10911:10911 -p 10909:10909 -v " + PropsDockerUtil.dockerAbsDataDir + "maven:/root/.m2/repository   king019/rocketmq");
    }
}
