package com.k.docker.dockerhub.soft.common.special.linux;

import com.k.docker.dockerhub.soft.common.special.linux.alpine.CommonAlpineHubAll;
import com.k.docker.dockerhub.soft.common.special.linux.centos.CommonCentosHubAll;
import com.k.docker.dockerhub.soft.common.special.linux.linux.CommonLinuxItemHubAll;
import com.k.docker.dockerhub.soft.common.special.linux.openeuler.CommonOpeneulerHubAll;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CommonAlpineHubAll.class, CommonCentosHubAll.class, CommonLinuxItemHubAll.class, CommonOpeneulerHubAll.class})
public class CommonLinuxHubAll {

}
