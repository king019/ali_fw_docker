package com.k.docker.docker.amd.softmix.cache.redis.single.redis;

import com.k.docker.docker.amd.softmix.cache.redis.single.redis.build.RedisSingleBuild;
import com.k.docker.docker.amd.softmix.cache.redis.single.redis.image.create.RedisSingleImageCreate;
import com.k.docker.docker.amd.softmix.cache.redis.single.redis.run.RedisSingleRun;
import com.k.docker.docker.amd.softmix.cache.redis.single.redis.scan.RedisSingleScan;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({RedisSingleBuild.class, RedisSingleImageCreate.class, RedisSingleRun.class, RedisSingleScan.class})
public class RedisSingleAll {

}
