package com.k.docker.dockerhub.soft.common.special.alibaba;

import com.k.docker.dockerhub.soft.common.special.alibaba.arthas.CommonArthasHubAll;
import com.k.docker.dockerhub.soft.common.special.alibaba.dubbo.CommonDubboHubAll;
import com.k.docker.dockerhub.soft.common.special.alibaba.nacos.CommonAlibabaNacosHubAll;
import com.k.docker.dockerhub.soft.common.special.alibaba.rocketmq.CommonRocketmqHubAll;
import com.k.docker.dockerhub.soft.common.special.alibaba.sentinel.CommonAlibabaSentinelHubAll;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CommonArthasHubAll.class, CommonDubboHubAll.class, CommonAlibabaNacosHubAll.class, CommonAlibabaSentinelHubAll.class, CommonRocketmqHubAll.class})
public class CommonAlibabaHubAll {

}
