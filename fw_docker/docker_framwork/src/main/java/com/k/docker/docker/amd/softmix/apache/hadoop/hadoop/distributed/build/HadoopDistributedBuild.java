package com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.distributed.build;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.apache.hadoop.hadoop.distributed.install.HadoopDistributedInstall;
import com.k.docker.docker.amd.soft.jdk.JdkInstall;
import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.distributed.config.host.HadoopDistributedHost;
import com.k.docker.docker.base.soft.base.DockerBase;
import com.k.docker.docker.base.softmix.adapter.SoftMixAdapter;

import java.util.List;

public class HadoopDistributedBuild extends SoftMixAdapter {
    @Override
    public List<DockerBase> queryDocks() {
        HadoopDistributedHost host = new HadoopDistributedHost();
        dockerBases = Lists.newArrayList();
        dockerBases.add(new JdkInstall());
        dockerBases.add(new HadoopDistributedInstall(host.docker_all()));
        return dockerBases;
    }
}
