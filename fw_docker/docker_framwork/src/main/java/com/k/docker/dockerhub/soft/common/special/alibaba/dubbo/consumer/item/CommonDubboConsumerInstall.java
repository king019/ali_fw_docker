package com.k.docker.dockerhub.soft.common.special.alibaba.dubbo.consumer.item;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.docker.util.props.PropsContainerUtil;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;
import com.k.docker.dockerhub.base.enums.DockerRegionEnum;

import java.util.List;
import java.util.Map;

import static com.k.docker.docker.util.props.PropsDockerUtil.dockerAbsDataDir;

public class CommonDubboConsumerInstall extends DockerhubCallBack {
    @Override
    public void buildRegion(DockerRegionEnum region, List<DockerRun> runs) {
        String name = "dubboconsumer";
        String version = "king019/dubbo:consumer";
        int port = 8882;
        for (int i = 0; i < 2; i++) {
            buildModel(name + port, region, DockerRegionEnum.getVersion(region, version), port, runs);
            port++;
        }

    }

    public void buildModel(String name, DockerRegionEnum region, String version, int port, List<DockerRun> runs) {

        {
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.nameCmd(name);
            builder.dCmd(true);
            builder.version(version);
            {
                Map<String, String> vCmdMap = Maps.newHashMap();
                vCmdMap.put(dockerAbsDataDir + name, PropsContainerUtil.rootSoftDir);
                vCmdMap.put(dockerAbsDataDir + "maven/m2", PropsContainerUtil.rootM2Dir);
                builder.vCmdMap(vCmdMap);
            }
//            {
//                Map<String, String> linkMap = Maps.newHashMap();
//                linkMap.put("zookeeper", "zookeeper");
//                builder.linkMap(linkMap);
//            }
            {
                Map<String, String> pCmdMap = Maps.newHashMap();
                pCmdMap.put("" + port, "8882");
                builder.pCmdMap(pCmdMap);
            }
            {
                Map<String, String> eMap = Maps.newHashMap();
                eMap.put("JAVA_OPTS", "'-Xms32m -Xmx32m -Xmn16m'");
                builder.eMap(eMap);
            }
            builder.notes(List.of(region.getRegion()));
            builder.dRestart(true);
            runs.add(builder.build());
        }
    }
}
