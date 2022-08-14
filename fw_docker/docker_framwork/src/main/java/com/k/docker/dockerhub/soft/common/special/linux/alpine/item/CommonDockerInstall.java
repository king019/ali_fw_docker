package com.k.docker.dockerhub.soft.common.special.linux.alpine.item;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.docker.util.props.PropsContainerUtil;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;
import com.k.docker.dockerhub.base.enums.DockerRegionEnum;

import java.util.List;
import java.util.Map;

import static com.k.docker.docker.util.props.PropsDockerUtil.dockerAbsDataDir;

public class CommonDockerInstall extends DockerhubCallBack {
    @Override
    public void buildRegion(DockerRegionEnum region, List<DockerRun> runs) {
        String version = "king019/docker";
        String name = "alpinedocker";
        buildModel(name, DockerRegionEnum.getVersion(region, version), runs);
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
                vCmdMap.put(dockerAbsDataDir + name, PropsContainerUtil.rootSoftDir);
                vCmdMap.put(dockerAbsDataDir + "maven/m2", PropsContainerUtil.rootM2Dir);
                vCmdMap.put("/var/run/docker.sock", "/var/run/docker.sock");
                builder.vCmdMap(vCmdMap);
            }
            builder.dRestart(true);
            runs.add(builder.build());
        }
    }
}
