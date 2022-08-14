package com.k.docker.dockerhub.soft.common.github.apache.kafka;

import com.k.docker.dockerhub.soft.common.github.apache.kafka.kafka.CommonKafkaInstall;
import com.k.docker.dockerhub.soft.common.github.apache.kafka.kafka.CommonKafkaManagerInstall;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CommonKafkaInstall.class, CommonKafkaManagerInstall.class})
public class CommonKafkaHubAll {

}
