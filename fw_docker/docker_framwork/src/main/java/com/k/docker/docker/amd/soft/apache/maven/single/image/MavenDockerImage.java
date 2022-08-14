package com.k.docker.docker.amd.soft.apache.maven.single.image;

import com.k.docker.docker.base.docker.image.DockerImageBuildBase;
import com.k.docker.docker.util.docker.DockerVersion;

import java.io.File;

public class MavenDockerImage extends DockerImageBuildBase {
    public MavenDockerImage(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void buildImage(File file) throws Exception {
        dockerBuild(file, DockerVersion.hbaseSingleVersion);
    }
}
