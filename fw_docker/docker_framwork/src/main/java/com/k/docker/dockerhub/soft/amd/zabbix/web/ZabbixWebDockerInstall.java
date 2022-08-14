package com.k.docker.dockerhub.soft.amd.zabbix.web;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;

import java.util.List;
import java.util.Map;

//https://hub.docker.com/_/memcached/
public class ZabbixWebDockerInstall extends DockerhubCallBack {
    @Override
    public void writeDockerRuns(List<DockerRun> runs) {
        runs.add(build("zxweb"));
    }

    private DockerRun build(String name) {
        {
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.nameCmd(name);
            builder.dCmd(true);
            builder.version("zabbix/zabbix-web-apache-mysql");
            {
                Map<String, String> emap = Maps.newHashMap();
                emap.put("DB_SERVER_HOST", "mysql");
                emap.put("DB_SERVER_PORT", "3306");
                emap.put("MYSQL_USER", "root");
                emap.put("MYSQL_PASSWORD", "111111");
                emap.put("ZBX_SERVER_HOST", "zxserver");
                builder.eMap(emap);
            }
            {
                builder.pCmdMap(Map.of("8088", "80"));
            }

            builder.dRestart(true);
            return builder.build();
        }
    }
}
