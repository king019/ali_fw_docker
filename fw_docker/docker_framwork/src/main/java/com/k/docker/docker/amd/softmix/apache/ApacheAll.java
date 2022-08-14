package com.k.docker.docker.amd.softmix.apache;

import com.k.docker.docker.amd.softmix.apache.hadoop.HadoopAll;
import com.k.docker.docker.amd.softmix.apache.kafka.KafkaAll;
import com.k.docker.docker.amd.softmix.apache.maven.MavenAll;
import com.k.docker.docker.amd.softmix.apache.storm.StormAll;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({StormAll.class, HadoopAll.class, KafkaAll.class, MavenAll.class})
public class ApacheAll {

}
