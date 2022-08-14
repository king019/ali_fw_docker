package com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.single.hadoop.image.create;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.apache.hadoop.hadoop.single.image.HadoopSingleDockerImage;
import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.single.hadoop.build.HadoopSingleBuild;
import com.k.docker.docker.base.docker.image.DockerImageBuildBase;
import com.k.docker.docker.base.docker.image.base.DockerImageBase;
import org.junit.Test;

import java.util.List;

public class HadoopSingleImageCreate extends DockerImageBase {

    @Test
    public void test() throws Exception {
        List<DockerImageBuildBase> list = Lists.newArrayList();
        list.add(new HadoopSingleDockerImage(HadoopSingleBuild.class));
        buildList(list);
    }
}
