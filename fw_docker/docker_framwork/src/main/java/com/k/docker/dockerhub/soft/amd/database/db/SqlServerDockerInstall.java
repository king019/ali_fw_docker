package com.k.docker.dockerhub.soft.amd.database.db;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.docker.util.props.PropsDockerUtil;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;

import java.util.List;
import java.util.Map;

/**
 * username=sa
 * docker run -v /home/docker/database/sqlserver/v1:/var/opt/mssql -v /etc/localtime:/etc/localtime:ro -e TZ="Asia/Shanghai" -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=Admin123..' -p 1403:1433 -d --name sqlserver microsoft/mssql-server-linux
 */
public class SqlServerDockerInstall extends DockerhubCallBack {
    @Override
    public void writeDockerRuns(List<DockerRun> runs) {
        String name = "mssql";
        {
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.nameCmd(name);
            builder.dCmd(true);
            builder.version("microsoft/mssql-server-linux");
            {
                Map<String, String> vCmdMap = Maps.newHashMap();
                vCmdMap.put(PropsDockerUtil.dockerAbsDataDir + name, "/var/opt/mssql");
                builder.vCmdMap(vCmdMap);
            }
            {
                Map<String, String> map = Maps.newHashMap();
                map.put("ACCEPT_EULA", "y");
                map.put("SA_PASSWORD", "Admin123..");
                builder.eMap(map);
            }
            {
                Map<String, String> pCmdMap = Maps.newHashMap();
                pCmdMap.put("1433", "1433");
                builder.pCmdMap(pCmdMap);
            }
            builder.dRestart(true);
            runs.add(builder.build());
        }
    }
}
