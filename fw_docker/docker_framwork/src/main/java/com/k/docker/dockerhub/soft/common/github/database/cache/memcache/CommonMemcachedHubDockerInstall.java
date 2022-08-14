package com.k.docker.dockerhub.soft.common.github.database.cache.memcache;

import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;

import java.util.List;

//https://hub.docker.com/_/memcached/
public class CommonMemcachedHubDockerInstall extends DockerhubCallBack {

    @Override
    public void writeDockerRuns(List<DockerRun> runs) {
        runs.add(build("memcached1"));
        runs.add(build("memcached2"));
        runs.add(build("memcached3"));
        runs.add(build("memcached4"));
    }

    private DockerRun build(String name) {
        {
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.nameCmd(name);
            builder.dCmd(true);
            builder.version("memcached:1.4");
            builder.dRestart(true);
            return builder.build();
        }
    }
}
