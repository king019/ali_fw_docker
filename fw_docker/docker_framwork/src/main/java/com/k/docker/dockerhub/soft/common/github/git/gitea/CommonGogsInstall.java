package com.k.docker.dockerhub.soft.common.github.git.gitea;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.docker.util.props.PropsDockerHostUtil;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;
import com.k.docker.dockerhub.base.enums.DockerPlatformEnum;

import java.util.List;
import java.util.Map;

import static com.k.docker.docker.util.props.PropsDockerUtil.dockerAbsDataDir;

public class CommonGogsInstall extends DockerhubCallBack {
    @Override
    public void writeDockerRuns(List<DockerRun> runs) {
        String version = "whatever4711/gogs";
        String name = "gogs" + DockerPlatformEnum.ARM64.getPlatform();
        buildModel(name, version, runs);
        version = "gogs/gogs";
        name = "gogs" + DockerPlatformEnum.AMD.getPlatform();
        buildModel(name, version, runs, true);
    }

    public void buildModel(String name, String version, List<DockerRun> runs) {
        buildModel(name, version, runs, false);
    }

    public void buildModel(String name, String version, List<DockerRun> runs, boolean local) {
        {
            String drone = "drone";
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.mMem(true);
            builder.nameCmd(name);
            builder.dCmd(true);
            builder.version(version);
            {
                Map<String, String> vCmdMap = Maps.newHashMap();
                vCmdMap.put(dockerAbsDataDir + name, "/data");
                builder.vCmdMap(vCmdMap);
            }
            {
                Map<String, String> pHostMap = Maps.newHashMap();
                if (local) {
                    pHostMap.put(drone + DockerPlatformEnum.AMD.getPlatform(), PropsDockerHostUtil.DOCKER_HOST);
                    pHostMap.put(drone + DockerPlatformEnum.ARM64.getPlatform(), PropsDockerHostUtil.DOCKER_HOST);
                }
                builder.addHostMap(pHostMap);
            }
            {
                Map<String, String> pCmdMap = Maps.newHashMap();
                pCmdMap.put("3001", "3000");
                builder.pCmdMap(pCmdMap);
            }
            builder.dRestart(true);
            runs.add(builder.build());
        }
    }
}
