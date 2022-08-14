package com.k.docker.dockerhub.soft.common.github.database.cache;

import com.k.docker.dockerhub.soft.common.github.database.cache.memcache.CommonMemcachedHubDockerInstall;
import com.k.docker.dockerhub.soft.common.github.database.cache.redis.CommonRedisHubAll;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CommonRedisHubAll.class, CommonMemcachedHubDockerInstall.class})
public class CommonCacheHubAll {

}