package com.k.docker.docker.amd.soft.ssh.install;

import com.google.common.collect.Lists;
import com.k.docker.docker.base.soft.base.impl.DockerBaseImpl;
import com.k.docker.docker.util.file.ShellWriteUtil;
import com.k.docker.docker.util.props.PropsDockerUtil;

import java.io.File;
import java.util.List;

public class SShInstall extends DockerBaseImpl {
    public SShInstall() {
        // dockerBuildVOs.add(SoftVersion.zookeeperSoft);
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
        return runs;
    }

    @Override
    public void dockerShell(File file) throws Exception {
        List<String> lines = Lists.newArrayList();
        ShellWriteUtil.writeCdSoft(file);
        lines.add(" rm -fr /root/.ssh");
        lines.add(" ssh-keygen -t dsa");
        lines.add("cat ~/.ssh/id_dsa.pub >> ~/.ssh/authorized_keys ");
        lines.add("cat ~/.ssh/id_dsa.pub > " + PropsDockerUtil.dockerDir + "data/.ssh/authorized_keys ");
        //lines.add("/usr/sbin/sshd");
        ShellWriteUtil.writeListTail(file, lines);
    }
}
