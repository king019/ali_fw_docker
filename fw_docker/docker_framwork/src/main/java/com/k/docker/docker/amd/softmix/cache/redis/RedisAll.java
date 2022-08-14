package com.k.docker.docker.amd.softmix.cache.redis;

import com.k.docker.docker.amd.softmix.cache.redis.cluster.redis.RedisClusterAll;
import com.k.docker.docker.amd.softmix.cache.redis.single.redis.RedisSingleAll;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({RedisClusterAll.class, RedisSingleAll.class})
public class RedisAll {

}
