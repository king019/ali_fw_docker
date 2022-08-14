package com.k.docker.dockerhub.soft.common.github.apache.zookeeper.item;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;

import java.util.List;
import java.util.Map;

public class CommonZookeeperZKUIInstall extends DockerhubCallBack {

    @Override
    public void writeDockerRuns(List<DockerRun> runs) {
        {
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.nameCmd("zkui");
            builder.dCmd(true);
            builder.version("king019/zookeeper:zkui");

            {
                Map<String, String> pCmdMap = Maps.newHashMap();
                pCmdMap.put("9090", "9090");
                builder.pCmdMap(pCmdMap);
            }
            builder.dRestart(true);
            runs.add(builder.build());
        }
    }

}
