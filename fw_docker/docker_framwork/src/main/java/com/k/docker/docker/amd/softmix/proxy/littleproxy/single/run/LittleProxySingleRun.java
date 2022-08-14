package com.k.docker.docker.amd.softmix.proxy.littleproxy.single.run;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.proxy.single.littleproxy.install.LittleProxySingleInstall;
import com.k.docker.docker.amd.softmix.proxy.littleproxy.single.build.LittleProxySingleBuild;
import com.k.docker.docker.amd.softmix.proxy.littleproxy.single.config.host.LittleProxyHost;
import com.k.docker.docker.amd.softmix.proxy.littleproxy.single.image.create.LittleProxySingleImageCreate;
import com.k.docker.docker.base.docker.run.DockerRunFileBase;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.docker.run.base.impl.DockerRunBaseImpl;
import com.k.docker.docker.util.docker.DockerVersion;
import com.k.docker.docker.util.file.FileWriteUtil;
import org.junit.Test;

import java.util.List;

public class LittleProxySingleRun extends DockerRunFileBase {

    @Test
    public void test() throws Exception {
        String version = DockerVersion.littleProxyVersion;
        DockerRunBase dockerRun = new DockerRunBaseImpl();
        LittleProxyHost dockerHost = new LittleProxyHost();
        List<String> lines = Lists.newArrayList();
        String param = null;
        {
            FileWriteUtil.writeDnsAndImage(lines, LittleProxySingleImageCreate.class);
        }
        LittleProxySingleInstall.dockerRun(LittleProxySingleBuild.class, lines, version, dockerRun, dockerHost.proxy_single(), param);
        FileWriteUtil.writeShellChmod777(shFile, lines);
    }
}
