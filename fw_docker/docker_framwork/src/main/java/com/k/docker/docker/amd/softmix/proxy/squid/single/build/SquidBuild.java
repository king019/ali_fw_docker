package com.k.docker.docker.amd.softmix.proxy.squid.single.build;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.proxy.squid.single.install.SquidSingleInstall;
import com.k.docker.docker.base.soft.base.DockerBase;
import com.k.docker.docker.base.softmix.adapter.SoftMixAdapter;

import java.util.List;

public class SquidBuild extends SoftMixAdapter {
    @Override
    public List<DockerBase> queryDocks() {
        dockerBases = Lists.newArrayList();
        dockerBases.add(new SquidSingleInstall());
        return dockerBases;
    }
}
