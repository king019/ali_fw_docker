package com.k.docker.docker.amd.softmix.alibaba.jstorm;

import com.k.docker.docker.amd.softmix.alibaba.jstorm.jstorm.JstormJstomAll;
import com.k.docker.docker.amd.softmix.alibaba.jstorm.zookeeper.JstormZookeeperAll;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({JstormJstomAll.class, JstormZookeeperAll.class})
public class JstormAll {

}
