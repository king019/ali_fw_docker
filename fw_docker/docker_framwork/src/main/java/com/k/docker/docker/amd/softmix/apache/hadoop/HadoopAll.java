package com.k.docker.docker.amd.softmix.apache.hadoop;

import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.HadoopHadoopAll;
import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.distributed.HadooDistributedHadoopAll;
import com.k.docker.docker.amd.softmix.apache.hadoop.hbase.pseudo_distributed.HbasePseudoDistributedAll;
import com.k.docker.docker.amd.softmix.apache.hadoop.hbase.single.HbaseSingleAll;
import com.k.docker.docker.amd.softmix.apache.hadoop.zookeeper.ZookeeperAll;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({HadoopHadoopAll.class, HbaseSingleAll.class, HbasePseudoDistributedAll.class, ZookeeperAll.class, HadooDistributedHadoopAll.class})
public class HadoopAll {

}
