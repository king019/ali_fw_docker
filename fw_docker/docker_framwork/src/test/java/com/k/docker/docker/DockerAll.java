package com.k.docker.docker;

import com.k.docker.docker.amd.softmix.SoftMixAll;
import com.k.docker.docker.dockerhub.DockerHubAll;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({SoftMixAll.class, DockerHubAll.class})
public class DockerAll {
    /// ---------------/opt/soft/桌面
}
