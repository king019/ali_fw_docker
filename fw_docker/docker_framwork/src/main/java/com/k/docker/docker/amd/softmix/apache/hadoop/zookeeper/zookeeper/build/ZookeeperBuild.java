package com.k.docker.docker.amd.softmix.apache.hadoop.zookeeper.zookeeper.build;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.apache.zookeeper.install.ZookeeperInstall;
import com.k.docker.docker.amd.soft.jdk.JdkInstall;
import com.k.docker.docker.amd.softmix.apache.hadoop.zookeeper.zookeeper.config.host.ZookeeperHost;
import com.k.docker.docker.base.soft.base.DockerBase;
import com.k.docker.docker.base.softmix.adapter.SoftMixAdapter;

import java.util.List;

public class ZookeeperBuild extends SoftMixAdapter {
    @Override
    public List<DockerBase> queryDocks() {
        ZookeeperHost stormHost = new ZookeeperHost();
        dockerBases = Lists.newArrayList();
        dockerBases.add(new JdkInstall());
        dockerBases.add(new ZookeeperInstall(stormHost.zookeeper_zookeeper()));
        return dockerBases;
    }
}
