package com.k.docker.docker.amd.softmix.apache.kafka.kafka;

import com.k.docker.docker.amd.softmix.apache.kafka.kafka.build.KafkaBuild;
import com.k.docker.docker.amd.softmix.apache.kafka.kafka.image.create.KafkaImageCreate;
import com.k.docker.docker.amd.softmix.apache.kafka.kafka.run.KafkaRun;
import com.k.docker.docker.amd.softmix.apache.kafka.kafka.scan.KafkaSingleWlanScan;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({KafkaBuild.class, KafkaRun.class, KafkaSingleWlanScan.class, KafkaImageCreate.class})
public class KafKakafkaAll {

}
