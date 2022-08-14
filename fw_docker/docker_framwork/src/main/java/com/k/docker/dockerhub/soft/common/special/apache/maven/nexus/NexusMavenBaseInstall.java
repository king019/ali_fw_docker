package com.k.docker.dockerhub.soft.common.special.apache.maven.nexus;

import com.k.docker.dockerhub.base.back.DockerhubCallBack;

import java.util.List;

public class NexusMavenBaseInstall extends DockerhubCallBack {
    @Override
    public void writeDockerLines(List<String> lines, List<String> hosts) {
        //https://hub.docker.com/r/sonatype/nexus/
        lines.add("http://maven.aliyun.com/nexus/content/groups/public/");
        lines.add("https://maven.aliyun.com/repository/public");
        lines.add("http://maven.aliyun.com/repository/public");
        lines.add("https://mirrors.huaweicloud.com/repository/maven/");
        lines.add("http://mirrors.huaweicloud.com/repository/maven/");
        lines.add("http://repo.huaweicloud.com/repository/maven/");
        lines.add("http://maven.oschina.net/content/groups/public/");
        lines.add("mkdir -p /opt/soft/docker/data/nexus");
        lines.add("chmod -R 777 /opt/soft/docker/data");
    }
}
