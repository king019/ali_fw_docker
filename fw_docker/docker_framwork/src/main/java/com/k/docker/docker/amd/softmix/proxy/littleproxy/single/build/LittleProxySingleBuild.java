package com.k.docker.docker.amd.softmix.proxy.littleproxy.single.build;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.proxy.single.littleproxy.install.LittleProxySingleInstall;
import com.k.docker.docker.base.soft.base.DockerBase;
import com.k.docker.docker.base.softmix.adapter.SoftMixAdapter;

import java.util.List;

public class LittleProxySingleBuild extends SoftMixAdapter {
    @Override
    public List<DockerBase> queryDocks() {
        dockerBases = Lists.newArrayList();
        dockerBases.add(new LittleProxySingleInstall());
        return dockerBases;
    }
}
