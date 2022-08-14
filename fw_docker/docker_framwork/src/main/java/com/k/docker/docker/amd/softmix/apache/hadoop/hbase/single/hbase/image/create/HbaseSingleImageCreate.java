package com.k.docker.docker.amd.softmix.apache.hadoop.hbase.single.hbase.image.create;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.apache.hadoop.hbase.single.image.HbaseSingleDockerImage;
import com.k.docker.docker.amd.soft.apache.zookeeper.image.ZookeeperDockerImage;
import com.k.docker.docker.amd.softmix.apache.hadoop.hbase.single.hbase.build.HbaseSingleBuild;
import com.k.docker.docker.amd.softmix.apache.hadoop.hbase.single.zookeeper.build.HbaseZookeeperBuild;
import com.k.docker.docker.base.docker.image.DockerImageBuildBase;
import com.k.docker.docker.base.docker.image.base.DockerImageBase;
import org.junit.Test;

import java.util.List;

public class HbaseSingleImageCreate extends DockerImageBase {

    @Test
    public void test() throws Exception {
        List<DockerImageBuildBase> list = Lists.newArrayList();
        list.add(new HbaseSingleDockerImage(HbaseSingleBuild.class));
        list.add(new ZookeeperDockerImage(HbaseZookeeperBuild.class));
        buildList(list);
    }
}
