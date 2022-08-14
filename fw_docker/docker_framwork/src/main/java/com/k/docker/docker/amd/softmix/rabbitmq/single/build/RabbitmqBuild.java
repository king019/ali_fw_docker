package com.k.docker.docker.amd.softmix.rabbitmq.single.build;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.rabbitmq.single.install.RabbitmqSingleInstall;
import com.k.docker.docker.base.soft.base.DockerBase;
import com.k.docker.docker.base.softmix.adapter.SoftMixAdapter;

import java.util.List;

public class RabbitmqBuild extends SoftMixAdapter {
    @Override
    public List<DockerBase> queryDocks() {
        dockerBases = Lists.newArrayList();
        dockerBases.add(new RabbitmqSingleInstall());
        return dockerBases;
    }
}
