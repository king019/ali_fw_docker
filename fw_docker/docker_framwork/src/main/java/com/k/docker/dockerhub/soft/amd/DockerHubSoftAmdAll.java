package com.k.docker.dockerhub.soft.amd;

import com.k.docker.dockerhub.soft.amd.apache.ApacheHubAll;
import com.k.docker.dockerhub.soft.amd.bpm.BpmSoftAll;
import com.k.docker.dockerhub.soft.amd.codecheck.CodeCheckSoftAll;
import com.k.docker.dockerhub.soft.amd.database.DataBaseHubAll;
import com.k.docker.dockerhub.soft.amd.elk.ElkHubAll;
import com.k.docker.dockerhub.soft.amd.git.GitHubAll;
import com.k.docker.dockerhub.soft.amd.monitor.MonitorHubAll;
import com.k.docker.dockerhub.soft.amd.proxy.ProxyHubAll;
import com.k.docker.dockerhub.soft.amd.spring.SrpingHubAll;
import com.k.docker.dockerhub.soft.amd.zabbix.ZabixAll;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({BpmSoftAll.class, DataBaseHubAll.class, CodeCheckSoftAll.class, ZabixAll.class, ProxyHubAll.class, ApacheHubAll.class, SrpingHubAll.class, MonitorHubAll.class, ElkHubAll.class, GitHubAll.class})
public class DockerHubSoftAmdAll {

}
