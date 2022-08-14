package com.k.docker.docker.amd.softmix.alibaba.jstorm.zookeeper.build;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.apache.zookeeper.install.ZookeeperInstall;
import com.k.docker.docker.amd.soft.jdk.JdkInstall;
import com.k.docker.docker.amd.softmix.alibaba.jstorm.jstorm.config.host.JstormHost;
import com.k.docker.docker.base.soft.base.DockerBase;
import com.k.docker.docker.base.softmix.adapter.SoftMixAdapter;

import java.util.List;

public class JstormZookeeperBuild extends SoftMixAdapter {
    @Override
    public List<DockerBase> queryDocks() {
        JstormHost stormHost = new JstormHost();
        dockerBases = Lists.newArrayList();
        dockerBases.add(new JdkInstall());
        dockerBases.add(new ZookeeperInstall(stormHost.jstorm_zookeeper()));
        return dockerBases;
    }
}
