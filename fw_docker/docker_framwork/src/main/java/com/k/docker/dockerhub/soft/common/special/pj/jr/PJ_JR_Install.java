package com.k.docker.dockerhub.soft.common.special.pj.jr;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;
import com.k.docker.dockerhub.base.enums.DockerRegionEnum;

import java.util.List;
import java.util.Map;

public class PJ_JR_Install extends DockerhubCallBack {
    @Override
    public void buildRegion(DockerRegionEnum region, List<DockerRun> runs) {
        String name = "pjjr";
        String version = "king019/jr";
        writeDockerRuns(name, DockerRegionEnum.getVersion(region, version), runs);
    }

    public void writeDockerRuns(String name, String version, List<DockerRun> runs) {
        {
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.nameCmd(name);
            builder.dCmd(true);
            builder.version(version);
            {
                Map<String, String> vCmdMap = Maps.newHashMap();
                builder.vCmdMap(vCmdMap);
            }
            {
                Map<String, String> pCmdMap = Maps.newHashMap();
                pCmdMap.put("2999", "2999");
                builder.pCmdMap(pCmdMap);
            }
            {
                Map<String, String> eCmdMap = Maps.newHashMap();
                eCmdMap.put("PORT", "2999");
                builder.eMap(eCmdMap);
            }
            builder.dRestart(true);
            runs.add(builder.build());
        }
    }
}
