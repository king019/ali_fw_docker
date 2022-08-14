package com.k.docker.docker.base.docker.image;

import com.k.docker.docker.util.file.ShellWriteUtil;
import com.k.docker.docker.util.props.PropsDockerUtil;

import java.io.File;

public abstract class DockerImageBuildBase {
    public Class<?> clazz;

    public abstract void buildImage(File file) throws Exception;

    public void dockerBuild(File file, String imageName) throws Exception {
        String command = "cd " + PropsDockerUtil.dockerAbsDockerDir + clazz.getPackage().getName().replace(".", "/");
        ShellWriteUtil.writeShell(file, command);
        command = "docker build -t " + imageName + "  .";
        ShellWriteUtil.writeShell(file, command);
    }
}
