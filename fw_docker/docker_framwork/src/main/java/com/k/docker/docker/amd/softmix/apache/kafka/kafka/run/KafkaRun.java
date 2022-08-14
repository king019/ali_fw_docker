package com.k.docker.docker.amd.softmix.apache.kafka.kafka.run;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.apache.kafka.cluster.install.KafkaInstall;
import com.k.docker.docker.amd.soft.apache.zookeeper.install.ZookeeperInstall;
import com.k.docker.docker.amd.softmix.apache.kafka.kafka.build.KafkaBuild;
import com.k.docker.docker.amd.softmix.apache.kafka.kafka.config.host.KafkaHost;
import com.k.docker.docker.amd.softmix.apache.kafka.kafka.image.create.KafkaImageCreate;
import com.k.docker.docker.amd.softmix.apache.kafka.zookeeper.build.KafkaZookeeperBuild;
import com.k.docker.docker.base.docker.run.DockerRunFileBase;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.docker.run.base.impl.DockerRunBaseImpl;
import com.k.docker.docker.util.docker.DockerVersion;
import com.k.docker.docker.util.file.FileWriteUtil;
import org.junit.Test;

import java.util.List;

public class KafkaRun extends DockerRunFileBase {

    @Test
    public void test() throws Exception {
        final String kafkaVersion = DockerVersion.kafkaClusterVersion;
        final String zooeeperVersion = DockerVersion.zookeeperVersion;
        final DockerRunBase dockerRun = new DockerRunBaseImpl();
        final KafkaHost hostConfig = new KafkaHost();
        final List<String> lines = Lists.newArrayList();
        final String param = null;
        {
            FileWriteUtil.writeCdClassandCopySoft(lines, KafkaZookeeperBuild.class, KafkaBuild.class);
        }
        {
            FileWriteUtil.writeDnsAndImage(lines, KafkaImageCreate.class);
        }
        // zookeeper
        {
            ZookeeperInstall.dockerRun(KafkaZookeeperBuild.class, lines, zooeeperVersion, dockerRun, hostConfig.kafka_zookeeper(), param);
        }
        // kafka
        {
            KafkaInstall.dockerRun(KafkaBuild.class, lines, kafkaVersion, dockerRun, hostConfig.kafka_kafka(), param);
        }
        FileWriteUtil.write(shFile, lines);
    }
}
