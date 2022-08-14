package com.k.docker.dockerhub.soft.common.special.alibaba.nacos.feign.server.item;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.docker.util.props.PropsContainerUtil;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;
import com.k.docker.dockerhub.base.enums.DockerRegionEnum;

import java.util.List;
import java.util.Map;

import static com.k.docker.docker.util.props.PropsDockerUtil.dockerAbsDataDir;

public class CommonNacosFeignServerInstall extends DockerhubCallBack {
    @Override
    public void buildRegion(DockerRegionEnum region, List<DockerRun> runs) {
        String name = "nacos_feign_server";
        String version = "king019/alibaba:nacos_feign_server";
        int port = 4100;
        for (int i = 0; i < 3; i++) {
            buildModel(name + i, i, port++, region, DockerRegionEnum.getVersion(region, version), runs);
        }
    }

    public void buildModel(String name, int index, int port, DockerRegionEnum region, String version, List<DockerRun> runs) {
        {
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.nameCmd(name);
            builder.dCmd(true);
            builder.version(version);
            {
                Map<String, String> vCmdMap = Maps.newHashMap();
                vCmdMap.put(dockerAbsDataDir + name, PropsContainerUtil.rootSoftDir);
                vCmdMap.put(dockerAbsDataDir + "maven/m2", PropsContainerUtil.rootM2Dir);
                vCmdMap.put(dockerAbsDataDir + "nginxdown", PropsContainerUtil.rootToolsDir);
                builder.vCmdMap(vCmdMap);
            }
            {
                Map<String, String> pCmdMap = Maps.newHashMap();
                pCmdMap.put("" + port, "8884");
                builder.pCmdMap(pCmdMap);
            }
            {
                Map<String, String> eMap = Maps.newHashMap();
//                eMap.put("JAVA_OPTS", "'-Dspring.cloud.nacos.discovery=smp '");
                eMap.put("spring.cloud.nacos.discovery.ip", "smp");
                eMap.put("spring.cloud.nacos.discovery.port", "" + port);
                builder.eMap(eMap);
            }
            {
                if (index == 0) {
                    builder.notes(List.of(region.getRegion()));
                }
                builder.dRestart(true);
                runs.add(builder.build());
            }
        }
    }
}
