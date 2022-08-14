package com.k.docker.docker.amd.softmix.cache.redis.cluster.redis.image.create;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.cache.redis.cluster.image.RedisDockerImage;
import com.k.docker.docker.amd.softmix.cache.redis.cluster.redis.build.RedisClusterBuild;
import com.k.docker.docker.base.docker.image.DockerImageBuildBase;
import com.k.docker.docker.base.docker.image.base.DockerImageBase;
import org.junit.Test;

import java.util.List;

public class RedisClusterImageCreate extends DockerImageBase {

    @Test
    public void test() throws Exception {
        List<DockerImageBuildBase> list = Lists.newArrayList();
        list.add(new RedisDockerImage(RedisClusterBuild.class));
        buildList(list);
    }
}
