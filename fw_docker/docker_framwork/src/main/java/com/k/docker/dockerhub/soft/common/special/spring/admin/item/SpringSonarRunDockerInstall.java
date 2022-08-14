package com.k.docker.dockerhub.soft.common.special.spring.admin.item;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.docker.util.props.PropsContainerUtil;
import com.k.docker.docker.util.props.PropsDockerUtil;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;
import com.k.docker.dockerhub.base.enums.DockerRegionEnum;

import java.util.List;
import java.util.Map;

public class SpringSonarRunDockerInstall extends DockerhubCallBack {
    @Override
    public void buildRegion(DockerRegionEnum region, List<DockerRun> runs) {
        String version = "king019/spring:sonar";
        String name = "sonarRun";
        buildModel(name, region, version, runs);
    }

    public void buildModel(String name, DockerRegionEnum region, String version, List<DockerRun> runs) {
        String port = "9911";
        {
            version = DockerRegionEnum.getVersion(region, version);
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.region(region);
            builder.nameCmd(name);
            builder.dCmd(true);
            builder.version(version);
            {
                Map<String, String> vCmdMap = Maps.newHashMap();
                vCmdMap.put(PropsDockerUtil.dockerAbsDataDir + name, PropsContainerUtil.rootSoftDir);
                builder.vCmdMap(vCmdMap);
            }
            {
                Map<String, String> eCmdMap = Maps.newHashMap();
                eCmdMap.put("SONAR_TOKEN", "533b0a02f7cfffcf200b1f71eaee9d1a658cf3a7");
                eCmdMap.put("JAVA_OPTS", "'-Dserver.port=" + port + "'");
                builder.eMap(eCmdMap);
            }
            {
                Map<String, String> pCmdMap = Maps.newHashMap();
                pCmdMap.put(port, port);
                builder.pCmdMap(pCmdMap);
            }
            builder.dRestart(true);
            runs.add(builder.build());
        }
    }
}
