package com.k.docker.dockerhub.soft.common;

import com.k.docker.dockerhub.soft.common.github.CommonGithubHubSoftAll;
import com.k.docker.dockerhub.soft.common.special.CommonSpecialHubSoftAll;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CommonGithubHubSoftAll.class, CommonSpecialHubSoftAll.class})
public class CommonDockerHubSoftAll {

}
