package com.k.docker.dockerhub.soft.common.github.database.cache.redis.item;

import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;

import java.util.List;
import java.util.Map;

/**
 * https://hub.docker.com/r/arm32v7/redis
 */
public class CommonRedisInstall extends DockerhubCallBack {

    @Override
    public void writeDockerRuns(List<DockerRun> runs) {
        runs.add(build("redis"));
    }

    private DockerRun build(String name) {
        {
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.nameCmd(name);
            builder.dCmd(true);
            builder.version("redis");
            {
                builder.consumerCmdsAfter(List.of("--requirepass password"));
            }
            {
                builder.pCmdMap(Map.of("6379", "6379"));
            }

            builder.dRestart(true);
            return builder.build();
        }
    }
}
