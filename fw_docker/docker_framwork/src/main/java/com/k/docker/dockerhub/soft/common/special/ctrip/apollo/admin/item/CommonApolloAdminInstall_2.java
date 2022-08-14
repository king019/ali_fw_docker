package com.k.docker.dockerhub.soft.common.special.ctrip.apollo.admin.item;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.docker.util.props.PropsContainerUtil;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;
import com.k.docker.dockerhub.base.enums.DockerRegionEnum;

import java.util.List;
import java.util.Map;

import static com.k.docker.docker.util.props.PropsDockerUtil.dockerAbsDataDir;

public class CommonApolloAdminInstall_2 extends DockerhubCallBack {
    @Override
    public void buildRegion(DockerRegionEnum region, List<DockerRun> runs) {
        String name = "apolloAdmin";
        String version = "king019/apollo:admin";
        buildModel(name, DockerRegionEnum.getVersion(region, version), runs);
    }

    public void buildModel(String name, String version, List<DockerRun> runs) {
        {
            //ApolloConfigDB.ServerConfig    配置注册中心
            //http://apolloConfig:6121/eureka/
            int port = 6122;
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
                pCmdMap.put("" + port, "" + port);
                builder.pCmdMap(pCmdMap);
            }
            {
                Map<String, String> linkMap = Maps.newHashMap();
                linkMap.put("apolloConfig", "apolloConfig");
                builder.linkMap(linkMap);
            }
            {
                Map<String, String> eMap = Maps.newHashMap();
                eMap.put("DS_URL", "jdbc:mysql://mysql:3306/ApolloConfigDB");
                eMap.put("DS_USERNAME", "root");
                eMap.put("DS_PASSWORD", "111111");
                eMap.put("SERVER_PORT", "" + port);
                eMap.put("EUREKA_INSTANCE_IP_ADDRESS", "apolloAdmin");
                //eMap.put("JAVA_OPTS", "'-Xms128m -Xmx128m -Xmn64m -Deureka.instance.instance-id=smp:"+port+"'");
                //eMap.put("JAVA_OPTS", "'-Xms128m -Xmx128m -Xmn64m -Deureka.instance.ip-address=smp '");
                eMap.put("JAVA_OPTS", "'-Xms128m -Xmx128m -Xmn64m '");
                builder.eMap(eMap);
            }
            builder.dRestart(true);
            runs.add(builder.build());
        }
    }
}
