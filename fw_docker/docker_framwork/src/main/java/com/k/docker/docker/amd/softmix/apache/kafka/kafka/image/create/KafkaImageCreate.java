package com.k.docker.docker.amd.softmix.apache.kafka.kafka.image.create;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.apache.kafka.cluster.image.KafkaDockerImage;
import com.k.docker.docker.amd.soft.apache.zookeeper.image.ZookeeperDockerImage;
import com.k.docker.docker.amd.softmix.apache.kafka.kafka.build.KafkaBuild;
import com.k.docker.docker.amd.softmix.apache.kafka.zookeeper.build.KafkaZookeeperBuild;
import com.k.docker.docker.base.docker.image.DockerImageBuildBase;
import com.k.docker.docker.base.docker.image.base.DockerImageBase;
import org.junit.Test;

import java.util.List;

public class KafkaImageCreate extends DockerImageBase {

    @Test
    public void test() throws Exception {
        final List<DockerImageBuildBase> list = Lists.newArrayList();
        list.add(new KafkaDockerImage(KafkaBuild.class));
        list.add(new ZookeeperDockerImage(KafkaZookeeperBuild.class));
        buildList(list);
    }
}
