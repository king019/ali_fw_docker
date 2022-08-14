package com.k.docker.docker.amd.softmix.proxy.littleproxy.single.image.create;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.proxy.single.littleproxy.image.LittleProxySingleDockerImage;
import com.k.docker.docker.amd.softmix.proxy.littleproxy.single.build.LittleProxySingleBuild;
import com.k.docker.docker.base.docker.image.DockerImageBuildBase;
import com.k.docker.docker.base.docker.image.base.DockerImageBase;
import org.junit.Test;

import java.util.List;

public class LittleProxySingleImageCreate extends DockerImageBase {

    @Test
    public void test() throws Exception {
        List<DockerImageBuildBase> list = Lists.newArrayList();
        list.add(new LittleProxySingleDockerImage(LittleProxySingleBuild.class));
        buildList(list);
    }
}
