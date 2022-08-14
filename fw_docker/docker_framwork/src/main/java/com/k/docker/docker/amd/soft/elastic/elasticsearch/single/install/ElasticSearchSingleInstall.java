package com.k.docker.docker.amd.soft.elastic.elasticsearch.single.install;

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

public class ElasticSearchSingleInstall extends DockerBaseImpl {
    public ElasticSearchSingleInstall(final List<String> hosts) {
        dockerBuildVOs.add(SoftVersion.elasticsearchSoft);
        this.hosts = hosts;
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
        mvfile(file);
        final String host = "$1";
        final String configFile = "elasticsearch.yml";
        final String command;
        final DockerBuildVO es = queryTar("elasticsearch");
        ShellWriteUtil.writeCdSoft(file);
        ShellWriteUtil.writeTar(file, es.tar);
        ShellWriteUtil.writeMv(file, es.unpress, es.mvDir);
        ShellWriteUtil.writeCdTar(file, es.mvDir);
        {
            command = "/config/" + configFile;
            ShellWriteUtil.writeRmAbs(file, command);
            ShellWriteUtil.writeCp(file, host + configFile, es.mvDir + command);
        }
        ShellWriteUtil.writeChmod(file);
        // command = "./bin/plugin install mobz/elasticsearch-head";
        // ShellWriteUtil.writeShell(file, command);
        {
            ShellWriteUtil.writeMkdir(file, "elasticsearch/data");
            ShellWriteUtil.writeMkdir(file, "elasticsearch/logs");
        }
        suFile(file);
        // command = "nohup ./bin/elasticsearch &";
        // ShellWriteUtil.writeAddUser(file, "elke");
        // ShellWriteUtil.writeSuUser(file, "elke", command);
        // ShellWriteUtil.writeShell(file, command);
        // ShellWriteUtil.writeTail(file);
    }

    private void mvfile(final File file) throws Exception {
        final String limits = "/etc/security/limits.conf";
        final String sysctl = "/etc/sysctl.conf";
        ShellWriteUtil.writeRmAbs(file, limits);
        ShellWriteUtil.writeRmAbs(file, sysctl);
        ShellWriteUtil.writeCpAbs(file, "limits.conf", limits);
        ShellWriteUtil.writeCpAbs(file, "sysctl.conf", sysctl);
        ShellWriteUtil.writeSysctl(file);
    }

    private void suFile(final File file) throws Exception {
        ShellWriteUtil.writeAddUser(file, "elke");
        ShellWriteUtil.writeSuUserFile(file, "elke", PropsDockerUtil.dockerDir + "shell.sh");
    }

    @Override
    public List<File> buildConfigFile() {
        final List<File> files = new ArrayList<>();
        File profile;
        try {
            for (final String host : hosts) {
                profile = File.createTempFile(host + "elasticsearch.yml" + DockerBase.tempSub, DockerBase.tempSub);
                FileWriteUtil.write(profile, "cluster.name: es_cluster");
                FileWriteUtil.write(profile, "node.name: node_" + host);
                FileWriteUtil.write(profile, "path.data: " + PropsDockerUtil.dockerDir + "elasticsearch/data");
                FileWriteUtil.write(profile, "path.logs: " + PropsDockerUtil.dockerDir + "elasticsearch/logs");
                FileWriteUtil.write(profile, "network.host: " + host);
                FileWriteUtil.write(profile, "http.port: 9200");
                files.add(profile);
            }
            {
                profile = File.createTempFile("limits.conf" + DockerBase.tempSub, DockerBase.tempSub);
                FileWriteUtil.write(profile, "elk soft nofile 65536");
                FileWriteUtil.write(profile, "elk hard nofile 65536");
                FileWriteUtil.write(profile, "elk soft nproc 2048");
                FileWriteUtil.write(profile, "elk hard nproc 2048");
                FileWriteUtil.write(profile, "elk soft memlock unlimited");
                FileWriteUtil.write(profile, "elk hard memlock unlimited");
                files.add(profile);
            }
            {
                profile = File.createTempFile("sysctl.conf" + DockerBase.tempSub, DockerBase.tempSub);
                FileWriteUtil.write(profile, "vm.max_map_count = 262144");
                files.add(profile);
            }
            {
                profile = File.createTempFile("shell.sh" + DockerBase.tempSub, DockerBase.tempSub);
                String command = "#!/bin/bash";
                ShellWriteUtil.writeShell(profile, command);
                command = "set -x";
                ShellWriteUtil.writeShell(profile, command);
                ShellWriteUtil.writeSourceProfile(profile);
                command = "cd " + PropsDockerUtil.dockerDir + "elasticsearch";
                ShellWriteUtil.writeShell(profile, command);
                command = "./bin/elasticsearch  -d";
                ShellWriteUtil.writeShell(profile, command);
                ShellWriteUtil.writeTail(profile);
                files.add(profile);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return files;
    }
}
