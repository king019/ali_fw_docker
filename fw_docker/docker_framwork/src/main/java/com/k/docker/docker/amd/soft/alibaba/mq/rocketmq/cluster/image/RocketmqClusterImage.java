package com.k.docker.docker.amd.soft.alibaba.mq.rocketmq.cluster.image;

import com.k.docker.docker.base.docker.image.DockerImageBuildBase;
import com.k.docker.docker.util.docker.DockerVersion;

import java.io.File;

public class RocketmqClusterImage extends DockerImageBuildBase {
    public RocketmqClusterImage(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void buildImage(File file) throws Exception {
        dockerBuild(file, DockerVersion.rockmqSingleVersion);
    }
}
