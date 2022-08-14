package com.k.docker.docker.amd.softmix.proxy;

import com.k.docker.docker.amd.softmix.proxy.littleproxy.single.LittleProxySingleProxyAll;
import com.k.docker.docker.amd.softmix.proxy.squid.single.ProxySquidAll;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ProxySquidAll.class, LittleProxySingleProxyAll.class})
public class ProxyAll {

}