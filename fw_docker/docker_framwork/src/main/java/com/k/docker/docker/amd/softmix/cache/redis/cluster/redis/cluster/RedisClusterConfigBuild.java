package com.k.docker.docker.amd.softmix.cache.redis.cluster.redis.cluster;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.docker.install.base.InstallFile;
import com.k.docker.docker.amd.softmix.cache.redis.cluster.redis.config.host.RedisClusterHost;
import com.k.docker.docker.util.file.FileWriteUtil;
import com.k.docker.docker.util.path.FWPathUtil;
import com.k.docker.docker.util.props.PropsDockerUtil;
import org.junit.Test;

import java.util.List;

public class RedisClusterConfigBuild extends InstallFile {

    @Override
    public void bf() {
        shPath = FWPathUtil.getTargetPath(getClass()) + "/docker_redis_cluster.sh";
        shFile = FileWriteUtil.getFile(shPath);
    }

    public List<String> hostIps() {
        RedisClusterHost dockerHost = new RedisClusterHost();
        return dockerHost.docker_all();
    }

    @Test
    public void test() throws Exception {
        String redisCluster = "./redis-trib.rb create --replicas 1 ";
        List<String> hostIps = hostIps();
        List<String> lines = Lists.newArrayList();
        lines.add("gem install redis   ");
        lines.add("cd " + PropsDockerUtil.dockerDir + "redis/src");
        for (String host : hostIps) {
            redisCluster += host + ":" + host.replace("rdcu", "") + "  ";
        }
        lines.add(redisCluster);
        FileWriteUtil.write(shFile, lines);
    }
}
