package com.k.docker.docker.amd.softmix.proxy.squid.single.run;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.proxy.squid.single.install.SquidSingleInstall;
import com.k.docker.docker.amd.softmix.proxy.squid.single.build.SquidBuild;
import com.k.docker.docker.amd.softmix.proxy.squid.single.config.host.SquidHost;
import com.k.docker.docker.amd.softmix.proxy.squid.single.image.create.SquidImageCreate;
import com.k.docker.docker.base.docker.run.DockerRunFileBase;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.docker.run.base.impl.DockerRunBaseImpl;
import com.k.docker.docker.util.docker.DockerVersion;
import com.k.docker.docker.util.file.FileWriteUtil;
import org.junit.Test;

import java.util.List;

public class SquidRun extends DockerRunFileBase {

    @Test
    public void test() throws Exception {
        String squidrVersion = DockerVersion.squidSingleVersion;
        DockerRunBase dockerRun = new DockerRunBaseImpl();
        SquidHost dockerHost = new SquidHost();
        List<String> lines = Lists.newArrayList();
        String param = null;
        {
            FileWriteUtil.writeCdClassandCopySoft(lines, SquidBuild.class);
        }
        {
            FileWriteUtil.writeDnsAndImage(lines, SquidImageCreate.class);
        }
        SquidSingleInstall.dockerRun(SquidBuild.class, lines, squidrVersion, dockerRun, dockerHost.docker_all(), param);
        FileWriteUtil.writeShellChmod777(shFile, lines);
    }
}
