package com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.distributed.run;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.apache.hadoop.hadoop.distributed.install.HadoopDistributedInstall;
import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.distributed.build.HadoopDistributedBuild;
import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.distributed.config.host.HadoopDistributedHost;
import com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.distributed.image.create.HadoopDistributedImageCreate;
import com.k.docker.docker.base.docker.run.DockerRunFileBase;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.docker.run.base.impl.DockerRunBaseImpl;
import com.k.docker.docker.util.docker.DockerVersion;
import com.k.docker.docker.util.file.FileWriteUtil;
import org.junit.Test;

import java.util.List;

/**
 * @author k
 */
public class HadoopDistributedRun extends DockerRunFileBase {

    @Test
    public void test() throws Exception {
        String hadoopVersion = DockerVersion.hadoopDistributedVersion;
        DockerRunBase dockerRun = new DockerRunBaseImpl();
        HadoopDistributedHost hostEntity = new HadoopDistributedHost();
        List<String> lines = Lists.newArrayList();
        String param = null;
        {
            FileWriteUtil.writeCdClassandCopySoft(lines, HadoopDistributedBuild.class);
        }
        {
            FileWriteUtil.writeDnsAndImage(lines, HadoopDistributedImageCreate.class);
        }
        HadoopDistributedInstall.dockerRun(HadoopDistributedBuild.class, lines, hadoopVersion, dockerRun, hostEntity.docker_all(), param);
        FileWriteUtil.write(shFile, lines);
    }
}
