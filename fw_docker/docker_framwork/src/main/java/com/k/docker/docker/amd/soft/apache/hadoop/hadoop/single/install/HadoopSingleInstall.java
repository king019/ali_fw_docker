package com.k.docker.docker.amd.soft.apache.hadoop.hadoop.single.install;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.single.hadoop.build.HadoopSingleBuild;
import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.single.hadoop.config.host.HadoopSingleHost;
import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.single.hadoop.resource.HadoopSingleResDir;
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

public class HadoopSingleInstall extends DockerBaseImpl {
    private final String hadoopProfile = "hadoopProfile";

    public HadoopSingleInstall() {
        dockerBuildVOs = Lists.newArrayList();
        dockerBuildVOs.add(SoftVersion.hadoopSoft);
        dockerBuildVOs.addAll(dockerResFile());
    }

    public static void dockerRun(final Class<HadoopSingleBuild> clazz, final List<String> lines, final String dockerVersion, final DockerRunBase dockerRun, final HadoopSingleHost hostEntity, String param) {
        final List<String> hosts;
        lines.add("cd " + FWPathUtil.getClassPath(clazz));
        {
            param = "admin";
            hosts = hostEntity.hadoop_single();
            dealDockerRunNoLimitMemery(lines, hosts, dockerRun, dockerVersion, param);
        }
    }

    @Override
    public List<DockerBuildVO> dockerResFile() {
        final List<String> files = FWPathUtil.getDirFilesPath(HadoopSingleResDir.class);
        final List<DockerBuildVO> dockerBuildVOs = Lists.newArrayList();
        DockerBuildVO vo;
        for (final String fname : files) {
            vo = new DockerBuildVO(fname, fname, fname);
            dockerBuildVOs.add(vo);
        }
        return dockerBuildVOs;
    }

    @Override
    public List<String> dockerSpecialYum() {
        final List<String> yums = Lists.newArrayList();
        yums.add("gcc-c++ which openssh-server openssh-clients");
        return yums;
    }

    @Override
    public List<String> dockerSpecialRuns() {
        final List<String> runs = Lists.newArrayList();
        runs.add("ssh-keygen -t rsa -f /etc/ssh/ssh_host_rsa_key");
        runs.add("ssh-keygen -t ecdsa -f /etc/ssh/ssh_host_ecdsa_key");
        runs.add("ssh-keygen -t dsa -f /etc/ssh/ssh_host_dsa_key");
        runs.add("ssh-keygen -t ed25519 -f /etc/ssh/ssh_host_ed25519_key");
        runs.add("sed -ri 's/^PermitRootLogin\\s+.*/PermitRootLogin yes/' /etc/ssh/sshd_config");
        runs.add("sed -ri 's/UsePAM yes/#UsePAM yes/g' /etc/ssh/sshd_config");
        runs.add("mkdir -p /root/.ssh");
        runs.add("ssh-keygen -t dsa -f /root/.ssh/id_dsa");
        runs.add(" cat ~/.ssh/id_dsa.pub > ~/.ssh/authorized_keys ");
        return runs;
    }

    @Override
    public void dockerShell(final File file) throws Exception {
        {
            FWPathUtil.copyFileToDir(HadoopSingleResDir.class, HadoopSingleBuild.class);
        }
        final String hadoopHome = PropsDockerUtil.dockerDir + "hadoop";
        dealHadoopTar(file);
        ShellWriteUtil.writeCatAppend(file, hadoopProfile, "/etc/profile");
        ShellWriteUtil.writeSourceProfile(file);
        ShellWriteUtil.writeShell(file, "java -verbose >> java_version");
        final List<String> lines = Lists.newArrayList();
        final List<DockerBuildVO> dockerBuildVOs = dockerResFile();
        for (final DockerBuildVO dockerBuildVO : dockerBuildVOs) {
            ShellWriteUtil.writeCatCover(file, dockerBuildVO.getTar(), PropsDockerUtil.dockerDir + "hadoop/etc/hadoop/" + dockerBuildVO.getTar());
        }
        ShellWriteUtil.writeCdSoft(file);
        // lines.add(" rm -fr /usr/soft/data/.ssh");
        // lines.add(" cp -R ~/.ssh/ /usr/soft/data/");
        // lines.add(" rm -fr /root/.ssh");
        // lines.add(" ssh-keygen -t dsa");
        // lines.add("cat ~/.ssh/id_dsa.pub > ~/.ssh/authorized_keys ");
        //lines.add("/usr/sbin/sshd");
        lines.add("cd " + hadoopHome + "/bin");
        lines.add("./hdfs namenode -format");
        lines.add("cd " + hadoopHome + "/sbin");
        lines.add("./start-dfs.sh");
        lines.add("cd " + hadoopHome + "/sbin");
        lines.add("./start-yarn.sh");
        ShellWriteUtil.writeListTail(file, lines);
    }

    private void dealHadoopTar(final File file) throws Exception {
        for (final DockerBuildVO dockerBuildVO : dockerBuildVOs) {
            ShellWriteUtil.writeTar(file, dockerBuildVO.tar);
            ShellWriteUtil.writeMv(file, dockerBuildVO.unpress, dockerBuildVO.mvDir);
        }
    }

    @Override
    public List<File> buildConfigFile() {
        final List<File> files = Lists.newArrayList();
        try {
            final File profile = File.createTempFile(hadoopProfile + DockerBase.tempSub, DockerBase.tempSub);
            FileWriteUtil.write(profile, "export HADOOP_HOME=" + PropsDockerUtil.dockerDir + "hadoop");
            FileWriteUtil.write(profile, "export PATH=$HADOOP_HOME/bin:$PATH");
            files.add(profile);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return files;
    }
}
