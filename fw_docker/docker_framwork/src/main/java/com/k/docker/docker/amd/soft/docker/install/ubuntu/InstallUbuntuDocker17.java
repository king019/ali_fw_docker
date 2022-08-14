package com.k.docker.docker.amd.soft.docker.install.ubuntu;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.docker.install.base.InstallFile;
import com.k.docker.docker.util.file.FileWriteUtil;
import com.k.docker.docker.util.path.FWPathUtil;
import org.junit.Test;

import java.util.List;

public class InstallUbuntuDocker17 extends InstallFile {

    @Override
    public void bf() {
        shPath = FWPathUtil.getTargetPath(InstallUbuntuDocker17.class) + "/docker17.sh";
        shFile = FileWriteUtil.getFile(shPath);
    }

    @Test
    public void test() throws Exception {
        String host = "https://download.docker.com";
        host = " https://mirrors.aliyun.com/docker-ce";
        FileWriteUtil.writeStart(shFile);
        List<String> lines = Lists.newArrayList();
        lines.add("apt-get remove docker docker-engine docker.io");
        lines.add("if 14");
        lines.add("apt-get update");
        lines.add("apt-get install  linux-image-extra-$(uname -r)  linux-image-extra-virtual");
        lines.add("end 14");
        lines.add("apt-get update");
        lines.add("apt-get install  apt-transport-https  ca-certificates  curl  software-properties-common");
        lines.add("curl -fsSL " + host + "/linux/ubuntu/gpg | sudo apt-key add -");
        lines.add("apt-key fingerprint 0EBFCD88");
        lines.add("add-apt-repository  \"deb [arch=amd64] " + host + "/linux/ubuntu  $(lsb_release -cs)  stable\"");
        lines.add("apt-get update");
        lines.add("apt-get -y install docker-ce");
        lines.add("#config docker");
        lines.add("echo \"DOCKER_OPTS='$DOCKER_OPTS --registry-mirror=https://msb3wa7d.mirror.aliyuncs.com  --dns 172.17.0.1'\" | sudo tee -a /etc/default/docker");
        lines.add("sudo service docker restart");
        FileWriteUtil.write(shFile, lines);
    }
}
