package com.k.docker.docker.amd.soft.proxy.squid.single.install;

import com.google.common.collect.Lists;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.soft.base.impl.DockerBaseImpl;
import com.k.docker.docker.util.file.ShellWriteUtil;
import com.k.docker.docker.util.path.FWPathUtil;

import java.io.File;
import java.util.List;

public class SquidSingleInstall extends DockerBaseImpl {
    public SquidSingleInstall() {
        dockerBuildVOs = Lists.newArrayList();
    }

    public static void dockerRun(Class clazz, List<String> lines, String dockerVersion,
                                 DockerRunBase dockerRun, List<String> hosts, String param) {
        lines.add("cd " + FWPathUtil.getClassPath(clazz));
        {
            param = "";
            dealDockerRun(lines, hosts, dockerRun, dockerVersion, param);
        }
    }

    @Override
    public List<String> dockerSpecialYum() {
        List<String> yums = Lists.newArrayList();
        yums.add("squid");
        return yums;
    }

    @Override
    public void dockerShell(File file) throws Exception {
        List<String> lines = Lists.newArrayList();
        lines.add("squid restart");
        ShellWriteUtil.writeListTail(file, lines);
    }

    @Override
    public List<File> buildConfigFile() {
        List<File> files = Lists.newArrayList();
        return files;
    }
}
