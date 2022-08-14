package com.k.docker.docker.amd.softmix.alibaba.dubbo.dubbo.image.create;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.alibaba.dubbo.image.DubboDockerImage;
import com.k.docker.docker.amd.soft.apache.zookeeper.image.ZookeeperDockerImage;
import com.k.docker.docker.amd.soft.apache.zookeeperui.image.ZookeeperUIDockerImage;
import com.k.docker.docker.amd.softmix.alibaba.dubbo.dubbo.build.DubboBuild;
import com.k.docker.docker.amd.softmix.alibaba.dubbo.zookeeper.build.DubboZookeeperBuild;
import com.k.docker.docker.amd.softmix.alibaba.dubbo.zookeeperui.build.DubboZookeeperUIBuild;
import com.k.docker.docker.base.docker.image.DockerImageBuildBase;
import com.k.docker.docker.base.docker.image.base.DockerImageBase;
import org.junit.Test;

import java.util.List;

public class DubboImageCreate extends DockerImageBase {

    @Test
    public void test() throws Exception {
        List<DockerImageBuildBase> list = Lists.newArrayList();
        list.add(new DubboDockerImage(DubboBuild.class));
        list.add(new ZookeeperDockerImage(DubboZookeeperBuild.class));
        list.add(new ZookeeperUIDockerImage(DubboZookeeperUIBuild.class));
        buildList(list);
    }
}
