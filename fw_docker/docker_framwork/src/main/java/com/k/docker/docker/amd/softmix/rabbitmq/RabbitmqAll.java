package com.k.docker.docker.amd.softmix.rabbitmq;

import com.k.docker.docker.amd.softmix.rabbitmq.cluster.RabbitmqClusterAll;
import com.k.docker.docker.amd.softmix.rabbitmq.single.RabbitmqSingleAll;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({RabbitmqSingleAll.class, RabbitmqClusterAll.class})
public class RabbitmqAll {

}