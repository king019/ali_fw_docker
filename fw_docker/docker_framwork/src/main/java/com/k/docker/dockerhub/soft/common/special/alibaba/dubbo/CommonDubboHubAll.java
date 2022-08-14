package com.k.docker.dockerhub.soft.common.special.alibaba.dubbo;

import com.k.docker.dockerhub.soft.common.special.alibaba.dubbo.admin.item.CommonDubboAdminInstall;
import com.k.docker.dockerhub.soft.common.special.alibaba.dubbo.consumer.item.CommonDubboConsumerInstall;
import com.k.docker.dockerhub.soft.common.special.alibaba.dubbo.monitor.item.CommonDubboMonitorInstall;
import com.k.docker.dockerhub.soft.common.special.alibaba.dubbo.provider.item.CommonDubboProviderInstall;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CommonDubboAdminInstall.class, CommonDubboMonitorInstall.class, CommonDubboProviderInstall.class, CommonDubboConsumerInstall.class})
public class CommonDubboHubAll {

}
