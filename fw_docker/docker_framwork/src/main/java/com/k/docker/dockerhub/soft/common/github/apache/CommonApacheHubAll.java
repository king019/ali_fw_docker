package com.k.docker.dockerhub.soft.common.github.apache;

import com.k.docker.dockerhub.soft.common.github.apache.kafka.CommonKafkaHubAll;
import com.k.docker.dockerhub.soft.common.github.apache.maven.MavenHubAll;
import com.k.docker.dockerhub.soft.common.github.apache.zookeeper.CommonZookeeperbAll;
import com.k.docker.dockerhub.soft.common.special.apache.maven.CommonMavenHubAll;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CommonMavenHubAll.class, CommonZookeeperbAll.class, MavenHubAll.class, CommonKafkaHubAll.class})
public class CommonApacheHubAll {
}
