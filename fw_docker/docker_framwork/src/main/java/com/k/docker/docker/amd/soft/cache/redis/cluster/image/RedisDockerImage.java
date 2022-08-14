package com.k.docker.docker.amd.soft.cache.redis.cluster.image;

import com.k.docker.docker.base.docker.image.DockerImageBuildBase;

import java.io.File;

public class RedisDockerImage extends DockerImageBuildBase {
    public RedisDockerImage(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void buildImage(File file) throws Exception {
        dockerBuild(file, "redis:cluster");
    }
}
