package com.k.docker.dockerhub.soft.common.special.alibaba.arthas.item;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.docker.util.props.PropsContainerUtil;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;
import com.k.docker.dockerhub.base.enums.DockerRegionEnum;

import java.util.List;
import java.util.Map;

import static com.k.docker.docker.util.props.PropsDockerUtil.dockerAbsDataDir;

public class CommonArthasInstall extends DockerhubCallBack {
    @Override
    public void buildRegion(DockerRegionEnum region, List<DockerRun> runs) {
        String name = "arthas";
        String version = "king019/arthas";
        buildModel(name, version, region, runs);
    }

    public void buildModel(String name, String version, DockerRegionEnum region, List<DockerRun> runs) {
        version = DockerRegionEnum.getVersion(region, version);
        {
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.region(region);
            builder.nameCmd(name);
            builder.dCmd(true);
            builder.version(version);
            {
                Map<String, String> vCmdMap = Maps.newHashMap();
                vCmdMap.put(dockerAbsDataDir + name, PropsContainerUtil.rootSoftDir);
                vCmdMap.put(dockerAbsDataDir + "maven/m2", PropsContainerUtil.rootM2Dir);
                builder.vCmdMap(vCmdMap);
            }
            {
                Map<String, String> pCmdMap = Maps.newHashMap();
                pCmdMap.put("6102", "3658");
                builder.pCmdMap(pCmdMap);
            }
//            {
//                Map<String, String> linkMap = Maps.newHashMap();
//                linkMap.put("zookeeper", "zookeeper");
//                builder.linkMap(linkMap);
//            }
            {
                Map<String, String> eMap = Maps.newHashMap();
                eMap.put("JAVA_OPTS", "'-Xms64m -Xmx64m -Xmn32m'");
                builder.eMap(eMap);
            }
            builder.dRestart(true);
            runs.add(builder.build());
        }
    }
}
