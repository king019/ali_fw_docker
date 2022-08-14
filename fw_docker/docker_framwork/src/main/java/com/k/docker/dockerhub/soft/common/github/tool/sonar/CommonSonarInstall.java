package com.k.docker.dockerhub.soft.common.github.tool.sonar;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;

import java.util.List;
import java.util.Map;

import static com.k.docker.docker.util.props.PropsDockerUtil.dockerAbsDataDir;

/**
 * mvn sonar:sonar  -Dsonar.projectKey=sonar  -Dsonar.host.url=http://sonar:9100  -Dsonar.login=e355e68bb85baf6ea0c5c46e2186148e754e2170  -Dsonar.java.binaries=target/ -Djava-module.sonar.projectBaseDir=.
 * docker run --name sonar -d -p 9100:9000 hub4rpi64/sonarqube:8.3.1.34397
 * docker run -d --name Sonarqube -p 9100:9000 -p 9102:9092   -d hub4rpi64/sonarqube:8.3.1.34397
 */
public class CommonSonarInstall extends DockerhubCallBack {
    @Override
    public void writeDockerRuns(List<DockerRun> runs) {
        String version = "hub4rpi64/sonarqube:8.3.1.34397";
        String name = "SonarqubeArm";
        buildModel(name, version, runs);
        version = "hub4rpi64/sonarqube:7community";
        buildModel(name, version, runs);
    }


    public void buildModel(String name, String version, List<DockerRun> runs) {

        {
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
                Map<String, String> pCmdMap = Maps.newHashMap();
                pCmdMap.put("9400", "9000");
                pCmdMap.put("9492", "9092");
                builder.pCmdMap(pCmdMap);
            }
            builder.dRestart(true);
            runs.add(builder.build());
        }
    }
}
