package com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.pseudo_distributed;

import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.pseudo_distributed.build.HadoopPseudoDistributedBuild;
import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.pseudo_distributed.command.HadoopPseudoDistributedCommand;
import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.pseudo_distributed.image.create.HadoopPseudoDistributedImageCreate;
import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.pseudo_distributed.run.HadoopPseudoDistributedRun;
import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.pseudo_distributed.scan.HadoopPseudoDistributedWlanScan;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({HadoopPseudoDistributedBuild.class, HadoopPseudoDistributedRun.class, HadoopPseudoDistributedImageCreate.class, HadoopPseudoDistributedWlanScan.class, HadoopPseudoDistributedCommand.class})
public class HadooPseudoDistributedHadoopAll {

}
