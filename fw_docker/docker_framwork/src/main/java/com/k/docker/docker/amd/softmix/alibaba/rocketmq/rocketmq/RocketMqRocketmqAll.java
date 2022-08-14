package com.k.docker.docker.amd.softmix.alibaba.rocketmq.rocketmq;

import com.k.docker.docker.amd.softmix.alibaba.rocketmq.rocketmq.build.RocketMqBuild;
import com.k.docker.docker.amd.softmix.alibaba.rocketmq.rocketmq.image.create.RocketMqImageCreate;
import com.k.docker.docker.amd.softmix.alibaba.rocketmq.rocketmq.run.RocketMqRun;
import com.k.docker.docker.amd.softmix.alibaba.rocketmq.rocketmq.scan.RocketMqWlanScan;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({RocketMqBuild.class, RocketMqImageCreate.class, RocketMqRun.class, RocketMqWlanScan.class})
public class RocketMqRocketmqAll {

}