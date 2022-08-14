package com.k.docker.docker.amd.softmix.apache.kafka;

import com.k.docker.docker.amd.softmix.apache.kafka.kafka.KafKakafkaAll;
import com.k.docker.docker.amd.softmix.apache.kafka.zookeeper.KafkaZookeeperAll;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({KafKakafkaAll.class, KafkaZookeeperAll.class})
public class KafkaAll {

}
