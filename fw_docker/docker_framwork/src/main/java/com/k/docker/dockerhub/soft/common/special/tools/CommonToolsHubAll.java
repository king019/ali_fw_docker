package com.k.docker.dockerhub.soft.common.special.tools;

import com.k.docker.dockerhub.soft.common.special.tools.jenkins.CommonJenkinsInstall;
import com.k.docker.dockerhub.soft.common.special.tools.nginx.CommonNginxCdnInstall;
import com.k.docker.dockerhub.soft.common.special.tools.nginx.CommonNginxDownInstall;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CommonJenkinsInstall.class, CommonNginxDownInstall.class, CommonNginxCdnInstall.class})
public class CommonToolsHubAll {

}
