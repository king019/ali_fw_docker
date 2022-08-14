package com.k.docker.docker.amd.softmix.rabbitmq.cluster.build;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.rabbitmq.cluster.install.RabbitmqClusterInstall;
import com.k.docker.docker.amd.softmix.rabbitmq.cluster.config.host.RabbitmqClusterHost;
import com.k.docker.docker.base.soft.base.DockerBase;
import com.k.docker.docker.base.softmix.adapter.SoftMixAdapter;

import java.util.List;

public class RabbitmqClusterBuild extends SoftMixAdapter {
    @Override
    public List<DockerBase> queryDocks() {
        dockerBases = Lists.newArrayList();
        dockerBases.add(new RabbitmqClusterInstall(new RabbitmqClusterHost().docker_all()));
        return dockerBases;
    }
}
