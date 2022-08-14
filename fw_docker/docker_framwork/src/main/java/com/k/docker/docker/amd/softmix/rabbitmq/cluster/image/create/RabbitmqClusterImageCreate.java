package com.k.docker.docker.amd.softmix.rabbitmq.cluster.image.create;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.rabbitmq.cluster.image.RabbitmqClusterDockerImage;
import com.k.docker.docker.amd.softmix.rabbitmq.cluster.build.RabbitmqClusterBuild;
import com.k.docker.docker.base.docker.image.DockerImageBuildBase;
import com.k.docker.docker.base.docker.image.base.DockerImageBase;
import org.junit.Test;

import java.util.List;

public class RabbitmqClusterImageCreate extends DockerImageBase {

    @Test
    public void test() throws Exception {
        List<DockerImageBuildBase> list = Lists.newArrayList();
        list.add(new RabbitmqClusterDockerImage(RabbitmqClusterBuild.class));
        buildList(list);
    }
}
