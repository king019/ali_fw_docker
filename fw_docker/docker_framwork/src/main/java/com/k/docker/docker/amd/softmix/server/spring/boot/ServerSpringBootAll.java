package com.k.docker.docker.amd.softmix.server.spring.boot;

import com.k.docker.docker.amd.softmix.server.spring.boot.build.ServerBootBuild;
import com.k.docker.docker.amd.softmix.server.spring.boot.image.create.ServerBootImageCreate;
import com.k.docker.docker.amd.softmix.server.spring.boot.run.ServerShowRun;
import com.k.docker.docker.amd.softmix.server.spring.boot.scan.ServerShowWlanScan;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ServerBootBuild.class, ServerShowRun.class, ServerBootImageCreate.class, ServerShowWlanScan.class})
public class ServerSpringBootAll {

}