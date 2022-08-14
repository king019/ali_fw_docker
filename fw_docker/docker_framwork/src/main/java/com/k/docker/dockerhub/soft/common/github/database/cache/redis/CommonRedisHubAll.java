package com.k.docker.dockerhub.soft.common.github.database.cache.redis;

import com.k.docker.dockerhub.soft.common.github.database.cache.redis.item.CommonRedisInstall;
import com.k.docker.dockerhub.soft.common.special.database.cache.redis.item.CommonRedisClusterInstall;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CommonRedisClusterInstall.class, CommonRedisInstall.class})
public class CommonRedisHubAll {

}
