package com.k.docker.docker.amd.soft.elastic.kibana.single.install;

import com.google.common.collect.Lists;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.soft.base.DockerBase;
import com.k.docker.docker.base.soft.base.impl.DockerBaseImpl;
import com.k.docker.docker.util.docker.SoftVersion;
import com.k.docker.docker.util.file.FileWriteUtil;
import com.k.docker.docker.util.file.ShellWriteUtil;
import com.k.docker.docker.util.path.FWPathUtil;
import com.k.docker.docker.util.vo.DockerBuildVO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class KibanaSingleInstall extends DockerBaseImpl {
    public KibanaSingleInstall() {
        dockerBuildVOs.add(SoftVersion.kibanaSoft);
    }

    public KibanaSingleInstall(final List<String> hosts, final List<String> hosts2) {
        dockerBuildVOs.add(SoftVersion.kibanaSoft);
        this.hosts = hosts;
        this.hosts2 = hosts2;
    }

    public static void dockerRun(final Class clazz, final List<String> lines, final String dockerVersion, final DockerRunBase dockerRun, final List<String> hosts) {
        lines.add("cd " + FWPathUtil.getClassPath(clazz));
        {
            dealDockerRunParamHost(lines, hosts, dockerRun, dockerVersion);
        }
    }

    @Override
    public List<String> dockerSpecialYum() {
        final List<String> yums = Lists.newArrayList();
        // yums.add(" net-tools");
        return yums;
    }

    @Override
    public void dockerShell(final File file) throws Exception {
        final String host = "$1";
        final String configFile = "kibana.yml";
        String command;
        final DockerBuildVO es = queryTar("kibana");
        ShellWriteUtil.writeCdSoft(file);
        ShellWriteUtil.writeTar(file, es.tar);
        ShellWriteUtil.writeCdTar(file, es.mvDir);
        {
            command = "config/" + configFile;
            ShellWriteUtil.writeRmAbs(file, command);
            ShellWriteUtil.writeCp(file, host + configFile, es.mvDir + command);
        }
        command = "nohup ./bin/kibaba &";
        ShellWriteUtil.writeShell(file, command);
    }

    @Override
    public List<File> buildConfigFile() {
        final List<File> files = new ArrayList<>();
        File profile;
        try {
            final String elke = hosts2.get(0);
            for (final String host : hosts) {
                profile = File.createTempFile(host + "kibana.yml" + DockerBase.tempSub, DockerBase.tempSub);
                FileWriteUtil.write(profile, "server.port: 5601");
                FileWriteUtil.write(profile, "server.host: '" + host + "'");
                FileWriteUtil.write(profile, "elasticsearch.url: http://" + elke + ":9200");
                FileWriteUtil.write(profile, "kibana.index: '.kibana'");
                files.add(profile);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return files;
    }
}
