package com.k.docker.dockerhub.soft.amd.proxy;

import com.k.docker.dockerhub.soft.amd.proxy.littleproxy.littleproxy.LittleProxyDockerInstall;
import com.k.docker.dockerhub.soft.amd.proxy.littleproxy.spring.LittleProxySpringDockerInstall;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({LittleProxyDockerInstall.class, LittleProxySpringDockerInstall.class})
public class ProxyHubAll {

}
