package com.k.docker.docker.amd.soft.cache.middleware.codis.install;

import com.google.common.collect.Lists;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.soft.base.impl.DockerBaseImpl;
import com.k.docker.docker.util.docker.GitSoftVersion;
import com.k.docker.docker.util.file.FileWriteUtil;
import com.k.docker.docker.util.file.ShellWriteUtil;
import com.k.docker.docker.util.path.FWPathUtil;
import com.k.docker.docker.util.props.PropsDockerUtil;

import java.io.File;
import java.util.List;

public class CodisSingleInstall extends DockerBaseImpl {
    private final String goPath = "goProfile";

    public CodisSingleInstall() {
        dockerGitBuildVOs.add(GitSoftVersion.codis);
    }

    public static void dockerRun(Class clazz, List<String> lines, String dockerVersion, DockerRunBase dockerRun, List<String> hosts, String param) {
        lines.add("cd " + FWPathUtil.getClassPath(clazz));
        {
            param = "";
            dealDockerRun(lines, hosts, dockerRun, dockerVersion, param);
        }
    }

    @Override
    public List<String> dockerSpecialYum() {
        List<String> yums = Lists.newArrayList();
        yums.add("make");
        yums.add("go");
        yums.add("autoconf");
        return yums;
    }

    @Override
    public void dockerShell(File file) throws Exception {
        queryGitTar("codis");
        List<String> lines = Lists.newArrayList();
        ShellWriteUtil.writeCatAppend(file, goPath, "/etc/profile");
        ShellWriteUtil.writeSourceProfile(file);
        lines.add(" mkdir -p $GOPATH/src/github.com/CodisLabs");
        lines.add("cd $_ && git clone https://github.com/CodisLabs/codis.git -b release3.2");
        lines.add("cd $GOPATH/src/github.com/CodisLabs/codis");
        lines.add("make");
        lines.add("./admin/codis-dashboard-admin.sh start");
        lines.add(" ./admin/codis-proxy-admin.sh start");
        lines.add(" ./admin/codis-server-admin.sh start");
        lines.add("./admin/codis-fe-admin.sh start");
        ShellWriteUtil.writeListTail(file, lines);
    }

    @Override
    public List<File> buildConfigFile() {
        List<File> files = Lists.newArrayList();
        try {
            File conf;
            conf = File.createTempFile(goPath + tempSub, tempSub);
            {
                FileWriteUtil.write(conf, "echo 'export GOPATH=" + PropsDockerUtil.dockerDir + "gopath' ");
            }
            files.add(conf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return files;
    }
}
