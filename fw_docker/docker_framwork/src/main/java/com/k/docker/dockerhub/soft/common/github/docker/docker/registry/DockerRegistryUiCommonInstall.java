package com.k.docker.dockerhub.soft.common.github.docker.docker.registry;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;

import java.util.List;
import java.util.Map;

//docker run -d -p 8080:8080 --name registry-web --link registry -e REGISTRY_URL=http://registry:5000/v2 -e REGISTRY_NAME=registry:5000 hyper/docker-registry-web
public class DockerRegistryUiCommonInstall extends DockerhubCallBack {
    @Override
    public void writeDockerRuns(List<DockerRun> runs) {
        String version = "hyper/docker-registry-web";
        String name = "registryui";
        {
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.nameCmd(name);
            builder.dCmd(true);
            builder.version(version);
            {
                Map<String, String> pCmdMap = Maps.newHashMap();
                pCmdMap.put("6000", "8080");
                builder.pCmdMap(pCmdMap);
            }
            {
                Map<String, String> eCmdMap = Maps.newHashMap();
                eCmdMap.put("REGISTRY_URL", "http://registry:5000/v2");
                eCmdMap.put("REGISTRY_NAME", "registry:5000");
                builder.eMap(eCmdMap);
            }
            builder.linkMap(ImmutableMap.of("registry", "registry"));
            builder.dRestart(true);
            runs.add(builder.build());
        }
    }
}
