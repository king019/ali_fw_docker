package com.k.docker.dockerhub.soft.common.github.tool;

import com.k.docker.dockerhub.soft.common.github.tool.cloud.CommonNextCloudInstall;
import com.k.docker.dockerhub.soft.common.github.tool.sonar.CommonSonarInstall;
import com.k.docker.dockerhub.soft.common.github.tool.zipkin.CommonZipkinInstall;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CommonZipkinInstall.class,
        CommonSonarInstall.class,
        CommonNextCloudInstall.class

})
public class CommonToolHubAll {

}
