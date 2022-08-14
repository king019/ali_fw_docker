package com.k.docker.docker.amd.soft.apache.maven.single.install;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.softmix.apache.maven.resource.MavenRes;
import com.k.docker.docker.base.soft.base.impl.DockerBaseImpl;
import com.k.docker.docker.util.file.ShellWriteUtil;
import com.k.docker.docker.util.path.FWPathUtil;
import com.k.docker.docker.util.vo.DockerBuildVO;

import java.io.File;
import java.util.List;

public class MavenInstall extends DockerBaseImpl {
    @Override
    public List<DockerBuildVO> dockerResFile() {
        List<String> files = FWPathUtil.getDirFilesPath(MavenRes.class);
        List<DockerBuildVO> dockerBuildVOs = Lists.newArrayList();
        DockerBuildVO vo;
        for (String fname : files) {
            vo = new DockerBuildVO(fname, fname, fname);
            dockerBuildVOs.add(vo);
        }
        return dockerBuildVOs;
    }

    @Override
    public List<String> dockerSpecialYum() {
        final List<String> yums = Lists.newArrayList();
        yums.add(" maven git");
        return yums;
    }

    @Override
    public void dockerShell(File file) throws Exception {
        List<String> lines = Lists.newArrayList();
        ShellWriteUtil.writeCdSoft(file);
        ShellWriteUtil.writeListTail(file, lines);
    }
}
