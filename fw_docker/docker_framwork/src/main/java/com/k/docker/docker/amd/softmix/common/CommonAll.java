package com.k.docker.docker.amd.softmix.common;

import com.k.docker.docker.amd.soft.dnsmasq.install.ubuntu.DnsmasqInstall;
import com.k.docker.docker.amd.soft.docker.install.ubuntu.InstallUbuntuDocker;
import com.k.docker.docker.amd.soft.docker.install.ubuntu.InstallUbuntuDocker17;
import com.k.docker.docker.base.dnsmasq.dnsconfig.DnsConfig;
import com.k.docker.docker.base.dnsmasq.dnsdelete.DnsDeleteBase;
import com.k.docker.docker.base.docker.image.DockerImageRemoveBase;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({DnsConfig.class, InstallUbuntuDocker.class, InstallUbuntuDocker17.class, DnsDeleteBase.class, DnsmasqInstall.class, DockerImageRemoveBase.class})
public class CommonAll {

}
