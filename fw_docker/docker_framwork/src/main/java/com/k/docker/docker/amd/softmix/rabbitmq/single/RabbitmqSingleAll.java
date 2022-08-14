package com.k.docker.docker.amd.softmix.rabbitmq.single;

import com.k.docker.docker.amd.softmix.rabbitmq.single.build.RabbitmqBuild;
import com.k.docker.docker.amd.softmix.rabbitmq.single.image.create.RabbitmqImageCreate;
import com.k.docker.docker.amd.softmix.rabbitmq.single.run.RabbitmqRun;
import com.k.docker.docker.amd.softmix.rabbitmq.single.scan.RabbitmqWlanScan;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({RabbitmqBuild.class, RabbitmqImageCreate.class, RabbitmqRun.class, RabbitmqWlanScan.class})
public class RabbitmqSingleAll {

}