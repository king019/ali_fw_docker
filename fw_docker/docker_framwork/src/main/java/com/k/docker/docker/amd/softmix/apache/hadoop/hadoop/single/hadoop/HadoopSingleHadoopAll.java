package com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.single.hadoop;

import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.single.hadoop.build.HadoopSingleBuild;
import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.single.hadoop.image.create.HadoopSingleImageCreate;
import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.single.hadoop.run.HadoopSingleRun;
import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.single.hadoop.scan.HadoopSingleWlanScan;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({HadoopSingleBuild.class, HadoopSingleRun.class, HadoopSingleImageCreate.class, HadoopSingleWlanScan.class})
public class HadoopSingleHadoopAll {

}
