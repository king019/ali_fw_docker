package com.k.docker.dockerhub.soft.common.github.apache.maven.nexus;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.docker.util.props.PropsDockerUtil;
import com.k.docker.dockerhub.soft.common.special.apache.maven.nexus.NexusMavenBaseInstall;

import java.util.List;
import java.util.Map;

public class NexusMavenInstall extends NexusMavenBaseInstall {

    @Override
    public void writeDockerRuns(List<DockerRun> runs) {
        Map<String, String> pCmdMap = Maps.newHashMap();
        {

            pCmdMap.put("80", "8081");
            pCmdMap.put("8081", "8081");
        }
        {
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.nameCmd("nexus3arm");
            builder.dCmd(true);
            builder.version("klo2k/nexus3");
            {
                Map<String, String> vCmdMap = Maps.newHashMap();
                vCmdMap.put(PropsDockerUtil.dockerAbsDataDir + "nexus3arm", "/sonatype-work");
                builder.vCmdMap(vCmdMap);
            }
            {
                //pCmdMap.put("443", "8081");
                builder.pCmdMap(pCmdMap);
            }
            builder.dRestart(true);
            runs.add(builder.build());
        }
        //adm64
        {
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.nameCmd("nexus2");
            builder.dCmd(true);
            builder.version("sonatype/nexus");
            {
                Map<String, String> vCmdMap = Maps.newHashMap();
                vCmdMap.put(PropsDockerUtil.dockerAbsDataDir + "nexus2", "/sonatype-work");
                builder.vCmdMap(vCmdMap);
            }
            {
                builder.pCmdMap(pCmdMap);
            }
            builder.dRestart(true);
            runs.add(builder.build());
        }
        {
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.nameCmd("nexus3");
            builder.dCmd(true);
            builder.version("sonatype/nexus3");
            {
                Map<String, String> vCmdMap = Maps.newHashMap();
                vCmdMap.put(PropsDockerUtil.dockerAbsDataDir + "nexus3", "/nexus-data");
                builder.vCmdMap(vCmdMap);
            }
            {
                builder.pCmdMap(pCmdMap);
            }
            builder.dRestart(true);
            runs.add(builder.build());
        }
    }
}
