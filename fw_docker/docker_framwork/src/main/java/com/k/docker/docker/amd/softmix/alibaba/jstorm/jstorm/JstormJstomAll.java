package com.k.docker.docker.amd.softmix.alibaba.jstorm.jstorm;

import com.k.docker.docker.amd.softmix.alibaba.jstorm.jstorm.build.JstromBuild;
import com.k.docker.docker.amd.softmix.alibaba.jstorm.jstorm.image.create.JstormImageCreate;
import com.k.docker.docker.amd.softmix.alibaba.jstorm.jstorm.run.JstormRun;
import com.k.docker.docker.amd.softmix.alibaba.jstorm.jstorm.scan.JstormWlanScan;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({JstromBuild.class, JstormImageCreate.class, JstormRun.class, JstormWlanScan.class})
public class JstormJstomAll {

}