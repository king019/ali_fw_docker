package com.k.docker.docker.amd.soft.docker.install.ubuntu;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.docker.install.base.InstallFile;
import com.k.docker.docker.util.file.FileWriteUtil;
import com.k.docker.docker.util.path.FWPathUtil;
import org.junit.Test;

import java.util.List;

public class InstallUbuntuDocker extends InstallFile {

    @Override
    public void bf() {
        shPath = FWPathUtil.getTargetPath(InstallUbuntuDocker.class) + "/docker.sh";
        shFile = FileWriteUtil.getFile(shPath);
    }

    @Test
    public void test() throws Exception {
        FileWriteUtil.writeStart(shFile);
        List<String> lines = Lists.newArrayList();
        String install = "apt-get install  -y curl";
        lines.add(install);
        install = "curl -sSL http://acs-public-mirror.oss-cn-hangzhou.aliyuncs.com/docker-engine/internet | sh -";
        lines.add(install);
        String dockerops = "echo \"DOCKER_OPTS='$DOCKER_OPTS --registry-mirror=https://msb3wa7d.mirror.aliyuncs.com  --dns 172.17.0.1'\" | sudo tee -a /etc/default/docker";
        lines.add(dockerops);
        String dockerRestart = "sudo service docker restart";
        lines.add(dockerRestart);
        FileWriteUtil.write(shFile, lines);
    }
}
