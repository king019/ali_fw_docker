package com.k.docker.docker.amd.softmix.alibaba.dubbo.dubbo.run;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.alibaba.dubbo.install.DubboInstall;
import com.k.docker.docker.amd.soft.apache.zookeeper.install.ZookeeperInstall;
import com.k.docker.docker.amd.soft.apache.zookeeperui.install.ZookeeperUIInstall;
import com.k.docker.docker.amd.softmix.alibaba.dubbo.dubbo.build.DubboBuild;
import com.k.docker.docker.amd.softmix.alibaba.dubbo.dubbo.config.host.DubboHost;
import com.k.docker.docker.amd.softmix.alibaba.dubbo.dubbo.image.create.DubboImageCreate;
import com.k.docker.docker.amd.softmix.alibaba.dubbo.zookeeper.build.DubboZookeeperBuild;
import com.k.docker.docker.amd.softmix.alibaba.dubbo.zookeeperui.build.DubboZookeeperUIBuild;
import com.k.docker.docker.base.docker.run.DockerRunFileBase;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.docker.run.base.impl.DockerRunBaseImpl;
import com.k.docker.docker.util.docker.DockerVersion;
import com.k.docker.docker.util.file.FileWriteUtil;
import org.junit.Test;

import java.util.List;

public class DubboRun extends DockerRunFileBase {

    @Test
    public void test() throws Exception {
        String zooeeperVersion = DockerVersion.zookeeperVersion;
        String dubboVersion = DockerVersion.dubboClusterVersion;
        String zookeepeUIVersion = DockerVersion.zookeepeUIVersion;
        DockerRunBase dockerRun = new DockerRunBaseImpl();
        DubboHost dubboHost = new DubboHost();
        List<String> lines = Lists.newArrayList();
        String param = null;
        {
            FileWriteUtil.writeCdClassandCopySoft(lines, DubboZookeeperBuild.class, DubboBuild.class, DubboZookeeperUIBuild.class);
        }
        {
            FileWriteUtil.writeDnsAndImage(lines, DubboImageCreate.class);
        }
        ZookeeperInstall.dockerRun(DubboZookeeperBuild.class, lines, zooeeperVersion, dockerRun, dubboHost, param);
        ZookeeperUIInstall.dockerRun(DubboZookeeperUIBuild.class, lines, zookeepeUIVersion, dockerRun, dubboHost.dubbo_zkui(), param);
        DubboInstall.dockerRun(DubboBuild.class, lines, dubboVersion, dockerRun, dubboHost, param);
        FileWriteUtil.writeShellChmod777(shFile, lines);
    }
}
