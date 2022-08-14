package com.k.docker.docker.amd.softmix.server.spring.boot.run;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.server.spring.boot.show.install.ServerShowInstall;
import com.k.docker.docker.amd.softmix.server.spring.boot.build.ServerBootBuild;
import com.k.docker.docker.amd.softmix.server.spring.boot.config.host.ServerBootHost;
import com.k.docker.docker.amd.softmix.server.spring.boot.image.create.ServerBootImageCreate;
import com.k.docker.docker.base.docker.run.DockerRunFileBase;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.docker.run.base.impl.DockerRunBaseImpl;
import com.k.docker.docker.util.docker.DockerVersion;
import com.k.docker.docker.util.file.FileWriteUtil;
import org.junit.Test;

import java.util.List;

public class ServerShowRun extends DockerRunFileBase {

    @Test
    public void test() throws Exception {
        String version = DockerVersion.ServerShowVersion;
        DockerRunBase dockerRun = new DockerRunBaseImpl();
        ServerBootHost hots = new ServerBootHost();
        List<String> lines = Lists.newArrayList();
        String param = null;
        {
            FileWriteUtil.writeCdClassandCopySoft(lines, ServerBootBuild.class);
        }
        {
            FileWriteUtil.writeDnsAndImage(lines, ServerBootImageCreate.class);
        }
        ServerShowInstall.dockerRun(ServerBootBuild.class, lines, version, dockerRun, hots.docker_all(), param);
        FileWriteUtil.writeShellChmod777(shFile, lines);
    }
}
