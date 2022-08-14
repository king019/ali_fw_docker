package com.k.docker.dockerhub.soft.common.github.docker.docker;

import com.k.docker.dockerhub.soft.common.github.docker.docker.registry.DockerRegistryCommonInstall;
import com.k.docker.dockerhub.soft.common.github.docker.docker.registry.DockerRegistryUiCommonInstall;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({DockerRegistryCommonInstall.class, DockerRegistryUiCommonInstall.class})
public class DockerCommonAll {

}
