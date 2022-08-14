package com.k.docker.dockerhub.soft.common.github.git;

import com.k.docker.dockerhub.soft.common.github.git.gitea.CommonGiteaInstall;
import com.k.docker.dockerhub.soft.common.github.git.gitea.CommonGogsInstall;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CommonGiteaInstall.class, CommonGogsInstall.class})
public class CommonGitHubAll {

}
