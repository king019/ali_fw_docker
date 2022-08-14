package com.k.docker.docker.amd.softmix.apache.hadoop.hbase.pseudo_distributed.hbase.image.create;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.apache.hadoop.hbase.pseudo_distributed.image.HbasePseudoDistributedDockerImage;
import com.k.docker.docker.amd.soft.apache.zookeeper.image.ZookeeperDockerImage;
import com.k.docker.docker.amd.softmix.apache.hadoop.hbase.pseudo_distributed.hbase.build.HbasePseudoDistributedBuild;
import com.k.docker.docker.amd.softmix.apache.hadoop.hbase.pseudo_distributed.zookeeper.build.HbasePseudoDistributedZookeeperBuild;
import com.k.docker.docker.base.docker.image.DockerImageBuildBase;
import com.k.docker.docker.base.docker.image.base.DockerImageBase;
import org.junit.Test;

import java.util.List;

public class HbasePseudoDistributedImageCreate extends DockerImageBase {

    @Test
    public void test() throws Exception {
        List<DockerImageBuildBase> list = Lists.newArrayList();
        list.add(new HbasePseudoDistributedDockerImage(HbasePseudoDistributedBuild.class));
        list.add(new ZookeeperDockerImage(HbasePseudoDistributedZookeeperBuild.class));
        buildList(list);
    }
}
