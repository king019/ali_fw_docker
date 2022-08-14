package com.k.docker.docker.amd.softmix.apache.hadoop.zookeeper;

import com.k.docker.docker.amd.softmix.apache.hadoop.zookeeper.zookeeper.ZookeeperZookeeperAll;
import com.k.docker.docker.amd.softmix.apache.hadoop.zookeeper.zookeeperui.ZookeeperZookeeperUIAll;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ZookeeperZookeeperAll.class, ZookeeperZookeeperUIAll.class})
public class ZookeeperAll {

}
