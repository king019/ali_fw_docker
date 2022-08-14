package com.k.docker.docker.amd.softmix.apache.storm.storm;

import com.k.docker.docker.amd.softmix.apache.storm.storm.build.StormBuild;
import com.k.docker.docker.amd.softmix.apache.storm.storm.run.StormRun;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({StormBuild.class, StormRun.class})
public class StormStormAll {

}
