package com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.single.hadoop.run;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.apache.hadoop.hadoop.single.install.HadoopSingleInstall;
import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.single.hadoop.build.HadoopSingleBuild;
import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.single.hadoop.config.host.HadoopSingleHost;
import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.single.hadoop.image.create.HadoopSingleImageCreate;
import com.k.docker.docker.base.docker.run.DockerRunFileBase;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.docker.run.base.impl.DockerRunBaseImpl;
import com.k.docker.docker.util.docker.DockerVersion;
import com.k.docker.docker.util.file.FileWriteUtil;
import org.junit.Test;

import java.util.List;

public class HadoopSingleRun extends DockerRunFileBase {

    @Test
    public void test() throws Exception {
        String hadoopVersion = DockerVersion.hadoopSingleVersion;
        DockerRunBase dockerRun = new DockerRunBaseImpl();
        HadoopSingleHost hostEntity = new HadoopSingleHost();
        List<String> lines = Lists.newArrayList();
        Lists.newArrayList();
        hostEntity.docker_host_map();
        String param = null;
        {
            FileWriteUtil.writeCdClassandCopySoft(lines, HadoopSingleBuild.class);
        }
        {
            FileWriteUtil.writeDnsAndImage(lines, HadoopSingleImageCreate.class);
        }
        HadoopSingleInstall.dockerRun(HadoopSingleBuild.class, lines, hadoopVersion, dockerRun, hostEntity, param);
        FileWriteUtil.write(shFile, lines);
    }
}
