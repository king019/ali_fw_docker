package com.k.docker.docker.amd.softmix.alibaba.mq.rocketmq.cluster.build;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.alibaba.mq.rocketmq.cluster.install.RocketMqClusterInstall;
import com.k.docker.docker.amd.soft.apache.tomcat.install.TomcatInstall;
import com.k.docker.docker.amd.soft.jdk.JdkInstall;
import com.k.docker.docker.base.soft.base.DockerBase;
import com.k.docker.docker.base.softmix.adapter.SoftMixAdapter;

import java.util.List;

public class RocketMqClusterBuild extends SoftMixAdapter {
    @Override
    public List<DockerBase> queryDocks() {
        dockerBases = Lists.newArrayList();
        dockerBases.add(new JdkInstall());
        dockerBases.add(new TomcatInstall(true, true, false));
        dockerBases.add(new RocketMqClusterInstall());
        return dockerBases;
    }
}
