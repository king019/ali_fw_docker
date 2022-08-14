package com.k.docker.docker.amd.softmix.apache.hadoop.hbase.pseudo_distributed.hbase.build;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.apache.hadoop.hbase.pseudo_distributed.install.HbasePseudoDistributedInstall;
import com.k.docker.docker.amd.soft.jdk.JdkInstall;
import com.k.docker.docker.base.soft.base.DockerBase;
import com.k.docker.docker.base.softmix.adapter.SoftMixAdapter;

import java.util.List;

public class HbasePseudoDistributedBuild extends SoftMixAdapter {
    @Override
    public List<DockerBase> queryDocks() {
        dockerBases = Lists.newArrayList();
        dockerBases.add(new JdkInstall());
        dockerBases.add(new HbasePseudoDistributedInstall());
        return dockerBases;
    }
}
