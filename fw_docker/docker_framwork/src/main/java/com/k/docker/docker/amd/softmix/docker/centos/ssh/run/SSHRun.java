package com.k.docker.docker.amd.softmix.docker.centos.ssh.run;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.softmix.docker.centos.ssh.build.CentosSSHBuild;
import com.k.docker.docker.base.docker.run.DockerRunFileBase;
import com.k.docker.docker.util.file.FileWriteUtil;
import com.k.docker.docker.util.path.FWPathUtil;
import org.junit.Test;

import java.util.List;

public class SSHRun extends DockerRunFileBase {

    @Test
    public void test() throws Exception {
        List<String> lines = Lists.newArrayList();
        String buildPath = FWPathUtil.getClassPath(CentosSSHBuild.class);
        lines.add("cat ~/.ssh/authorized_keys > " + buildPath + "/authorized_keys");
        lines.add("cat ~/.ssh/id_rsa > " + buildPath + "/id_rsa");
        lines.add("cat ~/.ssh/id_rsa.pub > " + buildPath + "/id_rsa.pub");
        FileWriteUtil.write(shFile, lines);
    }
}
