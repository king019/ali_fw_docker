package com.k.docker.docker.amd.softmix.apache.hadoop.hbase.pseudo_distributed.zookeeper.build;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.apache.zookeeper.install.ZookeeperInstall;
import com.k.docker.docker.amd.soft.jdk.JdkInstall;
import com.k.docker.docker.amd.softmix.apache.hadoop.hbase.pseudo_distributed.hbase.config.host.HbasePseudoDistributedHost;
import com.k.docker.docker.base.soft.base.DockerBase;
import com.k.docker.docker.base.softmix.adapter.SoftMixAdapter;

import java.util.List;

public class HbasePseudoDistributedZookeeperBuild extends SoftMixAdapter {
    @Override
    public List<DockerBase> queryDocks() {
        HbasePseudoDistributedHost stormHost = new HbasePseudoDistributedHost();
        dockerBases = Lists.newArrayList();
        dockerBases.add(new JdkInstall());
        dockerBases.add(new ZookeeperInstall(stormHost.hbase_zookeeper()));
        return dockerBases;
    }
}
