package com.k.docker.docker.amd.softmix.alibaba.dubbo.dubbo;

import com.k.docker.docker.amd.softmix.alibaba.dubbo.dubbo.build.DubboBuild;
import com.k.docker.docker.amd.softmix.alibaba.dubbo.dubbo.image.create.DubboImageCreate;
import com.k.docker.docker.amd.softmix.alibaba.dubbo.dubbo.run.DubboRun;
import com.k.docker.docker.amd.softmix.alibaba.dubbo.dubbo.scan.DubboWlanScan;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({DubboBuild.class, DubboRun.class, DubboImageCreate.class, DubboWlanScan.class})
public class DubboDubboAll {

}