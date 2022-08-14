package com.k.docker.docker.amd.softmix.alibaba.mq.rocketmq.cluster.image.create;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.alibaba.mq.rocketmq.cluster.image.RocketmqClusterImage;
import com.k.docker.docker.amd.softmix.alibaba.mq.rocketmq.cluster.build.RocketMqClusterBuild;
import com.k.docker.docker.base.docker.image.DockerImageBuildBase;
import com.k.docker.docker.base.docker.image.base.DockerImageBase;
import org.junit.Test;

import java.util.List;

public class RocketMqClusterImageCreate extends DockerImageBase {

    @Test
    public void test() throws Exception {
        List<DockerImageBuildBase> list = Lists.newArrayList();
        list.add(new RocketmqClusterImage(RocketMqClusterBuild.class));
        buildList(list);
    }
}
