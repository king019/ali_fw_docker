package com.k.docker.docker.amd.softmix.apache.hadoop.hbase.single.hbase.run;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.apache.hadoop.hbase.single.install.HbaseSingleInstall;
import com.k.docker.docker.amd.soft.apache.zookeeper.install.ZookeeperInstall;
import com.k.docker.docker.amd.softmix.apache.hadoop.hbase.single.hbase.build.HbaseSingleBuild;
import com.k.docker.docker.amd.softmix.apache.hadoop.hbase.single.hbase.config.host.HbaseSingleHost;
import com.k.docker.docker.amd.softmix.apache.hadoop.hbase.single.hbase.image.create.HbaseSingleImageCreate;
import com.k.docker.docker.amd.softmix.apache.hadoop.hbase.single.zookeeper.build.HbaseZookeeperBuild;
import com.k.docker.docker.base.docker.run.DockerRunFileBase;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.docker.run.base.impl.DockerRunBaseImpl;
import com.k.docker.docker.util.docker.DockerVersion;
import com.k.docker.docker.util.file.FileWriteUtil;
import org.junit.Test;

import java.util.List;

public class HbaseSingleRun extends DockerRunFileBase {

    @Test
    public void test() throws Exception {
        String zooeeperVersion = DockerVersion.zookeeperVersion;
        String hbaseVersion = DockerVersion.hbaseSingleVersion;
        DockerRunBase dockerRun = new DockerRunBaseImpl();
        HbaseSingleHost hostEntity = new HbaseSingleHost();
        List<String> lines = Lists.newArrayList();
        String param = null;
        {
            // HbaseSingleImageCreate
            FileWriteUtil.writeCdClassandCopySoft(lines, HbaseSingleBuild.class, HbaseZookeeperBuild.class);
        }
        {
            FileWriteUtil.writeDnsAndImage(lines, HbaseSingleImageCreate.class);
        }
        ZookeeperInstall.dockerRun(HbaseZookeeperBuild.class, lines, zooeeperVersion, dockerRun, hostEntity.hbase_zookeeper(), param);
        HbaseSingleInstall.dockerRun(HbaseSingleBuild.class, lines, hbaseVersion, dockerRun, hostEntity.hbase_single(), param);
        FileWriteUtil.write(shFile, lines);
    }
}
