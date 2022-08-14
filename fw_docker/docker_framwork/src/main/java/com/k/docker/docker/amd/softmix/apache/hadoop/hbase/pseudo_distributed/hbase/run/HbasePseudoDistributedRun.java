package com.k.docker.docker.amd.softmix.apache.hadoop.hbase.pseudo_distributed.hbase.run;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.apache.hadoop.hbase.pseudo_distributed.install.HbasePseudoDistributedInstall;
import com.k.docker.docker.amd.soft.apache.zookeeper.install.ZookeeperInstall;
import com.k.docker.docker.amd.softmix.apache.hadoop.hbase.pseudo_distributed.hbase.build.HbasePseudoDistributedBuild;
import com.k.docker.docker.amd.softmix.apache.hadoop.hbase.pseudo_distributed.hbase.config.host.HbasePseudoDistributedHost;
import com.k.docker.docker.amd.softmix.apache.hadoop.hbase.pseudo_distributed.hbase.image.create.HbasePseudoDistributedImageCreate;
import com.k.docker.docker.amd.softmix.apache.hadoop.hbase.pseudo_distributed.zookeeper.build.HbasePseudoDistributedZookeeperBuild;
import com.k.docker.docker.base.docker.run.DockerRunFileBase;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.docker.run.base.impl.DockerRunBaseImpl;
import com.k.docker.docker.util.docker.DockerVersion;
import com.k.docker.docker.util.file.FileWriteUtil;
import org.junit.Test;

import java.util.List;

public class HbasePseudoDistributedRun extends DockerRunFileBase {

    @Test
    public void test() throws Exception {
        String zooeeperVersion = DockerVersion.zookeeperVersion;
        String hbaseVersion = DockerVersion.hbasePseudoDistributedVersion;
        DockerRunBase dockerRun = new DockerRunBaseImpl();
        HbasePseudoDistributedHost hostEntity = new HbasePseudoDistributedHost();
        List<String> lines = Lists.newArrayList();
        String param = null;
        {
            // HbaseSingleImageCreate
            FileWriteUtil.writeCdClassandCopySoft(lines, HbasePseudoDistributedBuild.class, HbasePseudoDistributedZookeeperBuild.class);
        }
        {
            FileWriteUtil.writeDnsAndImage(lines, HbasePseudoDistributedImageCreate.class);
        }
        ZookeeperInstall.dockerRun(HbasePseudoDistributedZookeeperBuild.class, lines, zooeeperVersion, dockerRun, hostEntity.hbase_zookeeper(), param);
        HbasePseudoDistributedInstall.dockerRun(HbasePseudoDistributedBuild.class, lines, hbaseVersion, dockerRun, hostEntity.hbase_single(), param);
        FileWriteUtil.write(shFile, lines);
    }
}
