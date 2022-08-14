package com.k.docker.docker.amd.softmix.apache.hadoop.hbase.single;

import com.k.docker.docker.amd.softmix.apache.hadoop.hbase.single.hbase.HbaseSingleHbaseAll;
import com.k.docker.docker.amd.softmix.apache.hadoop.hbase.single.zookeeper.HbaseZookeeperAll;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({HbaseSingleHbaseAll.class, HbaseZookeeperAll.class})
public class HbaseSingleAll {

}
