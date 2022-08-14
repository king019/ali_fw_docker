package com.k.docker.docker.amd.softmix.alibaba.mq.rocketmq.cluster.run;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.alibaba.mq.rocketmq.cluster.install.RocketMqClusterInstall;
import com.k.docker.docker.amd.softmix.alibaba.mq.rocketmq.cluster.build.RocketMqClusterBuild;
import com.k.docker.docker.amd.softmix.alibaba.mq.rocketmq.cluster.config.host.RocketmqClusterHost;
import com.k.docker.docker.amd.softmix.alibaba.mq.rocketmq.cluster.image.create.RocketMqClusterImageCreate;
import com.k.docker.docker.base.docker.run.DockerRunFileBase;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.docker.run.base.impl.DockerRunBaseImpl;
import com.k.docker.docker.util.docker.DockerVersion;
import com.k.docker.docker.util.file.FileWriteUtil;
import org.junit.Test;

import java.util.List;

public class RocketMqClusterRun extends DockerRunFileBase {

    @Test
    public void test() throws Exception {
        String rocketMqVersion = DockerVersion.rockmqSingleVersion;
        DockerRunBase dockerRun = new DockerRunBaseImpl();
        RocketmqClusterHost hostEntity = new RocketmqClusterHost();
        List<String> lines = Lists.newArrayList();
        String param = null;
        {
            FileWriteUtil.writeCdClassandCopySoft(lines, RocketMqClusterBuild.class);
        }
        {
            FileWriteUtil.writeDnsAndImage(lines, RocketMqClusterImageCreate.class);
        }
        RocketMqClusterInstall.dockerRun(RocketMqClusterBuild.class, lines, rocketMqVersion, dockerRun, hostEntity, param);
        FileWriteUtil.write(shFile, lines);
    }
}
