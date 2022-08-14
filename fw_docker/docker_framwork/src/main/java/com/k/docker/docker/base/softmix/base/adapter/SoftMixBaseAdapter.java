package com.k.docker.docker.base.softmix.base.adapter;

import com.k.docker.docker.base.docker.build.base.DockerBuildBase;
import com.k.docker.docker.base.soft.base.DockerBase;
import com.k.docker.docker.base.softmix.base.SoftMixBase;
import org.junit.Test;

import java.util.List;

public class SoftMixBaseAdapter extends SoftMixBase {

    public List<DockerBase> dockerBases;

    @Test
    public void test() throws Exception {
        dockerBases = queryDocks();
        DockerBuildBase.dockerBuild(baseDir, from, maintatine, dockerBases);
    }

    public List<DockerBase> queryDocks() {
        return null;
    }
}
