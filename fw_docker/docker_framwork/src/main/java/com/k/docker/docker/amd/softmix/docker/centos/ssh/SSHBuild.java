package com.k.docker.docker.amd.softmix.docker.centos.ssh;

import com.k.docker.docker.amd.softmix.docker.centos.ssh.build.CentosSSHBuild;
import com.k.docker.docker.amd.softmix.docker.centos.ssh.run.SSHRun;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CentosSSHBuild.class, SSHRun.class})
public class SSHBuild {

}
