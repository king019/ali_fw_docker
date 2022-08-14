package com.k.docker.docker.amd.softmix.alibaba.rocketmq.rocketmq.run;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.softmix.alibaba.rocketmq.rocketmq.build.RocketMqBuild;
import com.k.docker.docker.amd.softmix.alibaba.rocketmq.rocketmq.config.host.RocketmqHost;
import com.k.docker.docker.amd.softmix.alibaba.rocketmq.rocketmq.image.create.RocketMqImageCreate;
import com.k.docker.docker.base.docker.run.DockerRunFileBase;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.docker.run.base.impl.DockerRunBaseImpl;
import com.k.docker.docker.util.docker.DockerVersion;
import com.k.docker.docker.util.file.FileWriteUtil;
import org.junit.Test;

import java.util.List;

public class RocketMqRun extends DockerRunFileBase {

    @Test
    public void test() throws Exception {
        final String rocketMqVersion = DockerVersion.rockmqClusterVersion;
        final DockerRunBase dockerRun = new DockerRunBaseImpl();
        final RocketmqHost hostEntity = new RocketmqHost();
        final List<String> lines = Lists.newArrayList();
        String param = null;
        int index = 0;
        List<String> hosts;
        {
            FileWriteUtil.writeCdClassandCopySoft(lines, RocketMqBuild.class);
        }
        {
            FileWriteUtil.writeDnsAndImage(lines, RocketMqImageCreate.class);
        }
        {
            index = 0;
            hosts = hostEntity.rocketmq_nameServer();
            for (final String host : hosts) {
                param = "mqnamesrv";
                dockerRun.dockerRun(lines, rocketMqVersion, host, param);
            }
        }
        {
            index = 0;
            hosts = hostEntity.rocketmq_master();
            for (final String host : hosts) {
                param = "mqbrokerMaster" + index++;
                dockerRun.dockerRun(lines, rocketMqVersion, host, param);
            }
        }
        {
            index = 0;
            hosts = hostEntity.rocketmq_slave();
            for (final String host : hosts) {
                param = "mqbrokerSlave" + index++;
                dockerRun.dockerRun(lines, rocketMqVersion, host, param);
            }
        }
        {
            hosts = hostEntity.rocketmq_web();
            param = "console";
            for (final String host : hosts) {
                dockerRun.dockerRun(lines, rocketMqVersion, host, param, "500m");
            }
        }
        FileWriteUtil.write(shFile, lines);
    }
}
