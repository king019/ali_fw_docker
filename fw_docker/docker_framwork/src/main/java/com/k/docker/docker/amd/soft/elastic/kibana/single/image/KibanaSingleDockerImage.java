package com.k.docker.docker.amd.soft.elastic.kibana.single.image;

import com.k.docker.docker.base.docker.image.DockerImageBuildBase;
import com.k.docker.docker.util.docker.DockerVersion;

import java.io.File;

public class KibanaSingleDockerImage extends DockerImageBuildBase {
    public KibanaSingleDockerImage(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void buildImage(File file) throws Exception {
        dockerBuild(file, DockerVersion.kibanaSingleVersion);
    }
}
