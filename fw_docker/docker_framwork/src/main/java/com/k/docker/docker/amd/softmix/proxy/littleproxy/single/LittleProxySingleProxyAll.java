package com.k.docker.docker.amd.softmix.proxy.littleproxy.single;

import com.k.docker.docker.amd.softmix.proxy.littleproxy.single.build.LittleProxySingleBuild;
import com.k.docker.docker.amd.softmix.proxy.littleproxy.single.image.create.LittleProxySingleImageCreate;
import com.k.docker.docker.amd.softmix.proxy.littleproxy.single.run.LittleProxySingleRun;
import com.k.docker.docker.amd.softmix.proxy.littleproxy.single.scan.LittleProxySingleWlanScan;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({LittleProxySingleBuild.class, LittleProxySingleRun.class, LittleProxySingleImageCreate.class, LittleProxySingleWlanScan.class})
public class LittleProxySingleProxyAll {

}