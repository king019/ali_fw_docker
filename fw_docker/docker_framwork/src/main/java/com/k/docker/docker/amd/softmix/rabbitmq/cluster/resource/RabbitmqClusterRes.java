package com.k.docker.docker.amd.softmix.rabbitmq.cluster.resource;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.rabbitmq.single.image.RabbitmqSingleDockerImage;
import com.k.docker.docker.amd.softmix.rabbitmq.cluster.build.RabbitmqClusterBuild;
import com.k.docker.docker.base.docker.image.DockerImageBuildBase;
import com.k.docker.docker.base.docker.image.base.DockerImageBase;
import org.junit.Test;

import java.util.List;

public class RabbitmqClusterRes extends DockerImageBase {

    @Test
    public void test() throws Exception {
        final List<DockerImageBuildBase> list = Lists.newArrayList();
        list.add(new RabbitmqSingleDockerImage(RabbitmqClusterBuild.class));
        buildList(list);
    }
}
