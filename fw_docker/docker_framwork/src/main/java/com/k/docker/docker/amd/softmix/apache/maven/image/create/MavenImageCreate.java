package com.k.docker.docker.amd.softmix.apache.maven.image.create;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.apache.maven.single.image.MavenDockerImage;
import com.k.docker.docker.amd.softmix.apache.maven.build.MavenBuild;
import com.k.docker.docker.base.docker.image.DockerImageBuildBase;
import com.k.docker.docker.base.docker.image.base.DockerImageBase;
import org.junit.Test;

import java.util.List;

public class MavenImageCreate extends DockerImageBase {

    @Test
    public void test() throws Exception {
        final List<DockerImageBuildBase> list = Lists.newArrayList();
        list.add(new MavenDockerImage(MavenBuild.class));
        buildList(list);
    }
}
