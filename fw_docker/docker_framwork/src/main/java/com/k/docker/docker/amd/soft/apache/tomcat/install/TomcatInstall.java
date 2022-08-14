package com.k.docker.docker.amd.soft.apache.tomcat.install;

import com.google.common.collect.Lists;
import com.k.docker.docker.base.soft.base.impl.DockerBaseImpl;
import com.k.docker.docker.util.docker.SoftVersion;
import com.k.docker.docker.util.file.ShellWriteUtil;
import com.k.docker.docker.util.props.PropsDockerUtil;
import com.k.docker.docker.util.vo.DockerBuildVO;

import java.io.File;
import java.util.List;

public class TomcatInstall extends DockerBaseImpl {
    public TomcatInstall() {
        dockerBuildVOs.add(SoftVersion.tomcatSoft);
    }

    public TomcatInstall(boolean tarFlag, boolean mvFlag, boolean runFlag) {
        dockerBuildVOs.add(SoftVersion.tomcatSoft);
        this.tarFlag = tarFlag;
        this.mvFlag = mvFlag;
        this.runFlag = runFlag;
    }

    @Override
    public void dockerShell(File file) throws Exception {
        DockerBuildVO dockerSoft = queryTar("tomcat");
        if (tarFlag) {
            ShellWriteUtil.writeTar(file, dockerSoft.tar);
        }
        if (mvFlag) {
            ShellWriteUtil.writeMv(file, dockerSoft.unpress, dockerSoft.mvDir);
        }
        if (runFlag) {
            List<String> lines = Lists.newArrayList();
            lines.add(PropsDockerUtil.dockerDir + dockerSoft.mvDir + "/bin/shutdown.sh");
            lines.add(PropsDockerUtil.dockerDir + dockerSoft.mvDir + "/bin/startup.sh");
            ShellWriteUtil.writeListTail(file, lines);
        }
    }
}
