package com.k.docker.docker.amd.soft.proxy.single.littleproxy.install;

import com.google.common.collect.Lists;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.soft.base.impl.DockerBaseImpl;
import com.k.docker.docker.util.file.ShellWriteUtil;
import com.k.docker.docker.util.path.FWPathUtil;

import java.io.File;
import java.util.List;

public class LittleProxySingleInstall extends DockerBaseImpl {
    public static void dockerRun(final Class clazz, final List<String> lines, final String dockerVersion, final DockerRunBase dockerRun, final List<String> hosts, String param) {
        lines.add("cd " + FWPathUtil.getClassPath(clazz));
        for (final String host : hosts) {
            param = host;
            dockerRun.dockerRun(lines, dockerVersion, host, param);
        }
    }

    @Override
    public List<String> dockerSpecialYum() {
        final List<String> yums = Lists.newArrayList();
        yums.add(" maven git");
        return yums;
    }

    @Override
    public void dockerShell(final File file) throws Exception {
        List<String> lines = Lists.newArrayList();
        lines.add("git clone git://github.com/adamfisk/LittleProxy.git");
        lines.add("cd LittleProxy");
        lines.add("./run.bash");
        ShellWriteUtil.writeListTail(file, lines);
    }
}
