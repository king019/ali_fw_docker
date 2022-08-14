package com.k.docker.docker.amd.softmix.alibaba;

import com.k.docker.docker.amd.softmix.alibaba.dubbo.DubboAll;
import com.k.docker.docker.amd.softmix.alibaba.jstorm.JstormAll;
import com.k.docker.docker.amd.softmix.alibaba.mq.AlibabaMqAll;
import com.k.docker.docker.amd.softmix.alibaba.rocketmq.rocketmq.RocketMqRocketmqAll;
import com.k.docker.docker.amd.softmix.alibaba.tair.TairAll;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({DubboAll.class, JstormAll.class, TairAll.class, RocketMqRocketmqAll.class, AlibabaMqAll.class})
public class AlibabaAll {

}
