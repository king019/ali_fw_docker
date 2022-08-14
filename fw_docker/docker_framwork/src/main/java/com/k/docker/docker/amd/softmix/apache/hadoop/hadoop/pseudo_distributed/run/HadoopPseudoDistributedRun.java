package com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.pseudo_distributed.run;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.apache.hadoop.hadoop.pseudo_distributed.install.HadoopPseudoDistributedInstall;
import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.pseudo_distributed.build.HadoopPseudoDistributedBuild;
import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.pseudo_distributed.config.host.HadoopPseudoDistributedHost;
import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.pseudo_distributed.image.create.HadoopPseudoDistributedImageCreate;
import com.k.docker.docker.base.docker.run.DockerRunFileBase;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.docker.run.base.impl.DockerRunBaseImpl;
import com.k.docker.docker.util.docker.DockerVersion;
import com.k.docker.docker.util.file.FileWriteUtil;
import org.junit.Test;

import java.util.List;

public class HadoopPseudoDistributedRun extends DockerRunFileBase {

    @Test
    public void test() throws Exception {
        String hadoopVersion = DockerVersion.hadoopPseudoDistributedVersion;
        DockerRunBase dockerRun = new DockerRunBaseImpl();
        HadoopPseudoDistributedHost hostEntity = new HadoopPseudoDistributedHost();
        List<String> lines = Lists.newArrayList();
        String param = null;
        {
            FileWriteUtil.writeCdClassandCopySoft(lines, HadoopPseudoDistributedBuild.class);
        }
        {
            FileWriteUtil.writeDnsAndImage(lines, HadoopPseudoDistributedImageCreate.class);
        }
        HadoopPseudoDistributedInstall.dockerRun(HadoopPseudoDistributedBuild.class, lines, hadoopVersion, dockerRun, hostEntity, param);
        FileWriteUtil.write(shFile, lines);
    }
}
