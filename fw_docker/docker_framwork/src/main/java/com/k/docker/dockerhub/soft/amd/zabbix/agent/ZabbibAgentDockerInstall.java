package com.k.docker.dockerhub.soft.amd.zabbix.agent;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.docker.util.props.PropsDockerUtil;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;

import java.util.List;
import java.util.Map;

//https://hub.docker.com/_/memcached/
public class ZabbibAgentDockerInstall extends DockerhubCallBack {


    @Override
    public void writeDockerRuns(List<DockerRun> runs) {
        String zabbixServer = "zxserver";
        String name = "zxagent";
        {
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.nameCmd(name);
            builder.dCmd(true);
            builder.version("zabbix/zabbix-agent");
            {
                builder.consumerCmds(List.of("--requirepass password"));
            }
            {
                Map<String, String> vCmdMap = Maps.newHashMap();
                vCmdMap.put(PropsDockerUtil.dockerAbsDataDir + name, "/var/lib/mysql");
                builder.vCmdMap(vCmdMap);
            }
            {
                Map<String, String> map = Maps.newHashMap();
                map.put("ZBX_HOSTNAME", name);
                map.put("ZBX_SERVER_HOST", zabbixServer);
                map.put("ZBX_SERVER_PORT", "10051");
                builder.eMap(map);
            }
            {
                builder.pCmdMap(Map.of("10050", "10050"));
            }

            builder.dRestart(true);
            runs.add(builder.build());
        }
    }
}
