package com.k.docker.docker.amd.softmix.cache.redis.single.redis.build;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.cache.redis.single.install.RedisSingleInstall;
import com.k.docker.docker.base.soft.base.DockerBase;
import com.k.docker.docker.base.softmix.adapter.SoftMixAdapter;

import java.util.List;

public class RedisSingleBuild extends SoftMixAdapter {
    @Override
    public List<DockerBase> queryDocks() {
        dockerBases = Lists.newArrayList();
        dockerBases.add(new RedisSingleInstall());
        return dockerBases;
    }
}
