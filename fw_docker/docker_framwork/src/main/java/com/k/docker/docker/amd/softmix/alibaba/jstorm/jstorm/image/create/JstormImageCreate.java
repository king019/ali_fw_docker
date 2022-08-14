package com.k.docker.docker.amd.softmix.alibaba.jstorm.jstorm.image.create;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.alibaba.jstorm.image.JstormDockerImage;
import com.k.docker.docker.amd.soft.apache.zookeeper.image.ZookeeperDockerImage;
import com.k.docker.docker.amd.softmix.alibaba.jstorm.jstorm.build.JstromBuild;
import com.k.docker.docker.amd.softmix.alibaba.jstorm.zookeeper.build.JstormZookeeperBuild;
import com.k.docker.docker.base.docker.image.DockerImageBuildBase;
import com.k.docker.docker.base.docker.image.base.DockerImageBase;
import org.junit.Test;

import java.util.List;

public class JstormImageCreate extends DockerImageBase {

    @Test
    public void test() throws Exception {
        final List<DockerImageBuildBase> list = Lists.newArrayList();
        list.add(new JstormDockerImage(JstromBuild.class));
        list.add(new ZookeeperDockerImage(JstormZookeeperBuild.class));
        buildList(list);
    }
}
