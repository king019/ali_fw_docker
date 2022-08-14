package com.k.docker.dockerhub.soft.common.github;

import com.k.docker.dockerhub.soft.common.github.apache.CommonApacheHubAll;
import com.k.docker.dockerhub.soft.common.github.ci.CommonCiHubAll;
import com.k.docker.dockerhub.soft.common.github.database.CommonDatabaseHubAll;
import com.k.docker.dockerhub.soft.common.github.docker.DockerHubCommonAll;
import com.k.docker.dockerhub.soft.common.github.git.CommonGitHubAll;
import com.k.docker.dockerhub.soft.common.github.tool.CommonToolHubAll;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CommonToolHubAll.class, CommonApacheHubAll.class, CommonCiHubAll.class, CommonDatabaseHubAll.class, DockerHubCommonAll.class, CommonGitHubAll.class})
public class CommonGithubHubSoftAll {

}
