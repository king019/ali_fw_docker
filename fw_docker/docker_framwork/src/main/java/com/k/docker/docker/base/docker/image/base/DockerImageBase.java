package com.k.docker.docker.base.docker.image.base;

import com.k.docker.docker.amd.soft.docker.install.base.InstallFile;
import com.k.docker.docker.base.docker.image.DockerImageBuildBase;
import com.k.docker.docker.util.file.FileWriteUtil;
import com.k.docker.docker.util.file.ShellWriteUtil;
import com.k.docker.docker.util.path.FWPathUtil;

import java.util.List;

public class DockerImageBase extends InstallFile {
    public static String shellName = "docker_image_create.sh";

    @Override
    public void bf() {
        shPath = FWPathUtil.getTargetPath(getClass()) + "/" + shellName;
        shFile = FileWriteUtil.getFile(shPath);
    }

    public void buildList(final List<DockerImageBuildBase> list) throws Exception {
        ShellWriteUtil.writeShellTop(shFile);
        for (final DockerImageBuildBase dockerImageBase : list) {
            dockerImageBase.buildImage(shFile);
        }
    }
}
