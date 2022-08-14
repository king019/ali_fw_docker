package com.k.docker.dockerhub.soft.common.github.apache.zookeeper.item;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.docker.util.props.PropsContainerUtil;
import com.k.docker.docker.util.props.PropsDockerUtil;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;

import java.util.List;
import java.util.Map;

/**
 * https://hub.docker.com/_/zookeeper
 * https://archive.apache.org/dist/zookeeper/current/apache-zookeeper-3.5.6.tar.gz
 */
public class CommonZookeeperInstall extends DockerhubCallBack {
    @Override
    public void writeDockerRuns(List<DockerRun> runs) {
        //String version = "zookeeper:3.5.4-beta";
        String version = "zookeeper:3.7";
        String name = "zookeeper";
        {
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.nameCmd(name);
            builder.dCmd(true);
            builder.version(version);
            {
                Map<String, String> vCmdMap = Maps.newHashMap();
                vCmdMap.put(PropsDockerUtil.dockerAbsDataDir + name, PropsContainerUtil.rootSoftDir);
                builder.vCmdMap(vCmdMap);
            }
            {
                Map<String, String> pCmdMap = Maps.newHashMap();
                pCmdMap.put("2181", "2181");
//                pCmdMap.put("2888", "2888");
//                pCmdMap.put("3888", "3888");
//                pCmdMap.put("9010", "9090");
                builder.pCmdMap(pCmdMap);
            }
            builder.dRestart(true);
            runs.add(builder.build());
        }
    }
}
