package com.k.docker.docker.amd.soft.alibaba.tair.image;

import com.k.docker.docker.base.docker.image.DockerImageBuildBase;

import java.io.File;

public class TairDockerImage extends DockerImageBuildBase {
    public TairDockerImage(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void buildImage(File file) throws Exception {
        dockerBuild(file, "tair:1");
    }
}
