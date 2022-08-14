package com.k.docker.dockerhub.soft.common.github.ci.drone.item;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.docker.util.props.PropsDockerHostUtil;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;
import com.k.docker.dockerhub.base.enums.DockerPlatformEnum;
import com.k.docker.dockerhub.base.enums.DockerRegionEnum;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CommonDroneRunnerInstall extends DockerhubCallBack {

    @Override
    public void buildRegion(DockerRegionEnum region, List<DockerRun> runs) {
        int num = 5;
        {
            String version = "drone/drone-runner-docker";
            String name = "runner" + DockerPlatformEnum.AMD.getPlatform();
            String host = "drone" + DockerPlatformEnum.AMD.getPlatform();
            String versionInner = DockerRegionEnum.getVersion(region, version);
            for (int i = 0; i < num; i++) {
                buildModel(name + i, region, versionInner, host, runs, false, i == 0);
            }
        }
        {
            String version = "drone/drone-runner-docker";
            String name = "runnerhost";
            String host = PropsDockerHostUtil.DOCKER_HOST;
            String versionInner = DockerRegionEnum.getVersion(region, version);
            for (int i = 0; i < num; i++) {
                buildModel(name + i, region, versionInner, host, runs, false, i == 0);
            }
        }
        {
            String version = "king019/drone-runner-docker";
            String name = "runner" + DockerPlatformEnum.ARM64.getPlatform();
            String host = "drone" + DockerPlatformEnum.ARM64.getPlatform();
            String versionInner = DockerRegionEnum.getVersion(region, version);
            for (int i = 0; i < num; i++) {
                buildModel(name + i, region, versionInner, host, runs, i == 0);
            }

        }
    }

    public void buildModel(String name, DockerRegionEnum region, String version, String host, List<DockerRun> runs, boolean note) {
        buildModel(name, region, version, host, runs, false, note);
    }

    public void buildModel(String name, DockerRegionEnum region, String version, String host, List<DockerRun> runs, boolean local, boolean note) {
        {
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.nameCmd(name);
            builder.dCmd(true);
            builder.version(version);
            {
                Map<String, String> vCmdMap = Maps.newHashMap();
                //vCmdMap.put(dockerAbsDataDir + name, PropsContainerUtil.rootSoftDir);
                vCmdMap.put("/var/run/docker.sock", "/var/run/docker.sock");
                builder.vCmdMap(vCmdMap);
            }
            {

                Map<String, String> pHostMap = Maps.newHashMap();
                if (local) {
                    pHostMap.put(host, PropsDockerHostUtil.DOCKER_HOST);
                }
                builder.addHostMap(pHostMap);
            }
            {
                Map<String, String> pCmdMap = Maps.newHashMap();
                //pCmdMap.put("4000", "3000");
                builder.pCmdMap(pCmdMap);
            }
            {
                Map<String, String> eLinkmap = Maps.newHashMap();
                eLinkmap.put("DRONE_RPC_PROTO", "http");
                eLinkmap.put("DRONE_RPC_HOST", host + ":4080");
                eLinkmap.put("DRONE_RPC_SECRET", "bea26a2221fd8090ea38720fc445eca6");
                eLinkmap.put("DRONE_RUNNER_CAPACITY", "2");
                eLinkmap.put("DRONE_RUNNER_NAME", "drone-runner-docker");
                builder.eMap(eLinkmap);
            }
            if (Objects.nonNull(region) && note) {
                builder.notes(Lists.newArrayList(region.getRegion()));
            }
            builder.dRestart(true);
            runs.add(builder.build());
        }
        /**
         *
         *   docker run -d \
         *   -v /var/run/docker.sock:/var/run/docker.sock \
         *   -e DRONE_RPC_PROTO=http \
         *   -e DRONE_RPC_HOST=drone:4080 \
         *   -e DRONE_RPC_SECRET=bea26a2221fd8090ea38720fc445eca6 \
         *   -e DRONE_RUNNER_CAPACITY=2 \
         *   -e DRONE_RUNNER_NAME=drone-runner-docker \
         *   -p 4000:3000 \
         *   --restart always \
         *   --name drone-runner-docker \
         *  drone/drone-runner-docker
         */
    }
}
