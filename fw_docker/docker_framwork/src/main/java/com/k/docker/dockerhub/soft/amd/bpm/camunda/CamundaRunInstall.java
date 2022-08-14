package com.k.docker.dockerhub.soft.amd.bpm.camunda;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;

import java.util.List;
import java.util.Map;

/**
 * demo:demo
 */
public class CamundaRunInstall extends DockerhubCallBack {
    @Override
    public void writeDockerRuns(List<DockerRun> runs) {
        String name = "camundaTomcat";
        String version = "camunda/camunda-bpm-platform:run-latest";
        {
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.nameCmd(name);
            builder.dCmd(true);
            builder.version(version);
            {
                Map<String, String> pCmdMap = Maps.newHashMap();
                pCmdMap.put("9901", "8080");
                builder.pCmdMap(pCmdMap);
            }
            builder.dRestart(true);
            runs.add(builder.build());
        }
    }
}
