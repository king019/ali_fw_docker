package com.k.docker.docker.amd.soft.cache.redis.cluster.install;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.softmix.cache.redis.cluster.redis.resource.RedisClusterResDir;
import com.k.docker.docker.base.soft.base.DockerBase;
import com.k.docker.docker.base.soft.base.impl.DockerBaseImpl;
import com.k.docker.docker.util.docker.SoftVersion;
import com.k.docker.docker.util.file.FileWriteUtil;
import com.k.docker.docker.util.file.ShellWriteUtil;
import com.k.docker.docker.util.path.FWPathUtil;
import com.k.docker.docker.util.props.PropsDockerUtil;
import com.k.docker.docker.util.vo.DockerBuildVO;

import java.io.File;
import java.util.List;

public class RedisClusterInstall extends DockerBaseImpl {
    private static String mvDirc = "redis";

    public RedisClusterInstall() {
        dockerBuildVOs.add(SoftVersion.redisSoft);
    }

    @Override
    public List<String> dockerSpecialYum() {
        List<String> yums = Lists.newArrayList();
        yums.add("gcc-c++");
        yums.add("make");
        yums.add("libevent");
        yums.add("zlib-devel ");
        yums.add("rubygems");
        yums.add(" ruby");
        yums.add(" gem");
        yums.add(" ruby-devel ruby-docs ruby-ri ruby-irb ruby-rdoc");
        return yums;
    }

    @Override
    public List<DockerBuildVO> dockerResFile() {
        List<String> files = FWPathUtil.getDirFilesPath(RedisClusterResDir.class);
        List<DockerBuildVO> dockerBuildVOs = Lists.newArrayList();
        DockerBuildVO vo;
        for (String fname : files) {
            vo = new DockerBuildVO(fname, fname, fname);
            dockerBuildVOs.add(vo);
        }
        return dockerBuildVOs;
    }

    @Override
    public void dockerShell(File file) throws Exception {
        DockerBuildVO dockerSoft = redisTar();
        ShellWriteUtil.writeTar(file, dockerSoft.tar);
        ShellWriteUtil.writeMv(file, dockerSoft.unpress, dockerSoft.mvDir);
        List<String> lines = Lists.newArrayList();
        lines.add("wk=$1");
        lines.add("mkdir -p " + PropsDockerUtil.dockerDir + "redis/conf/$wk ");
        lines.add("cp " + PropsDockerUtil.dockerDir + "redis$wk.conf " + PropsDockerUtil.dockerDir + "redis/conf/redis$wk.conf");
        String nowPath = "nowPath=$(pwd ./)";
        lines.add(nowPath);
        lines.add("cd ./redis/src");
        lines.add(" make clean");
        lines.add(" make && make install");
        lines.add("cd $nowPath");
        lines.add(PropsDockerUtil.dockerDir + mvDirc + "/src/redis-server " + PropsDockerUtil.dockerDir + "redis/conf/redis$wk.conf");
        ShellWriteUtil.writeListTail(file, lines);
    }

    private DockerBuildVO redisTar() {
        DockerBuildVO vo = null;
        for (DockerBuildVO dockerBuildVO : dockerBuildVOs) {
            if (dockerBuildVO.tar.endsWith("tar.gz")) {
                vo = dockerBuildVO;
                break;
            }
        }
        return vo;
    }

    @Override
    public List<File> buildConfigFile() {
        List<File> files = Lists.newArrayList();
        try {
            File conf;
            for (int port = 7001; port < 7022; port++) {
                conf = File.createTempFile("redis" + port + ".conf" + DockerBase.tempSub, DockerBase.tempSub);
                {
                    FileWriteUtil.write(conf, "port  " + port);
                    FileWriteUtil.write(conf, "cluster-enabled  yes ");
                    FileWriteUtil.write(conf, "cluster-config-file  nodes.conf ");
                    FileWriteUtil.write(conf, "cluster-node-timeout  5000 ");
                    FileWriteUtil.write(conf, "appendonly  yes ");
                    FileWriteUtil.write(conf, "daemonize yes");
                    FileWriteUtil.write(conf, "protected-mode no");
                }
                files.add(conf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return files;
    }
}
