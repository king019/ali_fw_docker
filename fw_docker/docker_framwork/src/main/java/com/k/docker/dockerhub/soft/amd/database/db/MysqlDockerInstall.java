package com.k.docker.dockerhub.soft.amd.database.db;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.docker.util.props.PropsDockerUtil;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;

import java.util.List;
import java.util.Map;

public class MysqlDockerInstall extends DockerhubCallBack {
    @Override
    public void writeDockerRuns(List<DockerRun> runs) {
        String name = "mysql";
        {
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.nameCmd(name);
            builder.dCmd(true);
            builder.version("mysql:5.7");
            {
                Map<String, String> vCmdMap = Maps.newHashMap();
                vCmdMap.put(PropsDockerUtil.dockerAbsDataDir + name, "/var/lib/mysql");
                builder.vCmdMap(vCmdMap);
            }
            {
                Map<String, String> map = Maps.newHashMap();
                map.put("MYSQL_ROOT_PASSWORD", "111111");
                map.put("MYSQL_DATABASE", "test");
                builder.eMap(map);
            }
            {
                Map<String, String> pCmdMap = Maps.newHashMap();
                pCmdMap.put("3306", "3306");
                builder.pCmdMap(pCmdMap);
            }
            builder.dRestart(true);
            runs.add(builder.build());
        }
    }
}
