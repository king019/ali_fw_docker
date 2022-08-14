package com.k.docker.docker;

import com.k.docker.docker.util.path.FWPathUtil;
import com.k.docker.docker.util.props.PropsDockerUtil;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;

public class DockerCopy {

    @Test
    public void test() {
        final String targetPath = FWPathUtil.getTargetPath();
        final String usrSoft = PropsDockerUtil.dockerAbsComDir;
        final File targetFile = new File(targetPath);
        final File softFile = new File(usrSoft);
        try {
            FileUtils.forceDelete(softFile);
        } catch (final Exception e) {
        }
        try {
            FileUtils.copyDirectory(targetFile, softFile);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
