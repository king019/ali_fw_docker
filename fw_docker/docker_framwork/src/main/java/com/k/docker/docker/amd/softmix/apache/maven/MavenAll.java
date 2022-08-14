package com.k.docker.docker.amd.softmix.apache.maven;

import com.k.docker.docker.amd.softmix.apache.maven.build.MavenBuild;
import com.k.docker.docker.amd.softmix.apache.maven.image.create.MavenImageCreate;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({MavenBuild.class, MavenImageCreate.class})
public class MavenAll {

}
