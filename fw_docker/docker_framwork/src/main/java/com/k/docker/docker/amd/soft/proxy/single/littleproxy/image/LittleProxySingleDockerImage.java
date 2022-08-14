package com.k.docker.docker.amd.soft.proxy.single.littleproxy.image;

import com.k.docker.docker.base.docker.image.DockerImageBuildBase;
import com.k.docker.docker.util.docker.DockerVersion;

import java.io.File;

public class LittleProxySingleDockerImage extends DockerImageBuildBase {
    public LittleProxySingleDockerImage(final Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void buildImage(final File file) throws Exception {
        dockerBuild(file, DockerVersion.littleProxyVersion);
    }
}
