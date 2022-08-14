package com.k.docker.docker.amd.softmix.alibaba.dubbo;

import com.k.docker.docker.amd.softmix.alibaba.dubbo.dubbo.DubboDubboAll;
import com.k.docker.docker.amd.softmix.alibaba.dubbo.zookeeper.DubboZookeeperAll;
import com.k.docker.docker.amd.softmix.alibaba.dubbo.zookeeperui.DubboZookeeperUIAll;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({DubboDubboAll.class, DubboZookeeperAll.class, DubboZookeeperUIAll.class})
public class DubboAll {

}
