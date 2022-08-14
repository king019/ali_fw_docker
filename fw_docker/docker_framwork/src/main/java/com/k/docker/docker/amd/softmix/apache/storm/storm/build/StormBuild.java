package com.k.docker.docker.amd.softmix.apache.storm.storm.build;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.apache.storm.install.StromInstall;
import com.k.docker.docker.amd.soft.jdk.JdkInstall;
import com.k.docker.docker.base.docker.build.base.DockerBuildBase;
import com.k.docker.docker.base.soft.base.DockerBase;
import com.k.docker.docker.base.softmix.adapter.SoftMixAdapter;
import com.k.docker.docker.base.softmix.base.SoftMixBase;
import org.junit.Test;

import java.util.List;

public class StormBuild extends SoftMixAdapter {

    @Override
    @Test
    public void test() throws Exception {
        List<DockerBase> dockerBases = Lists.newArrayList();
        dockerBases.add(new JdkInstall());
        dockerBases.add(new StromInstall());
        DockerBuildBase.dockerBuild(baseDir, SoftMixBase.from, SoftMixBase.maintatine, dockerBases);
    }
}
