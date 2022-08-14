package com.k.docker.docker.amd.softmix.cache.redis.single.redis.image.create;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.cache.redis.single.image.RedisSingleDockerImage;
import com.k.docker.docker.amd.softmix.cache.redis.single.redis.build.RedisSingleBuild;
import com.k.docker.docker.base.docker.image.DockerImageBuildBase;
import com.k.docker.docker.base.docker.image.base.DockerImageBase;
import org.junit.Test;

import java.util.List;

public class RedisSingleImageCreate extends DockerImageBase {

    @Test
    public void test() throws Exception {
        List<DockerImageBuildBase> list = Lists.newArrayList();
        list.add(new RedisSingleDockerImage(RedisSingleBuild.class));
        buildList(list);
    }
}
