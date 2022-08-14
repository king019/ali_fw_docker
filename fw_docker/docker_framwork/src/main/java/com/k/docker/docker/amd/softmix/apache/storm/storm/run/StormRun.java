package com.k.docker.docker.amd.softmix.apache.storm.storm.run;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.softmix.apache.storm.storm.config.host.StormHost;
import com.k.docker.docker.base.docker.run.DockerRunFileBase;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.docker.run.base.impl.DockerRunBaseImpl;
import com.k.docker.docker.util.docker.DockerVersion;
import com.k.docker.docker.util.file.FileWriteUtil;
import org.junit.Test;

import java.util.List;

public class StormRun extends DockerRunFileBase {

    @Test
    public void test() throws Exception {
        String stormVersion = DockerVersion.stromVersion;
        String zooeeperVersion = DockerVersion.zookeeperVersion;
        DockerRunBase dockerRun = new DockerRunBaseImpl();
        StormHost stormHost = new StormHost();
        List<String> lines = Lists.newArrayList();
        Lists.newArrayList();
        stormHost.docker_host_map();
        String param = null;
        List<String> hosts;
        // String dns_dnsmasq_path = FWPathUtil.getClassPath(StormDns.class);
        // zookeeper
        {
            hosts = stormHost.storm_zookeeper();
            for (String host : hosts) {
                dockerRun.dockerRun(lines, zooeeperVersion, host, param);
            }
        }
        // nimbus
        {
            param = "nimbus";
            hosts = stormHost.storm_nimbus();
            for (String host : hosts) {
                dockerRun.dockerRun(lines, stormVersion, host, param);
            }
        }
        // supervisor
        {
            param = "supervisor";
            hosts = stormHost.storm_supervisor();
            for (String host : hosts) {
                dockerRun.dockerRun(lines, stormVersion, host, param);
            }
        }
        // ui
        {
            param = "ui";
            hosts = stormHost.storm_ui();
            for (String host : hosts) {
                dockerRun.dockerRun(lines, stormVersion, host, param);
            }
        }
        FileWriteUtil.write(shFile, lines);
    }
}
