package com.k.docker.dockerhub.soft.amd.zabbix.server;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;

import java.util.List;
import java.util.Map;

//http://blog.csdn.net/u012373815/article/details/71598457
public class ZabbixServerDockerInstall extends DockerhubCallBack {
    @Override
    public void writeDockerRuns(List<DockerRun> runs) {
        runs.add(build("zxserver"));
    }

    private DockerRun build(String name) {
        {
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.nameCmd(name);
            builder.dCmd(true);
            builder.version("zabbix/zabbix-server-mysql");
            {
                Map<String, String> emap = Maps.newHashMap();
                emap.put("DB_SERVER_HOST", "mysql");
                emap.put("DB_SERVER_PORT", "3306");
                emap.put("MYSQL_USER", "root");
                emap.put("MYSQL_PASSWORD", "111111");
                builder.eMap(emap);
            }
            {
                builder.pCmdMap(Map.of("10051", "10051"));
            }

            builder.dRestart(true);
            return builder.build();
        }
    }
}
