package com.k.docker.dockerhub.soft.common.special.tools;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;
import com.k.docker.dockerhub.base.enums.DockerRegionEnum;

import java.util.List;
import java.util.Map;

public class CommonSyncInstall extends DockerhubCallBack {

    @Override
    public void buildRegion(DockerRegionEnum region, List<DockerRun> runs) {
        String version = "king019/tunasync";
        String name = "tunasync";
        buildModel(name, version, region, runs);
    }

    public void buildModel(String name, String version, DockerRegionEnum region, List<DockerRun> runs) {
        {
            version = DockerRegionEnum.getVersion(region, version);
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.nameCmd(name);
            builder.dCmd(true);
            builder.version(version);
            {
                Map<String, String> vCmdMap = Maps.newHashMap();
                //vCmdMap.put(dockerAbsDataDir + name, "/usr/share/nginx/html/data");
                builder.vCmdMap(vCmdMap);
            }
            {
                Map<String, String> pCmdMap = Maps.newHashMap();
                pCmdMap.put("12345", "12345");
                pCmdMap.put("6000", "6000");
                builder.pCmdMap(pCmdMap);
            }
            builder.dRestart(true);
            runs.add(builder.build());
        }
    }
}
