package com.k.docker.dockerhub.soft.common.special.alibaba.sentinel.share.sentinelapollo.item;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.docker.util.props.PropsContainerUtil;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;
import com.k.docker.dockerhub.base.enums.DockerRegionEnum;

import java.util.List;
import java.util.Map;

import static com.k.docker.docker.util.props.PropsDockerUtil.dockerAbsDataDir;

public class CommonSentinelShareApolloInstall extends DockerhubCallBack {
    @Override
    public void buildRegion(DockerRegionEnum region, List<DockerRun> runs) {
        String name = "sentinelShareApollo";
        String version = "king019/alibaba:sentinel_share_apollo";
        int port = 8915;
        for (int i = 0; i < 10; i++) {
            buildModel(name + port, region, port, DockerRegionEnum.getVersion(region, version), runs, i == 0);
            port++;
        }
    }

    public void buildModel(String name, DockerRegionEnum region, int port, String version, List<DockerRun> runs, boolean note) {
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
                pCmdMap.put("" + port, "" + port);
                builder.pCmdMap(pCmdMap);
            }
            {
                Map<String, String> eMap = Maps.newHashMap();
                eMap.put("JAVA_OPTS", "'-Xms32m -Xmx32m -Xmn16m -Dserver.port=" + port + "'");
                eMap.put("SERVER_PORT", "" + port);
                builder.eMap(eMap);
            }
            if (note) {
                builder.notes(List.of(region.getRegion()));
            }
            builder.dRestart(true);
            runs.add(builder.build());
        }
    }
}
