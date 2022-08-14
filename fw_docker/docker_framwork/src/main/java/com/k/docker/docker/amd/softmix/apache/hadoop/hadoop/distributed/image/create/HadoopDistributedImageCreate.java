package com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.distributed.image.create;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.apache.hadoop.hadoop.distributed.image.HadoopDistributedDockerImage;
import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.distributed.build.HadoopDistributedBuild;
import com.k.docker.docker.base.docker.image.DockerImageBuildBase;
import com.k.docker.docker.base.docker.image.base.DockerImageBase;
import org.junit.Test;

import java.util.List;

public class HadoopDistributedImageCreate extends DockerImageBase {

    @Test
    public void test() throws Exception {
        List<DockerImageBuildBase> list = Lists.newArrayList();
        list.add(new HadoopDistributedDockerImage(HadoopDistributedBuild.class));
        buildList(list);
    }
}
