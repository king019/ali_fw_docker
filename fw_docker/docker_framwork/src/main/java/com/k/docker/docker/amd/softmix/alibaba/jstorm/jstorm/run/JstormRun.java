package com.k.docker.docker.amd.softmix.alibaba.jstorm.jstorm.run;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.softmix.alibaba.jstorm.jstorm.build.JstromBuild;
import com.k.docker.docker.amd.softmix.alibaba.jstorm.jstorm.config.host.JstormHost;
import com.k.docker.docker.amd.softmix.alibaba.jstorm.jstorm.image.create.JstormImageCreate;
import com.k.docker.docker.amd.softmix.alibaba.jstorm.zookeeper.build.JstormZookeeperBuild;
import com.k.docker.docker.base.docker.run.DockerRunFileBase;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.docker.run.base.impl.DockerRunBaseImpl;
import com.k.docker.docker.util.docker.DockerVersion;
import com.k.docker.docker.util.file.FileWriteUtil;
import com.k.docker.docker.util.path.FWPathUtil;
import org.junit.Test;

import java.util.List;

public class JstormRun extends DockerRunFileBase {

    @Test
    public void test() throws Exception {
        String zooeeperVersion = DockerVersion.zookeeperVersion;
        String jstromVersion = DockerVersion.jstromVersion;
        DockerRunBase dockerRun = new DockerRunBaseImpl();
        JstormHost jstromHost = new JstormHost();
        List<String> lines = Lists.newArrayList();
        String param = null;
        List<String> hosts;
        {
            FileWriteUtil.writeCdClassandCopySoft(lines, JstromBuild.class, JstormZookeeperBuild.class);
        }
        dockerRun.dockerLines(lines, JstormImageCreate.class);
        // dnsImage(lines, JstormDns.class, JstormDnsDelete.class, JstormImageRemove.class, JstormImageCreate.class, JstormNoCreateImageRun.class, createImage, imageContain);
        lines.add("cd " + FWPathUtil.getClassPath(JstormZookeeperBuild.class));
        // zookeeper
        {
            hosts = jstromHost.jstorm_zookeeper();
            for (String host : hosts) {
                param = host.substring(host.lastIndexOf("zk") + 2);
                dockerRun.dockerRun(lines, zooeeperVersion, host, param);
            }
        }
        {
            // lines.add("sleep 30");
        }
        lines.add("cd " + FWPathUtil.getClassPath(JstromBuild.class));
        // ui
        {
            param = "ui";
            hosts = jstromHost.jstorm_ui();
            for (String host : hosts) {
                dockerRun.dockerRun(lines, jstromVersion, host, param, "500m");
            }
        }
        // nimbus
        {
            param = "jstorm  nimbus";
            hosts = jstromHost.jstorm_nimbus();
            for (String host : hosts) {
                dockerRun.dockerRun(lines, jstromVersion, host, param, "800m");
            }
        }
        {
            // lines.add("sleep 30");
        }
        // supervisor
        {
            param = " jstorm supervisor";
            hosts = jstromHost.jstorm_supervisor();
            for (String host : hosts) {
                dockerRun.dockerRun(lines, jstromVersion, host, param, "800m");
            }
        }
        FileWriteUtil.write(shFile, lines);
    }
}
