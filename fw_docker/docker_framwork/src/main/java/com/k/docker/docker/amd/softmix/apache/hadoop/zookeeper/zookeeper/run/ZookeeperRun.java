package com.k.docker.docker.amd.softmix.apache.hadoop.zookeeper.zookeeper.run;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.apache.zookeeper.install.ZookeeperInstall;
import com.k.docker.docker.amd.soft.apache.zookeeperui.install.ZookeeperUIInstall;
import com.k.docker.docker.amd.softmix.apache.hadoop.zookeeper.zookeeper.build.ZookeeperBuild;
import com.k.docker.docker.amd.softmix.apache.hadoop.zookeeper.zookeeper.config.host.ZookeeperHost;
import com.k.docker.docker.amd.softmix.apache.hadoop.zookeeper.zookeeper.image.create.ZookeeperImageCreate;
import com.k.docker.docker.amd.softmix.apache.hadoop.zookeeper.zookeeperui.build.ZookeeperUIBuild;
import com.k.docker.docker.base.docker.run.DockerRunFileBase;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.docker.run.base.impl.DockerRunBaseImpl;
import com.k.docker.docker.util.docker.DockerVersion;
import com.k.docker.docker.util.file.FileWriteUtil;
import org.junit.Test;

import java.util.List;

public class ZookeeperRun extends DockerRunFileBase {

    @Test
    public void test() throws Exception {
        String zooeeperVersion = DockerVersion.zookeeperVersion;
        String zooeeperUIVersion = DockerVersion.zookeepeUIVersion;
        DockerRunBase dockerRun = new DockerRunBaseImpl();
        ZookeeperHost hostEntity = new ZookeeperHost();
        List<String> lines = Lists.newArrayList();
        String param = null;
            FileWriteUtil.writeCdClassandCopySoft(lines, ZookeeperBuild.class, ZookeeperUIBuild.class);
            FileWriteUtil.writeDnsAndImage(lines, ZookeeperImageCreate.class);
        ZookeeperInstall.dockerRun(ZookeeperBuild.class, lines, zooeeperVersion, dockerRun, hostEntity.zookeeper_zookeeper(), param);
        ZookeeperUIInstall.dockerRun(ZookeeperUIBuild.class, lines, zooeeperUIVersion, dockerRun, hostEntity.zookeeper_ui(), param);
        FileWriteUtil.write(shFile, lines);
    }
}
