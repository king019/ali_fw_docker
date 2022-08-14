package com.k.docker.dockerhub.soft.common.special.source;

import com.k.docker.dockerhub.soft.common.special.source.alibaba.NacosSourceInstall;
import com.k.docker.dockerhub.soft.common.special.source.apache.ZookeeperSourceInstall;
import com.k.docker.dockerhub.soft.common.special.source.jdk.Dragonwell11SourceInstall;
import com.k.docker.dockerhub.soft.common.special.source.jdk.Dragonwell8SourceInstall;
import com.k.docker.dockerhub.soft.common.special.source.jdk.JdkSourceInstall;
import com.k.docker.dockerhub.soft.common.special.source.linux.KernelSourceInstall;
import com.k.docker.dockerhub.soft.common.special.source.mysql.MysqlSourceInstall;
import com.k.docker.dockerhub.soft.common.special.source.redis.RedisSourceInstall;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        NacosSourceInstall.class,
        ZookeeperSourceInstall.class,
        Dragonwell8SourceInstall.class,
        Dragonwell11SourceInstall.class,
        JdkSourceInstall.class,
        KernelSourceInstall.class,
        MysqlSourceInstall.class,
        RedisSourceInstall.class
})
public class SourceHubAll {

}