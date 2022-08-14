package com.k.docker.docker.amd.softmix.alibaba.dubbo.dubbo.build;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.alibaba.dubbo.install.DubboInstall;
import com.k.docker.docker.amd.soft.apache.tomcat.install.TomcatInstall;
import com.k.docker.docker.amd.soft.jdk.JdkInstall;
import com.k.docker.docker.base.soft.base.DockerBase;
import com.k.docker.docker.base.softmix.adapter.SoftMixAdapter;

import java.util.List;

public class DubboBuild extends SoftMixAdapter {
    @Override
    public List<DockerBase> queryDocks() {
        dockerBases = Lists.newArrayList();
        dockerBases.add(new JdkInstall());
        dockerBases.add(new TomcatInstall(true, true, false));
        dockerBases.add(new DubboInstall());
        return dockerBases;
    }
}
