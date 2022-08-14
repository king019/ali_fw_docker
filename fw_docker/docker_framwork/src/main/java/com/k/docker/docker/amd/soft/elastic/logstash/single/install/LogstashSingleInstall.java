package com.k.docker.docker.amd.soft.elastic.logstash.single.install;

import com.google.common.collect.Lists;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.soft.base.DockerBase;
import com.k.docker.docker.base.soft.base.impl.DockerBaseImpl;
import com.k.docker.docker.util.docker.SoftVersion;
import com.k.docker.docker.util.file.FileWriteUtil;
import com.k.docker.docker.util.file.ShellWriteUtil;
import com.k.docker.docker.util.path.FWPathUtil;
import com.k.docker.docker.util.props.PropsDockerUtil;
import com.k.docker.docker.util.vo.DockerBuildVO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LogstashSingleInstall extends DockerBaseImpl {
    public LogstashSingleInstall() {
        dockerBuildVOs.add(SoftVersion.logstashSoft);
    }

    public LogstashSingleInstall(List<String> hosts, List<String> hosts2) {
        dockerBuildVOs.add(SoftVersion.logstashSoft);
        this.hosts = hosts;
        this.hosts2 = hosts2;
    }

    public static void dockerRun(Class clazz, List<String> lines, String dockerVersion, DockerRunBase dockerRun, List<String> hosts) {
        lines.add("cd " + FWPathUtil.getClassPath(clazz));
        {
            dealDockerRunParamHost(lines, hosts, dockerRun, dockerVersion);
        }
    }

    @Override
    public List<String> dockerSpecialYum() {
        List<String> yums = Lists.newArrayList();
        // yums.add(" net-tools");
        return yums;
    }

    @Override
    public void dockerShell(File file) throws Exception {
        String host = "$1";
        String configFile = "/log4j_to_es.conf";
        String command;
        DockerBuildVO es = queryTar("logstash");
        ShellWriteUtil.writeCdSoft(file);
        ShellWriteUtil.writeTar(file, es.tar);
        ShellWriteUtil.writeCdTar(file, es.mvDir);
        command = "nohup ./bin/logstash agent -f    " + PropsDockerUtil.dockerDir + host + configFile + " &";
        ShellWriteUtil.writeShell(file, command);
    }

    @Override
    public List<File> buildConfigFile() {
        List<File> files = new ArrayList<>();
        File profile;
        try {
            String outs = "";
            for (String host : hosts2) {
                outs += "," + host + ":9200";
            }
            outs = outs.substring(1);
            for (String host : hosts) {
                profile = File.createTempFile(host + "log4j_to_es.conf" + DockerBase.tempSub, DockerBase.tempSub);
                FileWriteUtil.write(profile, "input {");
                FileWriteUtil.write(profile, "  log4j {");
                FileWriteUtil.write(profile, "   mode => 'server'");
                FileWriteUtil.write(profile, "   host => '" + host + "'");
                FileWriteUtil.write(profile, "   port => 4567");
                FileWriteUtil.write(profile, "  }");
                FileWriteUtil.write(profile, "}");
                FileWriteUtil.write(profile, "filter {");
                FileWriteUtil.write(profile, "}");
                FileWriteUtil.write(profile, "output {");
                FileWriteUtil.write(profile, "  elasticsearch {");
                FileWriteUtil.write(profile, "    action => 'index'");
                FileWriteUtil.write(profile, "    hosts  => '" + outs + "'");
                FileWriteUtil.write(profile, "    index  => 'applog' ");
                FileWriteUtil.write(profile, "  }");
                FileWriteUtil.write(profile, "}");
                files.add(profile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return files;
    }
}
