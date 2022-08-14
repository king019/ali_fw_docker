package com.k.docker.docker.amd.soft.apache.kafka.cluster.install;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.apache.zookeeper.install.ZookeeperInstall;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.soft.base.DockerBase;
import com.k.docker.docker.base.soft.base.impl.DockerBaseImpl;
import com.k.docker.docker.util.docker.SoftVersion;
import com.k.docker.docker.util.file.FileWriteUtil;
import com.k.docker.docker.util.file.ShellWriteUtil;
import com.k.docker.docker.util.path.FWPathUtil;
import com.k.docker.docker.util.props.PropsDockerUtil;
import com.k.docker.docker.util.vo.DockerBuildVO;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.List;

public class KafkaInstall extends DockerBaseImpl {
    public KafkaInstall(final List<String> hosts) {
        dockerBuildVOs.add(SoftVersion.zookeeperSoft);
        dockerBuildVOs.add(SoftVersion.kafkaSoft);
        this.hosts = hosts;
    }

    public static void dockerRun(final Class clazz, final List<String> lines, final String kafkaVersion, final DockerRunBase dockerRun, final List<String> hosts, String param) {
        lines.add("cd " + FWPathUtil.getClassPath(clazz));
        {
            for (final String host : hosts) {
                param = host.substring(host.lastIndexOf("ka") + 2) + " kafka";
                dockerRun.dockerRun(lines, kafkaVersion, host, param, "500m");
            }
        }
    }

    @Override
    public void dockerShell(final File file) throws Exception {
        final String compareParam = "$2";
        for (final DockerBuildVO dockerBuildVO : dockerBuildVOs) {
            final String tar = dockerBuildVO.getTar();
            final String mvDirc = dockerBuildVO.getMvDir();
            ShellWriteUtil.writeIfStartParam(file, mvDirc, compareParam);
            if (tar.contains("zookeeper")) {
                dealZookeeper(file);
            } else {
                shellServer(file);
            }
            ShellWriteUtil.writeIfEnd(file);
        }
    }

    private void dealZookeeper(final File file) throws Exception {
        ZookeeperInstall.builder().build().dockerShell(file);
    }

    private void shellServer(final File file) throws Exception {
        final DockerBuildVO dockerSoft = queryTar("kafka");
        ShellWriteUtil.writeTar(file, dockerSoft.tar);
        ShellWriteUtil.writeMv(file, dockerSoft.unpress, dockerSoft.mvDir);
        ShellWriteUtil.writeChmod(file);
        final List<String> lines = Lists.newArrayList();
        lines.add("nohup " + PropsDockerUtil.dockerDir + dockerSoft.mvDir + "/bin/kafka-server-start.sh " + PropsDockerUtil.dockerDir + "$1server.properties &");
        ShellWriteUtil.writeListTail(file, lines);
    }

    @Override
    public List<File> buildConfigFile() {
        final String zookeeper;
        final List<File> files = Lists.newArrayList();
        try {
            final List<String> lines;
            lines = Lists.newArrayList();
            zookeeper = StringUtils.join(hosts, ":2181,") + ":2181";
            buildZoo(lines, zookeeper, files);
            for (int id = 0; id < 10; id++) {
                buildServer(id, lines, zookeeper, files);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return files;
    }

    private void buildZoo(final List<String> lines, final String zookeeper, final List<File> files) throws Exception {
        ZookeeperInstall.builder().build().buildConfigFile(files, hosts);
    }

    private void buildServer(final int id, List<String> lines, final String zookeeper, final List<File> files) throws Exception {
        {
            final File zoo = File.createTempFile(id + "server.properties" + DockerBase.tempSub, DockerBase.tempSub);
            {
                lines = Lists.newArrayList();
                lines.add("broker.id=" + id);
                lines.add("num.network.threads=1");
                lines.add("num.io.threads=1");
                lines.add("socket.send.buffer.bytes=102400");
                lines.add("socket.receive.buffer.bytes=102400");
                lines.add("socket.request.max.bytes=104857600");
                lines.add("log.dirs=/tmp/kafka-logs");
                lines.add("num.partitions=1");
                lines.add("num.recovery.threads.per.data.dir=1");
                lines.add("offsets.topic.replication.factor=1");
                lines.add("transaction.state.log.replication.factor=1");
                lines.add("transaction.state.log.min.isr=1");
                lines.add("log.retention.hours=1");
                lines.add("log.segment.bytes=1073741824");
                lines.add("log.retention.check.interval.ms=300000");
                lines.add("zookeeper.connect=" + zookeeper);
                lines.add("zookeeper.connection.timeout.ms=60000");
                lines.add("group.initial.rebalance.delay.ms=2000");
                FileWriteUtil.writeLines(zoo, lines);
            }
            files.add(zoo);
        }
    }
}
