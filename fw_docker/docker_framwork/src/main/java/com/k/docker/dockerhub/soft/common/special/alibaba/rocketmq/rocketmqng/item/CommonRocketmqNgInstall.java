package com.k.docker.dockerhub.soft.common.special.alibaba.rocketmq.rocketmqng.item;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.docker.util.props.PropsContainerUtil;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;
import com.k.docker.dockerhub.base.config.InstallConfig;
import com.k.docker.dockerhub.base.enums.DockerRegionEnum;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.k.docker.docker.util.props.PropsDockerUtil.dockerAbsDataDir;

public class CommonRocketmqNgInstall extends DockerhubCallBack {
    @Override
    public void buildRegion(DockerRegionEnum region, List<DockerRun> runs) {
        String name = "rocketmqng";
        String version = "king019/rocketmq:console";
        String versionInner = DockerRegionEnum.getVersion(region, version);
        buildModel(name, region, versionInner, runs);
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
                InstallConfig.putJdk8(vCmdMap);
                builder.vCmdMap(vCmdMap);
            }
            {
                Map<String, String> pmap = Maps.newHashMap();
                pmap.put("" + 9996, "" + 8080);
                builder.pCmdMap(pmap);
            }
            {
                Map<String, String> linkMap = Maps.newHashMap();
                linkMap.put("rocketmq", "rocketmq");
                builder.linkMap(linkMap);
            }
            {
                Map<String, String> eMap = Maps.newHashMap();
                eMap.put("JAVA_OPTS", "'-Xms32m -Xmx32m -Xmn16m -Drocketmq.namesrv.addr=rocketmq:9876 -Dcom.rocketmq.sendMessageWithVIPChannel=false'");
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
