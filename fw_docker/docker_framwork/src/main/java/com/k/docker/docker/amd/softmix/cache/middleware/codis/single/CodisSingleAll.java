package com.k.docker.docker.amd.softmix.cache.middleware.codis.single;

import com.k.docker.docker.amd.softmix.cache.middleware.codis.single.build.CodisSingleBuild;
import com.k.docker.docker.amd.softmix.cache.middleware.codis.single.image.create.CodisSingleImageCreate;
import com.k.docker.docker.amd.softmix.cache.middleware.codis.single.run.CodisSingleRun;
import com.k.docker.docker.amd.softmix.cache.middleware.codis.single.scan.CodisSingleScan;
import com.k.docker.docker.amd.softmix.cache.redis.cluster.redis.cluster.RedisClusterConfigBuild;
import com.k.docker.docker.amd.softmix.cache.redis.cluster.redis.runsingle.RedisClusterSingleRun;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CodisSingleBuild.class, CodisSingleImageCreate.class, CodisSingleRun.class, CodisSingleScan.class, RedisClusterConfigBuild.class, RedisClusterSingleRun.class})
public class CodisSingleAll {

}
