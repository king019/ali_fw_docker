package com.k.docker.docker.amd.softmix.apache.storm;

import com.k.docker.docker.amd.softmix.apache.storm.storm.StormStormAll;
import com.k.docker.docker.amd.softmix.apache.storm.zookeeper.StormZookeeperAll;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({StormStormAll.class, StormZookeeperAll.class})
public class StormAll {

}
