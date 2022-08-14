package com.k.docker.dockerhub.soft.common.special.frame.gc;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.docker.util.props.PropsContainerUtil;
import com.k.docker.docker.util.props.PropsDockerUtil;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;
import com.k.docker.dockerhub.base.enums.DockerRegionEnum;

import java.util.List;
import java.util.Map;

import static com.k.docker.docker.util.props.PropsDockerUtil.dockerAbsDataDir;

public class SpringFrameGcDockerInstall extends DockerhubCallBack {
    @Override
    public void buildRegion(DockerRegionEnum region, List<DockerRun> runs) {
        String version = "king019/frame:gc";
        String name = "framegc";
        buildModel(name, DockerRegionEnum.getVersion(region, version), runs);
    }

    public void buildModel(String name, String version, List<DockerRun> runs) {
        {
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.nameCmd(name);
            builder.dCmd(true);
            builder.version(version);
            {
                Map<String, String> vCmdMap = Maps.newHashMap();
                vCmdMap.put(PropsDockerUtil.dockerAbsDataDir + name, PropsContainerUtil.rootSoftDir);
                vCmdMap.put(PropsDockerUtil.dockerAbsDataDir + "maven/m2", PropsContainerUtil.rootM2Dir);
                builder.vCmdMap(vCmdMap);
            }
            {
                Map<String, String> pCmdMap = Maps.newHashMap();
                pCmdMap.put("10011", "10011");
                pCmdMap.put("10012", "10012");pCmdMap.put("10001", "10001");
                pCmdMap.put("10002", "10002");
                builder.pCmdMap(pCmdMap);
            }
            builder.dRestart(true);
            runs.add(builder.build());
        }
    }
}
