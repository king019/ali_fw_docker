package com.k.docker.docker.amd.softmix.apache.hadoop.zookeeper.zookeeper;

import com.k.docker.docker.amd.softmix.apache.hadoop.zookeeper.zookeeper.build.ZookeeperBuild;
import com.k.docker.docker.amd.softmix.apache.hadoop.zookeeper.zookeeper.image.create.ZookeeperImageCreate;
import com.k.docker.docker.amd.softmix.apache.hadoop.zookeeper.zookeeper.run.ZookeeperRun;
import com.k.docker.docker.amd.softmix.apache.hadoop.zookeeper.zookeeper.scan.ZookeeperWlanScan;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ZookeeperBuild.class, ZookeeperImageCreate.class, ZookeeperRun.class, ZookeeperWlanScan.class})
public class ZookeeperZookeeperAll {

}
