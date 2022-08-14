package com.k.docker.docker.amd.soft.apache.hadoop.hadoop.distributed.install;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.distributed.build.HadoopDistributedBuild;
import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.distributed.resource.HadoopDistributedResDir;
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
import java.util.List;

public class HadoopDistributedInstall extends DockerBaseImpl {
    private final String hadoopProfile = "hadoopProfile";
    private String hadoopHome = PropsDockerUtil.dockerDir + "hadoop";

    public HadoopDistributedInstall(List<String> hosts) {
        dockerBuildVOs.add(SoftVersion.hadoopSoft);
        this.hosts = hosts;
    }

    public static void dockerRun(final Class clazz, final List<String> lines, final String dockerVersion, final DockerRunBase dockerRun, final List<String> hosts, String param) {
        lines.add("cd " + FWPathUtil.getClassPath(clazz));
        {
            for (final String host : hosts) {
                param = host;
                dockerRun.dockerRun(lines, dockerVersion, host, param, "800m");
            }
        }
    }

    @Override
    public List<DockerBuildVO> dockerResFile() {
        List<String> files = FWPathUtil.getDirFilesPath(HadoopDistributedResDir.class);
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
        yums.add("gcc-c++");
        yums.add("which");
        return yums;
    }

    @Override
    public void dockerShell(final File file) throws Exception {
        final DockerBuildVO dockerSoft = queryTar("hadoop");
        {
            FWPathUtil.copyFileToDir(HadoopDistributedResDir.class, HadoopDistributedBuild.class);
        }
        ShellWriteUtil.writeTar(file, dockerSoft.tar);
        ShellWriteUtil.writeMv(file, dockerSoft.unpress, dockerSoft.mvDir);
        ShellWriteUtil.writeCatAppend(file, hadoopProfile, "/etc/profile");
        ShellWriteUtil.writeSourceProfile(file);
        ShellWriteUtil.writeShell(file, "java -verbose >> java_version");
        List<String> lines = Lists.newArrayList();
        List<DockerBuildVO> dockerBuildVOs = dockerResFile();
        for (DockerBuildVO dockerBuildVO : dockerBuildVOs) {
            ShellWriteUtil.writeCdSoft(file);
            ShellWriteUtil.writeCatCover(file, dockerBuildVO.getTar(), PropsDockerUtil.dockerDir + "hadoop/etc/hadoop/" + dockerBuildVO.getTar());
            if (dockerBuildVO.getTar().endsWith(".sh")) {
            }
        }
        ShellWriteUtil.writeCatCover(file, "workers", PropsDockerUtil.dockerDir + "hadoop/etc/hadoop/" + "workers");
        ShellWriteUtil.writeCdSoft(file);
        lines.add("cd " + hadoopHome + "/bin");
        lines.add("./hdfs namenode -format");
        lines.add("cd " + hadoopHome + "/sbin");
        lines.add("./start-dfs.sh");
        lines.add("cd " + hadoopHome + "/sbin");
        lines.add("./start-yarn.sh");
        lines.add("cd " + hadoopHome + "/sbin");
        lines.add("./mapred --daemon start historyserver");
        ShellWriteUtil.writeListTail(file, lines);
    }

    @Override
    public List<File> buildConfigFile() throws Exception {
        final List<File> files = Lists.newArrayList();
        {
            final File profile = File.createTempFile(hadoopProfile + DockerBase.tempSub, DockerBase.tempSub);
            FileWriteUtil.write(profile, "export HADOOP_HOME=" + PropsDockerUtil.dockerDir + "hadoop");
            FileWriteUtil.write(profile, "export PATH=$HADOOP_HOME/bin:$PATH");
            FileWriteUtil.write(profile, "export HADOOP_HEAPSIZE=\"100m\"");
            FileWriteUtil.write(profile, "export HDFS_DATANODE_USER=root");
            FileWriteUtil.write(profile, "export HDFS_NAMENODE_USER=root");
            FileWriteUtil.write(profile, "export HDFS_SECONDARYNAMENODE_USER=root");
            FileWriteUtil.write(profile, "export YARN_RESOURCEMANAGER_USER=root");
            FileWriteUtil.write(profile, "export YARN_NODEMANAGER_USER=root");
            files.add(profile);
        }
        {
            final File profile = File.createTempFile("workers" + DockerBase.tempSub, DockerBase.tempSub);
            for (String host : hosts) {
                if (host.contains("slave")) {
                    FileWriteUtil.write(profile, host);
                }
            }
            files.add(profile);
        }
        return files;
    }
}
