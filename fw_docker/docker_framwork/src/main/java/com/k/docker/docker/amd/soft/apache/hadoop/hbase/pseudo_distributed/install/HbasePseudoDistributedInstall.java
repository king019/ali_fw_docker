package com.k.docker.docker.amd.soft.apache.hadoop.hbase.pseudo_distributed.install;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.softmix.apache.hadoop.hbase.pseudo_distributed.hbase.build.HbasePseudoDistributedBuild;
import com.k.docker.docker.amd.softmix.apache.hadoop.hbase.pseudo_distributed.hbase.resource.HbasePseudoDistributedResDir;
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

public class HbasePseudoDistributedInstall extends DockerBaseImpl {
    private final String hbaseProfile = "hbaseProfile";

    public HbasePseudoDistributedInstall() {
        dockerBuildVOs.add(SoftVersion.hbaseSoft);
    }

    public static void dockerRun(final Class clazz, final List<String> lines, final String dockerVersion, final DockerRunBase dockerRun, final List<String> hosts, final String param) {
        lines.add("cd " + FWPathUtil.getClassPath(clazz));
        final String memery = "500m";
        {
            for (final String host : hosts) {
                dockerRun.dockerRunVolume(lines, dockerVersion, host, param, memery);
            }
        }
    }

    @Override
    public List<DockerBuildVO> dockerResFile() {
        final List<String> files = FWPathUtil.getDirFilesPath(HbasePseudoDistributedResDir.class);
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
        yums.add("gcc-c++");
        yums.add("which");
        yums.add("openssh-server");
        yums.add("openssh-clients");
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
        runs.add("ssh-keygen -t dsa -f  /root/.ssh/id_dsa");
        runs.add("cat ~/.ssh/id_dsa.pub > ~/.ssh/authorized_keys ");
        return runs;
    }

    @Override
    public void dockerShell(final File file) throws Exception {
        {
            FWPathUtil.copyFileToDir(HbasePseudoDistributedResDir.class, HbasePseudoDistributedBuild.class);
        }
        final DockerBuildVO dockerSoft = queryTar("hbase");
        final String hbaseHome = PropsDockerUtil.dockerDir + "hbase";
        ShellWriteUtil.writeTar(file, dockerSoft.tar);
        ShellWriteUtil.writeMv(file, dockerSoft.unpress, dockerSoft.mvDir);
        ShellWriteUtil.writeCatAppend(file, hbaseProfile, "/etc/profile");
        ShellWriteUtil.writeSourceProfile(file);
        ShellWriteUtil.writeShell(file, "java -verbose >> java_version");
        {
            final List<DockerBuildVO> dockerBuildVOs = dockerResFile();
            for (final DockerBuildVO dockerBuildVO : dockerBuildVOs) {
                ShellWriteUtil.writeCatCover(file, dockerBuildVO.getTar(), PropsDockerUtil.dockerDir + "hbase/conf/" + dockerBuildVO.getTar());
            }
        }
        final List<String> lines = Lists.newArrayList();
        ShellWriteUtil.writeCdSoft(file);
        //lines.add("/usr/sbin/sshd");
        lines.add("cd " + hbaseHome + "/bin");
        // lines.add("./start-hbase.sh");
        ShellWriteUtil.writeListTail(file, lines);
    }

    @Override
    public List<File> buildConfigFile() {
        final List<File> files = Lists.newArrayList();
        try {
            final File profile = File.createTempFile(hbaseProfile + tempSub, tempSub);
            {
                FileWriteUtil.write(profile, "export HBASE_HOME=" + PropsDockerUtil.dockerDir + "hbase");
                FileWriteUtil.write(profile, "export PATH=$HBASE_HOME/bin:$PATH");
            }
            files.add(profile);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return files;
    }
}
