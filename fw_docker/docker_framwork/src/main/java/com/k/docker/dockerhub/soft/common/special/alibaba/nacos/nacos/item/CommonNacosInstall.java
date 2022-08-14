package com.k.docker.dockerhub.soft.common.special.alibaba.nacos.nacos.item;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.docker.util.props.PropsContainerUtil;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;
import com.k.docker.dockerhub.base.enums.DockerRegionEnum;

import java.util.List;
import java.util.Map;

import static com.k.docker.docker.util.props.PropsDockerUtil.dockerAbsDataDir;

public class CommonNacosInstall extends DockerhubCallBack {
    @Override
    public void buildRegion(DockerRegionEnum region, List<DockerRun> runs) {
        String name = "nacos";
        String version = "king019/nacos";
        buildModel(name, region, DockerRegionEnum.getVersion(region, version), runs);
    }

    public void buildModel(String name, DockerRegionEnum region, String version, List<DockerRun> runs) {
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
            {
                Map<String, String> pCmdMap = Maps.newHashMap();
                pCmdMap.put("8848", "8848");
                builder.pCmdMap(pCmdMap);
            }
            {
                Map<String, String> eCmdMap = Maps.newHashMap();
                eCmdMap.put("db.num", "1");
                eCmdMap.put("db.url.0", "jdbc:mysql://mysql:3306/nacos");
                eCmdMap.put("db.user", "root");
                eCmdMap.put("db.password", "111111");
                builder.eMap(eCmdMap);
            }
            {
                Map<String, String> eMap = Maps.newHashMap();
                eMap.put("JAVA_OPT", "'-Xms64m -Xmx64m -Xmn32m'");
                builder.eMap(eMap);
            }
            builder.notes(List.of(region.getRegion()));
            builder.dRestart(true);
            runs.add(builder.build());
        }
    }
}
