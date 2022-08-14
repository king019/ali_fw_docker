package com.k.docker.dockerhub;

import com.k.docker.dockerhub.soft.amd.DockerHubSoftAmdAll;
import com.k.docker.dockerhub.soft.common.CommonDockerHubSoftAll;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({DockerHubSoftAmdAll.class, CommonDockerHubSoftAll.class})
public class DockerHubSoftAll {

}
