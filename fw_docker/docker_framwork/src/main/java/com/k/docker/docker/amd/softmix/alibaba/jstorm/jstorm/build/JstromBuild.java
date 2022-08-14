package com.k.docker.docker.amd.softmix.alibaba.jstorm.jstorm.build;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.alibaba.jstorm.install.JstromInstall;
import com.k.docker.docker.amd.soft.apache.tomcat.install.TomcatInstall;
import com.k.docker.docker.amd.soft.jdk.JdkInstall;
import com.k.docker.docker.amd.softmix.alibaba.jstorm.jstorm.config.host.JstormHost;
import com.k.docker.docker.base.soft.base.DockerBase;
import com.k.docker.docker.base.softmix.adapter.SoftMixAdapter;

import java.util.List;

public class JstromBuild extends SoftMixAdapter {
    @Override
    public List<DockerBase> queryDocks() {
        JstormHost jstormHost = new JstormHost();
        dockerBases = Lists.newArrayList();
        dockerBases.add(new JdkInstall());
        dockerBases.add(new TomcatInstall(true, true, false));
        dockerBases.add(new JstromInstall(jstormHost.jstorm_zookeeper()));
        return dockerBases;
    }
}
