package com.k.docker.docker.amd.softmix.server.spring.boot.build;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.jdk.JdkInstall;
import com.k.docker.docker.amd.soft.server.spring.boot.show.install.ServerShowInstall;
import com.k.docker.docker.base.soft.base.DockerBase;
import com.k.docker.docker.base.softmix.adapter.SoftMixAdapter;

import java.util.List;

public class ServerBootBuild extends SoftMixAdapter {
    @Override
    public List<DockerBase> queryDocks() {
        dockerBases = Lists.newArrayList();
        dockerBases.add(new JdkInstall());
        dockerBases.add(new ServerShowInstall());
        return dockerBases;
    }
}
