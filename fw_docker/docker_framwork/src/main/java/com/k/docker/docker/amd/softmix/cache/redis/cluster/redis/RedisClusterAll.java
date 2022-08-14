package com.k.docker.docker.amd.softmix.cache.redis.cluster.redis;

import com.k.docker.docker.amd.softmix.cache.redis.cluster.redis.build.RedisClusterBuild;
import com.k.docker.docker.amd.softmix.cache.redis.cluster.redis.cluster.RedisClusterConfigBuild;
import com.k.docker.docker.amd.softmix.cache.redis.cluster.redis.image.create.RedisClusterImageCreate;
import com.k.docker.docker.amd.softmix.cache.redis.cluster.redis.run.RedisClusterRun;
import com.k.docker.docker.amd.softmix.cache.redis.cluster.redis.runsingle.RedisClusterSingleRun;
import com.k.docker.docker.amd.softmix.cache.redis.cluster.redis.scan.RedisClusterScan;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({RedisClusterBuild.class, RedisClusterImageCreate.class, RedisClusterRun.class, RedisClusterScan.class, RedisClusterConfigBuild.class, RedisClusterSingleRun.class})
public class RedisClusterAll {

}
