package com.k.docker.dockerhub.soft.common.github.ci.drone.item;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.docker.util.props.PropsContainerUtil;
import com.k.docker.docker.util.props.PropsDockerHostUtil;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;
import com.k.docker.dockerhub.base.enums.DockerPlatformEnum;

import java.util.List;
import java.util.Map;

import static com.k.docker.docker.util.props.PropsDockerUtil.dockerAbsDataDir;

public class CommonDroneInstall extends DockerhubCallBack {
    @Override
    public void writeDockerRuns(List<DockerRun> runs) {
        String name = "drone";
        writeDockerRuns(runs, name, DockerPlatformEnum.ARM64, false);
        writeDockerRuns(runs, name, DockerPlatformEnum.AMD, false);
        writeDockerRuns(runs, name, DockerPlatformEnum.AMD, true);
    }


    public void writeDockerRuns(List<DockerRun> runs, String name, DockerPlatformEnum planformEnum, boolean addLocalHost) {
        String version = "drone/drone";
        name = name + planformEnum.getPlatform();
        String gogs = "gogs" + planformEnum.getPlatform();
        {
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.nameCmd(name);
            builder.dCmd(true);
            builder.version(version);
            {
                Map<String, String> vCmdMap = Maps.newHashMap();
                vCmdMap.put(dockerAbsDataDir + name, PropsContainerUtil.rootSoftDir);
                vCmdMap.put(dockerAbsDataDir + name + "/data", "/data");
                builder.vCmdMap(vCmdMap);
            }
            {
                Map<String, String> pCmdMap = Maps.newHashMap();
                pCmdMap.put("4080", "80");
//                pCmdMap.put("4443", "443");
                builder.pCmdMap(pCmdMap);
            }
            {

                Map<String, String> pHostMap = Maps.newHashMap();
                if (addLocalHost) {
                    pHostMap.put(gogs, PropsDockerHostUtil.DOCKER_HOST);
                }
                builder.addHostMap(pHostMap);
            }
            {
                Map<String, String> eLinkmap = Maps.newHashMap();
                eLinkmap.put("DRONE_AGENTS_ENABLED", "true");
                eLinkmap.put("DRONE_GIT_ALWAYS_AUTH", "false");
                //gogs
                eLinkmap.put("DRONE_GOGS_SERVER", "http://" + gogs + ":3001");
                //gitea
//                eLinkmap.put("DRONE_GITEA_SERVER", "http://gitea:3000");
//                eLinkmap.put("DRONE_GITEA_CLIENT_ID", "0d902caa-1e09-44f7-9a2f-bdb7c29ac8c6");
//                eLinkmap.put("DRONE_GITEA_CLIENT_SECRET", "dr1C30aG_egIW8bRDWXJE0ejUZb6ZPeNTXh2ZcGSrQU");

                eLinkmap.put("DRONE_RPC_SECRET", "bea26a2221fd8090ea38720fc445eca6");
                eLinkmap.put("DRONE_SERVER_HOST", name + ":4080");
                eLinkmap.put("DRONE_SERVER_PROTO", "http");
                eLinkmap.put("DRONE_USER_CREATE", "username:king019,admin:true");
                builder.eMap(eLinkmap);
            }
            {
                //--env=DRONE_GITEA_SERVER={{DRONE_GITEA_SERVER}} \
                //  --env=DRONE_GITEA_CLIENT_ID={{DRONE_GITEA_CLIENT_ID}} \
                //  --env=DRONE_GITEA_CLIENT_SECRET={{DRONE_GITEA_CLIENT_SECRET}} \
            }

            builder.dRestart(true);
            runs.add(builder.build());
        }
        /**
         *
         * docker run \
         *   --env=DRONE_AGENTS_ENABLED=true \
         *   --env=DRONE_GIT_ALWAYS_AUTH=true \
         *   --env=DRONE_GOGS_SERVER=http://gitea:3000 \
         *   --env=DRONE_RPC_SECRET=bea26a2221fd8090ea38720fc445eca6 \
         *   --env=DRONE_SERVER_HOST=runner:4000 \
         *   --env=DRONE_SERVER_PROTO=http \
         *   --publish=4080:80 \
         *   --publish=4443:443 \
         *   --restart=always \
         *   --detach=true \
         *   --name=drone \
         *   drone/drone
         */
    }
}
