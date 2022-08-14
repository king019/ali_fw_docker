package com.k.docker.docker.amd.softmix.server.spring.boot.image.create;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.server.spring.boot.show.image.ServeBootDockerImage;
import com.k.docker.docker.amd.softmix.server.spring.boot.build.ServerBootBuild;
import com.k.docker.docker.base.docker.image.DockerImageBuildBase;
import com.k.docker.docker.base.docker.image.base.DockerImageBase;
import org.junit.Test;

import java.util.List;

public class ServerBootImageCreate extends DockerImageBase {

    @Test
    public void test() throws Exception {
        List<DockerImageBuildBase> list = Lists.newArrayList();
        list.add(new ServeBootDockerImage(ServerBootBuild.class));
        buildList(list);
    }
}
