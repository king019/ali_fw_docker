package com.k.docker.docker.amd.softmix.apache.hadoop.hadoop;

import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.pseudo_distributed.HadooPseudoDistributedHadoopAll;
import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.single.HadoopSingleAll;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({HadoopSingleAll.class, HadooPseudoDistributedHadoopAll.class})
public class HadoopHadoopAll {

}
