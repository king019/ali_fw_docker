package com.k.docker.docker.amd.soft.apache.hadoop.hadoop.distributed.image;

import com.k.docker.docker.base.docker.image.DockerImageBuildBase;
import com.k.docker.docker.util.docker.DockerVersion;

import java.io.File;

public class HadoopDistributedDockerImage extends DockerImageBuildBase {
    public HadoopDistributedDockerImage(final Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void buildImage(final File file) throws Exception {
        dockerBuild(file, DockerVersion.hadoopDistributedVersion);
    }
}
