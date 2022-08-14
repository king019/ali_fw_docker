package com.k.docker.dockerhub.soft.common.github.tool.zipkin;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;

import java.util.List;
import java.util.Map;

public class CommonZipkinInstall extends DockerhubCallBack {
    @Override
    public void writeDockerRuns(List<DockerRun> runs) {
        String version = "openzipkin/zipkin";
        String name = "zipkin";
        buildModel(name, version, runs);
    }


    public void buildModel(String name, String version, List<DockerRun> runs) {

        {
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.mMem(true);
            builder.nameCmd(name);
            builder.dCmd(true);
            builder.version(version);
            {
                Map<String, String> vCmdMap = Maps.newHashMap();
                builder.vCmdMap(vCmdMap);
            }
            {
                Map<String, String> pCmdMap = Maps.newHashMap();
                pCmdMap.put("" + 9411, "9411");
                builder.pCmdMap(pCmdMap);
            }
            builder.dRestart(true);
            runs.add(builder.build());
        }
    }
}
