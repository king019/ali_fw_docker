package com.k.docker.docker.amd.softmix.proxy.squid.single.image.create;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.proxy.squid.single.image.SquidSingleDockerImage;
import com.k.docker.docker.amd.softmix.proxy.squid.single.build.SquidBuild;
import com.k.docker.docker.base.docker.image.DockerImageBuildBase;
import com.k.docker.docker.base.docker.image.base.DockerImageBase;
import org.junit.Test;

import java.util.List;

public class SquidImageCreate extends DockerImageBase {

    @Test
    public void test() throws Exception {
        List<DockerImageBuildBase> list = Lists.newArrayList();
        list.add(new SquidSingleDockerImage(SquidBuild.class));
        buildList(list);
    }
}
