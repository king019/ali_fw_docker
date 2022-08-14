package com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.pseudo_distributed.build;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.apache.hadoop.hadoop.pseudo_distributed.install.HadoopPseudoDistributedInstall;
import com.k.docker.docker.amd.soft.jdk.JdkInstall;
import com.k.docker.docker.base.soft.base.DockerBase;
import com.k.docker.docker.base.softmix.adapter.SoftMixAdapter;

import java.util.List;

public class HadoopPseudoDistributedBuild extends SoftMixAdapter {
    @Override
    public List<DockerBase> queryDocks() {
        dockerBases = Lists.newArrayList();
        dockerBases.add(new JdkInstall());
        dockerBases.add(new HadoopPseudoDistributedInstall());
        return dockerBases;
    }
}
