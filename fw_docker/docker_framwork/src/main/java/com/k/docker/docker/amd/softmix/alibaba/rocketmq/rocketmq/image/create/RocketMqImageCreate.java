package com.k.docker.docker.amd.softmix.alibaba.rocketmq.rocketmq.image.create;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.alibaba.rocketmq.image.RocketmqDockerImage;
import com.k.docker.docker.amd.softmix.alibaba.rocketmq.rocketmq.build.RocketMqBuild;
import com.k.docker.docker.base.docker.image.DockerImageBuildBase;
import com.k.docker.docker.base.docker.image.base.DockerImageBase;
import org.junit.Test;

import java.util.List;

public class RocketMqImageCreate extends DockerImageBase {

    @Test
    public void test() throws Exception {
        final List<DockerImageBuildBase> list = Lists.newArrayList();
        list.add(new RocketmqDockerImage(RocketMqBuild.class));
        buildList(list);
    }
}
