package com.k.docker.dockerhub.soft.amd.codecheck.sonar;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;

import java.util.List;
import java.util.Map;

import static com.k.docker.docker.util.props.PropsDockerUtil.dockerAbsDataDir;

/**
 * docker run -d --name sonarqube \
 * -p 9000:9000 \
 * -e SONAR_JDBC_URL=... \
 * -e SONAR_JDBC_USERNAME=... \
 * -e SONAR_JDBC_PASSWORD=... \
 * -v sonarqube_data:/opt/sonarqube/data \
 * -v sonarqube_extensions:/opt/sonarqube/extensions \
 * -v sonarqube_logs:/opt/sonarqube/logs \
 * <image_name>
 */
public class SonarqubeInstall extends DockerhubCallBack {
    @Override
    public void writeDockerRuns(List<DockerRun> runs) {
        String name = "sonarqube";
        String version = "sonarqube";
        {
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.nameCmd(name);
            builder.dCmd(true);
            builder.version(version);
            {
                Map<String, String> vCmdMap = Maps.newHashMap();
                vCmdMap.put(dockerAbsDataDir + name + "/data", "/opt/sonarqube/data");
                vCmdMap.put(dockerAbsDataDir + name + "/extensions", "/opt/sonarqube/extensions");
                vCmdMap.put(dockerAbsDataDir + name + "/logs", "/opt/sonarqube/logs");
                builder.vCmdMap(vCmdMap);
            }
            {
                Map<String, String> pCmdMap = Maps.newHashMap();
                pCmdMap.put("9400", "9000");
                pCmdMap.put("9492", "9092");
                builder.pCmdMap(pCmdMap);
            }
            {
                Map<String, String> eCmdMap = Maps.newHashMap();
                eCmdMap.put("SONAR_JDBC_URL", "'jdbc:postgresql://postgres:5432/sonar'");
                eCmdMap.put("SONAR_JDBC_USERNAME", "root");
                eCmdMap.put("SONAR_JDBC_PASSWORD", "111111");

                //eCmdMap.put("SONAR_JDBC_URL", "'jdbc:sqlserver://mssql:1433;database=sonar'");
                //eCmdMap.put("SONAR_JDBC_USERNAME", "sa");
                //eCmdMap.put("SONAR_JDBC_PASSWORD", "Admin123..");
                builder.eMap(eCmdMap);
            }
            builder.dRestart(true);
            runs.add(builder.build());
        }
    }
}
