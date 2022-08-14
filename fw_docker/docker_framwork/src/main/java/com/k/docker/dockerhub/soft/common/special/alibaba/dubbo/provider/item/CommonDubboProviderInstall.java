package com.k.docker.dockerhub.soft.common.special.alibaba.dubbo.provider.item;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.docker.util.props.PropsContainerUtil;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;
import com.k.docker.dockerhub.base.enums.DockerRegionEnum;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.k.docker.docker.util.props.PropsDockerUtil.dockerAbsDataDir;

public class CommonDubboProviderInstall extends DockerhubCallBack {
    @Override
    public void buildRegion(DockerRegionEnum region, List<DockerRun> runs) {
        String name = "dubboprovider";
        String version = "king019/dubbo:provider";
        int port = 20881;
        int serverPort = 10881;
        for (int i = 0; i < 10; i++) {
            DockerRegionEnum innerRegion = null;
            String versionInner = DockerRegionEnum.getVersion(region, version);
            if (i == 0) {
                innerRegion = region;
            }
            buildModel(name + port, innerRegion, versionInner, port++, serverPort++, runs);
        }
    }

    public void buildModel(String name, DockerRegionEnum region, String version, int port, int serverPort, List<DockerRun> runs) {

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
//            {
//                Map<String, String> linkMap = Maps.newHashMap();
//                linkMap.put("zookeeper", "zookeeper");
//                builder.linkMap(linkMap);
//            }
            {
                Map<String, String> pmap = Maps.newHashMap();
                pmap.put("" + port, "" + port);
                pmap.put("" + serverPort, "" + serverPort);
                builder.pCmdMap(pmap);
            }
//            {
//                Map<String, String> hostMap = Maps.newHashMap();
//                hostMap.put("dubbo", "127.0.0.1");
//                builder.addHostMap(hostMap);
//            }
            {
                Map<String, String> eMap = Maps.newHashMap();
//                eMap.put("JAVA_OPTS", "'-Xms32m -Xmx32m -Xmn16m -Ddubbo.protocol.port=" + port + "'");
                eMap.put("JAVA_OPTS", "'-Xms32m -Xmx32m -Xmn16m '");
                eMap.put("DUBBO_IP_TO_REGISTRY", "dubbo");
                eMap.put("DUBBO_PORT_TO_REGISTRY", "" + port);
                eMap.put("DUBBO_IP_TO_BIND", name);
                eMap.put("DUBBO_PORT_TO_BIND", "" + port);
                eMap.put("server.port", "" + serverPort);

//                # 注册到注册中心的IP，这里我们选择宿主机的IP
//                DUBBO_IP_TO_REGISTRY: 116.62.139.15
//                # 注册到注册中心的端口
//                DUBBO_PORT_TO_REGISTRY: 20883
//                DUBBO_PORT_TO_BIND: 20883
                builder.eMap(eMap);
            }
            if (Objects.nonNull(region)) {
                builder.notes(Lists.newArrayList(region.getRegion()));
            }
            builder.dRestart(false);
            runs.add(builder.build());
        }
    }
}
