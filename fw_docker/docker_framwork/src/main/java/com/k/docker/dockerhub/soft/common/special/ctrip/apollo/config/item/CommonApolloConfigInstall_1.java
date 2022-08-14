package com.k.docker.dockerhub.soft.common.special.ctrip.apollo.config.item;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.docker.util.props.PropsContainerUtil;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;
import com.k.docker.dockerhub.base.enums.DockerRegionEnum;

import java.util.List;
import java.util.Map;

import static com.k.docker.docker.util.props.PropsDockerUtil.dockerAbsDataDir;

public class CommonApolloConfigInstall_1 extends DockerhubCallBack {
    @Override
    public void buildRegion(DockerRegionEnum region, List<DockerRun> runs) {
        String name = "apolloConfig";
        String version = "king019/apollo:config";
        buildModel(name, DockerRegionEnum.getVersion(region, version), runs);
    }

    public void buildModel(String name, String version, List<DockerRun> runs) {
        {
            //ApolloConfigDB.ServerConfig    配置注册中心
            //http://apolloConfig:6121/eureka/
            int port = 6121;
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
                Map<String, String> eMap = Maps.newHashMap();
                eMap.put("DS_URL", "jdbc:mysql://mysql:3306/ApolloConfigDB");
                eMap.put("DS_USERNAME", "root");
                eMap.put("DS_PASSWORD", "111111");
                eMap.put("SERVER_PORT", "" + port);
                eMap.put("EUREKA_INSTANCE_IP_ADDRESS", "apolloConfig");
                //eMap.put("JAVA_OPTS", "'-Xms128m -Xmx128m -Xmn64m -Deureka.instance.instance-id=smp:"+port+"'");
                //eMap.put("JAVA_OPTS", "'-Xms128m -Xmx128m -Xmn64m -Deureka.instance.ip-address=smp '");
                eMap.put("JAVA_OPTS", "'-Xms128m -Xmx128m -Xmn64m'");
                //-Deureka.client.register-with-eureka=false -Deureka.client.fetch-registry=false


                builder.eMap(eMap);
            }
            builder.dRestart(true);
            runs.add(builder.build());
        }
    }
}
