package com.k.docker.docker.amd.soft.apache.storm.install;

import com.google.common.collect.Lists;
import com.k.docker.docker.base.soft.base.impl.DockerBaseImpl;
import com.k.docker.docker.util.docker.SoftVersion;
import com.k.docker.docker.util.file.ShellWriteUtil;
import com.k.docker.docker.util.props.PropsDockerUtil;
import com.k.docker.docker.util.vo.DockerBuildVO;

import java.io.File;
import java.util.List;

public class StromInstall extends DockerBaseImpl {
    public StromInstall() {
        dockerBuildVOs.add(SoftVersion.stormSoft);
    }

    @Override
    public void dockerShell(File file) throws Exception {
        DockerBuildVO dockerSoft = queryTar("storm");
        ShellWriteUtil.writeTar(file, dockerSoft.tar);
        ShellWriteUtil.writeMv(file, dockerSoft.unpress, dockerSoft.mvDir);
        List<String> lines = Lists.newArrayList();
        lines.add("wk=$1");
        lines.add(PropsDockerUtil.dockerDir + dockerSoft.mvDir + "/bin/storm $wk &");
        ShellWriteUtil.writeListTail(file, lines);
    }
}
