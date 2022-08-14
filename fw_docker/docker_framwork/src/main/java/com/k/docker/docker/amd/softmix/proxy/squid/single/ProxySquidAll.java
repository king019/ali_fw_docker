package com.k.docker.docker.amd.softmix.proxy.squid.single;

import com.k.docker.docker.amd.softmix.proxy.squid.single.build.SquidBuild;
import com.k.docker.docker.amd.softmix.proxy.squid.single.image.create.SquidImageCreate;
import com.k.docker.docker.amd.softmix.proxy.squid.single.run.SquidRun;
import com.k.docker.docker.amd.softmix.proxy.squid.single.scan.SquidWlanScan;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({SquidBuild.class, SquidRun.class, SquidImageCreate.class, SquidWlanScan.class})
public class ProxySquidAll {

}