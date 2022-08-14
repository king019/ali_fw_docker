package com.k.docker.dockerhub.soft.common.special;

import com.k.docker.dockerhub.soft.common.special.alibaba.CommonAlibabaHubAll;
import com.k.docker.dockerhub.soft.common.special.config.ConfigHubAll;
import com.k.docker.dockerhub.soft.common.special.ctrip.CommonCtripHubAll;
import com.k.docker.dockerhub.soft.common.special.frame.CommonFrameHubAll;
import com.k.docker.dockerhub.soft.common.special.linux.CommonLinuxHubAll;
import com.k.docker.dockerhub.soft.common.special.source.SourceHubAll;
import com.k.docker.dockerhub.soft.common.special.spring.CommonSrpingHubAll;
import com.k.docker.dockerhub.soft.common.special.tools.CommonToolsHubAll;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CommonFrameHubAll.class, ConfigHubAll.class, CommonAlibabaHubAll.class, CommonCtripHubAll.class, CommonLinuxHubAll.class, CommonSrpingHubAll.class, CommonToolsHubAll.class, SourceHubAll.class})
public class CommonSpecialHubSoftAll {

}
