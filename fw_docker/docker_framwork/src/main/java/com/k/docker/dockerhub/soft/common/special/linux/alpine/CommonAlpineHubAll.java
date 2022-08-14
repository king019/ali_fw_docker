package com.k.docker.dockerhub.soft.common.special.linux.alpine;

import com.k.docker.dockerhub.soft.common.special.linux.alpine.item.CommonAlpineInstall;
import com.k.docker.dockerhub.soft.common.special.linux.alpine.item.CommonDockerInstall;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CommonAlpineInstall.class, CommonDockerInstall.class})
public class CommonAlpineHubAll {

}
