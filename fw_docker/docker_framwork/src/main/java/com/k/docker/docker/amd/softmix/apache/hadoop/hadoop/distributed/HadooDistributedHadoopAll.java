package com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.distributed;

import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.distributed.build.HadoopDistributedBuild;
import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.distributed.command.HadoopDistributedCommand;
import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.distributed.image.create.HadoopDistributedImageCreate;
import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.distributed.run.HadoopDistributedRun;
import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.distributed.scan.HadoopDistributedWlanScan;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({HadoopDistributedBuild.class, HadoopDistributedRun.class, HadoopDistributedImageCreate.class, HadoopDistributedWlanScan.class, HadoopDistributedCommand.class})
public class HadooDistributedHadoopAll {

}
