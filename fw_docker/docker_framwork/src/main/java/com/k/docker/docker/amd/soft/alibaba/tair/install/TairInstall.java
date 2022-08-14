package com.k.docker.docker.amd.soft.alibaba.tair.install;

import com.google.common.collect.Lists;
import com.k.docker.docker.base.soft.base.impl.DockerBaseImpl;
import com.k.docker.docker.util.props.PropsDockerUtil;

import java.util.List;

public class TairInstall extends DockerBaseImpl {
    public TairInstall() {
        // super(tarc, unpressc, mvDirc);
    }

    @Override
    public List<String> dockerSpecialYum() {
        List<String> yums = Lists.newArrayList();
        yums.add(" automake make gcc-c++ boost-devel git");
        return yums;
    }

    @Override
    public List<String> dockerSpecialRuns() {
        List<String> lines = Lists.newArrayList();
        lines.add("mkdir -p " + PropsDockerUtil.dockerDir);
        lines.add("cd " + PropsDockerUtil.dockerDir);
        lines.add("git clone https://code.aliyun.com/king019/ali_fw_three.git " + PropsDockerUtil.dockerDir + "ali_fw_three");
        lines.add("cp -R " + PropsDockerUtil.dockerDir + "ali_fw_three/aliyun/tair " + PropsDockerUtil.dockerDir + "tair");
        lines.add("cp -R " + PropsDockerUtil.dockerDir + "ali_fw_three/aliyun/tb-common-utils " + PropsDockerUtil.dockerDir + "tb-common-utils");
        // lines.add("svn checkout http:// code.taobao.org/svn/tb-common-utils/trunk/ tb-common-utils");
        // lines.add("svn checkout http:// code.taobao.org/svn/tair/trunk/ tair");
        return lines;
    }
}
