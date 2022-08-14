package com.k.docker.docker.amd.softmix.apache.hadoop.hbase.pseudo_distributed.hbase;

import com.k.docker.docker.amd.softmix.apache.hadoop.hbase.pseudo_distributed.hbase.build.HbasePseudoDistributedBuild;
import com.k.docker.docker.amd.softmix.apache.hadoop.hbase.pseudo_distributed.hbase.image.create.HbasePseudoDistributedImageCreate;
import com.k.docker.docker.amd.softmix.apache.hadoop.hbase.pseudo_distributed.hbase.run.HbasePseudoDistributedRun;
import com.k.docker.docker.amd.softmix.apache.hadoop.hbase.pseudo_distributed.hbase.scan.HbasePseudoDistributedWlanScan;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({HbasePseudoDistributedBuild.class, HbasePseudoDistributedRun.class, HbasePseudoDistributedImageCreate.class, HbasePseudoDistributedWlanScan.class})
public class HbasePseudoDistributedHbaseAll {

}
