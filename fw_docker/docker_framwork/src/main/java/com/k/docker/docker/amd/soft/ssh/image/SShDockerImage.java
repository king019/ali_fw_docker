package com.k.docker.docker.amd.soft.ssh.image;

import com.k.docker.docker.base.docker.image.DockerImageBuildBase;

import java.io.File;

public class SShDockerImage extends DockerImageBuildBase {
    public SShDockerImage(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void buildImage(File file) throws Exception {
        dockerBuild(file, "ssh:dis");
    }
}
