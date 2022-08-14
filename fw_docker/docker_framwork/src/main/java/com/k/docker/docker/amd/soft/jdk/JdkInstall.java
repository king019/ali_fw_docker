package com.k.docker.docker.amd.soft.jdk;

import com.google.common.collect.Lists;
import com.k.docker.docker.base.soft.base.DockerBase;
import com.k.docker.docker.base.soft.base.impl.DockerBaseImpl;
import com.k.docker.docker.util.docker.SoftVersion;
import com.k.docker.docker.util.file.FileWriteUtil;
import com.k.docker.docker.util.file.ShellWriteUtil;
import com.k.docker.docker.util.props.PropsDockerUtil;
import com.k.docker.docker.util.vo.DockerBuildVO;

import java.io.File;
import java.util.List;

public class JdkInstall extends DockerBaseImpl {
    public JdkInstall() {
        dockerBuildVOs.add(SoftVersion.JdkSoft8);
    }

    @Override
    public void dockerShell(final File file) throws Exception {
        final DockerBuildVO dockerSoft = queryTar("jdk");
        ShellWriteUtil.writeTar(file, dockerSoft.tar);
        ShellWriteUtil.writeMv(file, dockerSoft.unpress, dockerSoft.mvDir);
        ShellWriteUtil.writeCatAppend(file, "profile", "/etc/profile");
        ShellWriteUtil.writeSourceProfile(file);
        ShellWriteUtil.writeShell(file, "java -verbose >> java_version");
    }

    @Override
    public List<File> buildConfigFile() {
        final List<File> files = Lists.newArrayList();
        try {
            final File profile = File.createTempFile("profile" + DockerBase.tempSub, DockerBase.tempSub);
            {
                FileWriteUtil.write(profile, "export JAVA_HOME=" + PropsDockerUtil.dockerDir + "jdk");
                FileWriteUtil.write(profile, "export PATH=$JAVA_HOME/bin:$PATH");
                FileWriteUtil.write(profile, "export  KAFKA_HEAP_OPTS=\"-Xmx400m -Xms400m\"");
            }
            files.add(profile);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return files;
    }
}
