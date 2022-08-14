package com.k.docker.dockerhub.soft.amd.zabbix;

import com.k.docker.dockerhub.soft.amd.zabbix.agent.ZabbibAgentDockerInstall;
import com.k.docker.dockerhub.soft.amd.zabbix.server.ZabbixServerDockerInstall;
import com.k.docker.dockerhub.soft.amd.zabbix.web.ZabbixWebDockerInstall;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ZabbixServerDockerInstall.class, ZabbixWebDockerInstall.class, ZabbibAgentDockerInstall.class})
public class ZabixAll {

}
