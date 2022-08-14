package com.k.docker.dockerhub.soft.common.special.source.apache;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.docker.util.props.PropsContainerUtil;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;
import com.k.docker.dockerhub.base.enums.DockerRegionEnum;

import java.util.List;
import java.util.Map;

import static com.k.docker.docker.util.props.PropsDockerUtil.dockerAbsDataDir;

public class ZookeeperSourceInstall extends DockerhubCallBack {

    @Override
    public void buildRegion(DockerRegionEnum region, List<DockerRun> runs) {
        String name = "sourceZookeeper";
        String version = "king019/source:zookeeper";
        buildModel(name, region, DockerRegionEnum.getVersion(region, version), runs);
    }

    public void buildModel(String name, DockerRegionEnum region, String version, List<DockerRun> runs) {

        {
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.nameCmd(name);
            builder.dCmd(true);
            builder.version(version);
            builder.notes(List.of(region.getRegion()));
            builder.dRestart(true);
            {
                Map<String, String> vCmdMap = Maps.newHashMap();
                vCmdMap.put(dockerAbsDataDir + name, PropsContainerUtil.rootSoftDir);
                builder.vCmdMap(vCmdMap);
            }
            runs.add(builder.build());
        }
    }
}
