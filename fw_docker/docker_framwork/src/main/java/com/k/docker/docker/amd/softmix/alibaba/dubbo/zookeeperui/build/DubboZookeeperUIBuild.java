package com.k.docker.docker.amd.softmix.alibaba.dubbo.zookeeperui.build;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.apache.zookeeperui.install.ZookeeperUIInstall;
import com.k.docker.docker.amd.soft.jdk.JdkInstall;
import com.k.docker.docker.amd.softmix.alibaba.dubbo.dubbo.config.host.DubboHost;
import com.k.docker.docker.base.soft.base.DockerBase;
import com.k.docker.docker.base.softmix.adapter.SoftMixAdapter;

import java.util.List;

public class DubboZookeeperUIBuild extends SoftMixAdapter {
    @Override
    public List<DockerBase> queryDocks() {
        DubboHost stormHost = new DubboHost();
        List<DockerBase> dockerBases = Lists.newArrayList();
        dockerBases.add(new JdkInstall());
        dockerBases.add(new ZookeeperUIInstall(stormHost.dubbo_zookeeper()));
        return dockerBases;
    }
}
