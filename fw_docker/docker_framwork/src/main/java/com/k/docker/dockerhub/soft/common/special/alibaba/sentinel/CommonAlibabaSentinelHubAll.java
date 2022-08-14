package com.k.docker.dockerhub.soft.common.special.alibaba.sentinel;

import com.k.docker.dockerhub.soft.common.special.alibaba.sentinel.sentineldashboard.item.CommonSentinelDashboardInstall;
import com.k.docker.dockerhub.soft.common.special.alibaba.sentinel.share.sentinelapollo.item.CommonSentinelShareApolloInstall;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CommonSentinelDashboardInstall.class, CommonSentinelShareApolloInstall.class})
public class CommonAlibabaSentinelHubAll {

}
