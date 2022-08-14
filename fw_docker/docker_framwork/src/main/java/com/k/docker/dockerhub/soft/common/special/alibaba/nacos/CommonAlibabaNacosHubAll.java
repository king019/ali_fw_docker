package com.k.docker.dockerhub.soft.common.special.alibaba.nacos;

import com.k.docker.dockerhub.soft.common.special.alibaba.nacos.feign.server.item.CommonNacosFeignServerInstall;
import com.k.docker.dockerhub.soft.common.special.alibaba.nacos.nacos.item.CommonNacosInstall;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CommonNacosInstall.class, CommonNacosFeignServerInstall.class})
public class CommonAlibabaNacosHubAll {

}
