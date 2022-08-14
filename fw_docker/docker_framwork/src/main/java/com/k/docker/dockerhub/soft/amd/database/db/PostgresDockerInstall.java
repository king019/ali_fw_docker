package com.k.docker.dockerhub.soft.amd.database.db;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.docker.util.props.PropsDockerUtil;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;

import java.util.List;
import java.util.Map;

public class PostgresDockerInstall extends DockerhubCallBack {
    @Override
    public void writeDockerRuns(List<DockerRun> runs) {
        String name = "postgres";
        String version = "postgres";
        {
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.nameCmd(name);
            builder.dCmd(true);
            builder.version(version);
            {
                Map<String, String> vCmdMap = Maps.newHashMap();
                vCmdMap.put(PropsDockerUtil.dockerAbsDataDir + name, "/var/lib/postgresql/data");
                builder.vCmdMap(vCmdMap);
            }
            {
                Map<String, String> map = Maps.newHashMap();
                map.put("POSTGRES_USER", "root");
                map.put("POSTGRES_PASSWORD", "111111");
                //map.put("POSTGRES_DB", "test");
                builder.eMap(map);
            }
            {
                Map<String, String> pCmdMap = Maps.newHashMap();
                pCmdMap.put("5432", "5432");
                builder.pCmdMap(pCmdMap);
            }
            builder.dRestart(true);
            runs.add(builder.build());
        }
    }
}
