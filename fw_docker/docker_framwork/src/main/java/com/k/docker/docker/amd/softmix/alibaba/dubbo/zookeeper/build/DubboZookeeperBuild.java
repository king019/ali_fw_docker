package com.k.docker.docker.amd.softmix.alibaba.dubbo.zookeeper.build;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.apache.zookeeper.install.ZookeeperInstall;
import com.k.docker.docker.amd.soft.jdk.JdkInstall;
import com.k.docker.docker.amd.softmix.alibaba.dubbo.dubbo.config.host.DubboHost;
import com.k.docker.docker.base.soft.base.DockerBase;
import com.k.docker.docker.base.softmix.adapter.SoftMixAdapter;

import java.util.List;

public class DubboZookeeperBuild extends SoftMixAdapter {
    @Override
    public List<DockerBase> queryDocks() {
        DubboHost stormHost = new DubboHost();
        dockerBases = Lists.newArrayList();
        dockerBases.add(new JdkInstall());
        dockerBases.add(new ZookeeperInstall(stormHost.dubbo_zookeeper()));
        return dockerBases;
    }
}
