package com.k.docker.docker.amd.softmix.apache.hadoop.zookeeper.zookeeper.image.create;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.apache.zookeeper.image.ZookeeperDockerImage;
import com.k.docker.docker.amd.soft.apache.zookeeperui.image.ZookeeperUIDockerImage;
import com.k.docker.docker.amd.softmix.apache.hadoop.zookeeper.zookeeper.build.ZookeeperBuild;
import com.k.docker.docker.amd.softmix.apache.hadoop.zookeeper.zookeeperui.build.ZookeeperUIBuild;
import com.k.docker.docker.base.docker.image.DockerImageBuildBase;
import com.k.docker.docker.base.docker.image.base.DockerImageBase;
import org.junit.Test;

import java.util.List;

public class ZookeeperImageCreate extends DockerImageBase {

    @Test
    public void test() throws Exception {
        List<DockerImageBuildBase> list = Lists.newArrayList();
        list.add(new ZookeeperDockerImage(ZookeeperBuild.class));
        list.add(new ZookeeperUIDockerImage(ZookeeperUIBuild.class));
        buildList(list);
    }
}
