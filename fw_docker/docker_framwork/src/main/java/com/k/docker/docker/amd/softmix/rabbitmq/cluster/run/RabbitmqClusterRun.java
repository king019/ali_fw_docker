package com.k.docker.docker.amd.softmix.rabbitmq.cluster.run;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.rabbitmq.cluster.install.RabbitmqClusterInstall;
import com.k.docker.docker.amd.softmix.rabbitmq.cluster.build.RabbitmqClusterBuild;
import com.k.docker.docker.amd.softmix.rabbitmq.cluster.config.host.RabbitmqClusterHost;
import com.k.docker.docker.amd.softmix.rabbitmq.cluster.image.create.RabbitmqClusterImageCreate;
import com.k.docker.docker.base.docker.run.DockerRunFileBase;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.docker.run.base.impl.DockerRunBaseImpl;
import com.k.docker.docker.util.docker.DockerVersion;
import com.k.docker.docker.util.file.FileWriteUtil;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class RabbitmqClusterRun extends DockerRunFileBase {

    @Test
    public void test() throws Exception {
        String rabbitmqrVersion = DockerVersion.rabbitmqVersion;
        DockerRunBase dockerRun = new DockerRunBaseImpl();
        RabbitmqClusterHost dockerHost = new RabbitmqClusterHost();
        List<String> lines = Lists.newArrayList();
        List<String> ports = Lists.newArrayList();
        Map<String, String> hostMap = dockerHost.docker_host_map();
        FileWriteUtil.writeCdClassandCopySoft(lines, RabbitmqClusterBuild.class);
        FileWriteUtil.writeDnsAndImage(lines, RabbitmqClusterImageCreate.class);
        String param = null;
        RabbitmqClusterInstall.dockerRun(RabbitmqClusterBuild.class, lines, rabbitmqrVersion, dockerRun, dockerHost, param, hostMap, ports);
        FileWriteUtil.writeRun(shFile, lines);
    }
}
