package com.k.docker.docker.amd.softmix.apache.hadoop.hbase.single.hbase;

import com.k.docker.docker.amd.softmix.apache.hadoop.hbase.single.hbase.build.HbaseSingleBuild;
import com.k.docker.docker.amd.softmix.apache.hadoop.hbase.single.hbase.image.create.HbaseSingleImageCreate;
import com.k.docker.docker.amd.softmix.apache.hadoop.hbase.single.hbase.run.HbaseSingleRun;
import com.k.docker.docker.amd.softmix.apache.hadoop.hbase.single.hbase.scan.HbaseSingleWlanScan;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({HbaseSingleBuild.class, HbaseSingleRun.class, HbaseSingleImageCreate.class, HbaseSingleWlanScan.class})
public class HbaseSingleHbaseAll {

}
