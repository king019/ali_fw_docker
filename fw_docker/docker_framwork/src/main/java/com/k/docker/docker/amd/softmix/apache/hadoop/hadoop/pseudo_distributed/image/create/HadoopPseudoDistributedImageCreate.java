package com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.pseudo_distributed.image.create;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.apache.hadoop.hadoop.pseudo_distributed.image.HadooPseudoDistributedDockerImage;
import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.pseudo_distributed.build.HadoopPseudoDistributedBuild;
import com.k.docker.docker.base.docker.image.DockerImageBuildBase;
import com.k.docker.docker.base.docker.image.base.DockerImageBase;
import org.junit.Test;

import java.util.List;

public class HadoopPseudoDistributedImageCreate extends DockerImageBase {

    @Test
    public void test() throws Exception {
        List<DockerImageBuildBase> list = Lists.newArrayList();
        list.add(new HadooPseudoDistributedDockerImage(HadoopPseudoDistributedBuild.class));
        buildList(list);
    }
}
