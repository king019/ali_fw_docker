package com.k.docker.docker.amd.soft.server.spring.boot.show.install;

import com.google.common.collect.Lists;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.soft.base.impl.DockerBaseImpl;
import com.k.docker.docker.util.docker.SoftVersion;
import com.k.docker.docker.util.file.ShellWriteUtil;
import com.k.docker.docker.util.path.FWPathUtil;
import com.k.docker.docker.util.props.PropsDockerUtil;
import com.k.docker.docker.util.vo.DockerBuildVO;

import java.io.File;
import java.util.List;

public class ServerShowInstall extends DockerBaseImpl {
    String profile = "jvmProfile";

    public ServerShowInstall() {
        dockerBuildVOs.add(SoftVersion.springShowSoft);
    }

    public static void dockerRun(final Class clazz, final List<String> lines, final String dockerVersion, final DockerRunBase dockerRun, final List<String> hosts, String param) {
        lines.add("cd " + FWPathUtil.getClassPath(clazz));
        for (final String host : hosts) {
            param = host;
            dockerRun.dockerRun(lines, dockerVersion, host, param);
        }
    }

    @Override
    public void dockerShell(final File file) throws Exception {
        DockerBuildVO dockerSoft = queryTar("show");
        ShellWriteUtil.writeSourceProfile(file);
        ShellWriteUtil.writeShell(file, "java -verbose >> java_version");
        List<String> lines = Lists.newArrayList();
        lines.add("java -Xmx128m -Xms64m -Xmn32m -Xss16m -jar " + PropsDockerUtil.dockerDir + dockerSoft.unpress + " >> show.log");
        ShellWriteUtil.writeListTail(file, lines);
    }
}
