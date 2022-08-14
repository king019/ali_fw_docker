package com.k.docker.dockerhub.soft.common.special.apache.maven.nexus;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.docker.util.props.PropsDockerUtil;
import com.k.docker.dockerhub.base.enums.DockerRegionEnum;

import java.util.List;
import java.util.Map;

public class CommonNexusMavenInstall extends NexusMavenBaseInstall {
    @Override
    public void buildRegion(DockerRegionEnum region, List<DockerRun> runs) {
        String name = "nexus";
        String version = "king019/nexus";
        buildModel(name, DockerRegionEnum.getVersion(region, version), runs);
    }

    public void buildModel(String name, String version, List<DockerRun> runs) {
        {
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.nameCmd(name);
            builder.dCmd(true);
            builder.version(version);
            {
                Map<String, String> vCmdMap = Maps.newHashMap();
                vCmdMap.put(PropsDockerUtil.dockerAbsDataDir + name, "/root/tools/sonatype-work");
                builder.vCmdMap(vCmdMap);
            }
            {
                Map<String, String> pCmdMap = Maps.newHashMap();
                pCmdMap.put("8081", "8081");
                pCmdMap.put("80", "8081");
                builder.pCmdMap(pCmdMap);
            }
            builder.dRestart(true);
            runs.add(builder.build());
        }
    }
}
