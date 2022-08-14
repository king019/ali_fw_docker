package com.k.docker.docker.amd.soft.apache.hadoop.hadoop.pseudo_distributed.install;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.pseudo_distributed.build.HadoopPseudoDistributedBuild;
import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.pseudo_distributed.config.host.HadoopPseudoDistributedHost;
import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.pseudo_distributed.resource.HadoopPseudoDistributedResDir;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.soft.base.impl.DockerBaseImpl;
import com.k.docker.docker.util.docker.SoftVersion;
import com.k.docker.docker.util.file.FileWriteUtil;
import com.k.docker.docker.util.file.ShellWriteUtil;
import com.k.docker.docker.util.path.FWPathUtil;
import com.k.docker.docker.util.props.PropsDockerUtil;
import com.k.docker.docker.util.vo.DockerBuildVO;

import java.io.File;
import java.util.List;

public class HadoopPseudoDistributedInstall extends DockerBaseImpl {
    private String hadoopProfile = "hadoopProfile";

    public HadoopPseudoDistributedInstall() {
        dockerBuildVOs = Lists.newArrayList();
        dockerBuildVOs.add(SoftVersion.hadoopSoft);
        dockerBuildVOs.addAll(dockerResFile());
    }

    public static void dockerRun(Class<HadoopPseudoDistributedBuild> clazz, List<String> lines, String dockerVersion, DockerRunBase dockerRun, HadoopPseudoDistributedHost hostEntity, String param) {
        List<String> hosts;
        lines.add("cd " + FWPathUtil.getClassPath(clazz));
        {
            param = "admin";
            hosts = hostEntity.hadoop_single();
            dealDockerRunVolumeNoLimitMemery(lines, hosts, dockerRun, dockerVersion, param);
        }
    }

    @Override
    public List<DockerBuildVO> dockerResFile() {
        List<String> files = FWPathUtil.getDirFilesPath(HadoopPseudoDistributedResDir.class);
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
        List<String> yums = Lists.newArrayList();
        yums.add("gcc-c++");
        yums.add("which");
        yums.add("openssh-server");
        yums.add("openssh-clients");
        return yums;
    }

    @Override
    public List<String> dockerSpecialRuns() {
        List<String> runs = Lists.newArrayList();
        runs.add("ssh-keygen -t rsa -f /etc/ssh/ssh_host_rsa_key");
        runs.add("ssh-keygen -t ecdsa -f /etc/ssh/ssh_host_ecdsa_key");
        runs.add("ssh-keygen -t dsa -f /etc/ssh/ssh_host_dsa_key");
        runs.add("ssh-keygen -t ed25519 -f /etc/ssh/ssh_host_ed25519_key");
        runs.add("sed -ri 's/^PermitRootLogin\\s+.*/PermitRootLogin yes/' /etc/ssh/sshd_config");
        runs.add("sed -ri 's/UsePAM yes/#UsePAM yes/g' /etc/ssh/sshd_config");
        runs.add("mkdir -p /root/.ssh");
        runs.add("ssh-keygen -t dsa -f /root/.ssh/id_dsa");
        runs.add("cat ~/.ssh/id_dsa.pub > ~/.ssh/authorized_keys ");
        return runs;
    }

    @Override
    public void dockerShell(File file) throws Exception {
        {
            FWPathUtil.copyFileToDir(HadoopPseudoDistributedResDir.class, HadoopPseudoDistributedBuild.class);
        }
        dealHadoopTar(file);
        ShellWriteUtil.writeCatAppend(file, hadoopProfile, "/etc/profile");
        ShellWriteUtil.writeSourceProfile(file);
        ShellWriteUtil.writeShell(file, "java -verbose >> java_version");
        List<String> lines = Lists.newArrayList();
        List<DockerBuildVO> dockerBuildVOs = dockerResFile();
        for (DockerBuildVO dockerBuildVO : dockerBuildVOs) {
            ShellWriteUtil.writeCatCover(file, dockerBuildVO.getTar(), PropsDockerUtil.dockerDir + "hadoop/etc/hadoop/" + dockerBuildVO.getTar());
        }
        ShellWriteUtil.writeCdSoft(file);
        // lines.add(" ssh-keygen -t dsa");
        // lines.add("cat ~/.ssh/id_dsa.pub >> ~/.ssh/authorized_keys ");
        //lines.add("/usr/sbin/sshd");
        // lines.add("cd " + hadoopHome + "/bin");
        // lines.add("./hdfs namenode -format");
        // lines.add("cd " + hadoopHome + "/sbin");
        // lines.add("./start-dfs.sh");
        // lines.add("cd " + hadoopHome + "/sbin");
        // lines.add("./start-yarn.sh");
        ShellWriteUtil.writeListTail(file, lines);
    }

    private void dealHadoopTar(File file) throws Exception {
        for (DockerBuildVO dockerBuildVO : dockerBuildVOs) {
            ShellWriteUtil.writeTar(file, dockerBuildVO.tar);
            ShellWriteUtil.writeMv(file, dockerBuildVO.unpress, dockerBuildVO.mvDir);
        }
    }

    @Override
    public List<File> buildConfigFile() {
        List<File> files = Lists.newArrayList();
        try {
            File profile = File.createTempFile(hadoopProfile + tempSub, tempSub);
            {
                FileWriteUtil.write(profile, "export HADOOP_HOME=" + PropsDockerUtil.dockerDir + "hadoop");
                FileWriteUtil.write(profile, "export PATH=$HADOOP_HOME/bin:$PATH");
            }
            files.add(profile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return files;
    }
}
