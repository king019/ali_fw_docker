package com.k.docker.docker.amd.softmix.apache.hadoop.hbase.pseudo_distributed;

import com.k.docker.docker.amd.softmix.apache.hadoop.hbase.pseudo_distributed.hbase.HbasePseudoDistributedHbaseAll;
import com.k.docker.docker.amd.softmix.apache.hadoop.hbase.pseudo_distributed.zookeeper.HbasePseudoDistributedZookeeperAll;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({HbasePseudoDistributedHbaseAll.class, HbasePseudoDistributedZookeeperAll.class})
public class HbasePseudoDistributedAll {

}
