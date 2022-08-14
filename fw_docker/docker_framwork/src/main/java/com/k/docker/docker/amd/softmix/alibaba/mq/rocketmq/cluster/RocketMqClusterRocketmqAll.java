package com.k.docker.docker.amd.softmix.alibaba.mq.rocketmq.cluster;

import com.k.docker.docker.amd.softmix.alibaba.mq.rocketmq.cluster.build.RocketMqClusterBuild;
import com.k.docker.docker.amd.softmix.alibaba.mq.rocketmq.cluster.image.create.RocketMqClusterImageCreate;
import com.k.docker.docker.amd.softmix.alibaba.mq.rocketmq.cluster.run.RocketMqClusterRun;
import com.k.docker.docker.amd.softmix.alibaba.mq.rocketmq.cluster.scan.RocketMqClusterWlanScan;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({RocketMqClusterBuild.class, RocketMqClusterImageCreate.class, RocketMqClusterRun.class, RocketMqClusterWlanScan.class})
public class RocketMqClusterRocketmqAll {

}