package com.k.docker.docker.amd.softmix.rabbitmq.cluster;

import com.k.docker.docker.amd.softmix.rabbitmq.cluster.build.RabbitmqClusterBuild;
import com.k.docker.docker.amd.softmix.rabbitmq.cluster.image.create.RabbitmqClusterImageCreate;
import com.k.docker.docker.amd.softmix.rabbitmq.cluster.run.RabbitmqClusterRun;
import com.k.docker.docker.amd.softmix.rabbitmq.cluster.scan.RabbitmqClusterWlanScan;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({RabbitmqClusterBuild.class, RabbitmqClusterImageCreate.class, RabbitmqClusterRun.class, RabbitmqClusterWlanScan.class})
public class RabbitmqClusterAll {

}