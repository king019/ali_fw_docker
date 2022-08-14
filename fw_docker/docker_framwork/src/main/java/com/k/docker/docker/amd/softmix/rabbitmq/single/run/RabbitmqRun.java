package com.k.docker.docker.amd.softmix.rabbitmq.single.run;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.rabbitmq.single.install.RabbitmqSingleInstall;
import com.k.docker.docker.amd.softmix.rabbitmq.single.build.RabbitmqBuild;
import com.k.docker.docker.amd.softmix.rabbitmq.single.config.host.RabbitmqHost;
import com.k.docker.docker.amd.softmix.rabbitmq.single.image.create.RabbitmqImageCreate;
import com.k.docker.docker.base.docker.run.DockerRunFileBase;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.docker.run.base.impl.DockerRunBaseImpl;
import com.k.docker.docker.util.docker.DockerVersion;
import com.k.docker.docker.util.file.FileWriteUtil;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class RabbitmqRun extends DockerRunFileBase {

    @Test
    public void test() throws Exception {
        String rabbitmqrVersion = DockerVersion.rabbitmqVersion;
        DockerRunBase dockerRun = new DockerRunBaseImpl();
        RabbitmqHost dockerHost = new RabbitmqHost();
        List<String> lines = Lists.newArrayList();
        List<String> ports = Lists.newArrayList();
        Map<String, String> hostMap = dockerHost.docker_host_map();
        FileWriteUtil.writeCdClassandCopySoft(lines, RabbitmqBuild.class);
        FileWriteUtil.writeDnsAndImage(lines, RabbitmqImageCreate.class);
        String param = null;
        RabbitmqSingleInstall.dockerRun(RabbitmqBuild.class, lines, rabbitmqrVersion, dockerRun, dockerHost, param, hostMap, ports);
        FileWriteUtil.writeRun(shFile, lines);
    }
}
