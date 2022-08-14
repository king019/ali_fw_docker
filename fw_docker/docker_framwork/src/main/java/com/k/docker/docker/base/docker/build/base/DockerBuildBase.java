package com.k.docker.docker.base.docker.build.base;

import com.k.docker.docker.base.soft.base.DockerBase;
import com.k.docker.docker.util.file.FileWriteUtil;
import com.k.docker.docker.util.props.PropsDockerUtil;

import java.io.File;
import java.util.List;

public class DockerBuildBase {
    public static void dockerBuild(String baseDir, String from, String maintatine, List<DockerBase> dockerBases) throws Exception {
        String dockerFilePath;
        dockerFilePath = baseDir + "/Dockerfile";
        buildDockerFile(dockerFilePath, from, maintatine, dockerBases);
        dockerFilePath = baseDir + "/docker.sh";
        buildDockerShell(dockerFilePath, dockerBases);
    }

    public static void buildDockerFile(String dockerFilePath, String from, String maintatine, List<DockerBase> dockerBases) {
        try {
            File dockerFile = new File(dockerFilePath);
            FileWriteUtil.writeStart(dockerFile, "FROM " + from);
            FileWriteUtil.write(dockerFile, "MAINTAINER " + maintatine);
            for (DockerBase dockerBase : dockerBases) {
                dockerBase.dockerFile(dockerFile);
                dockerBase.dockerDealWithConfigFile(dockerFile);
                dockerBase.dockerDealWithResFile(dockerFile);
                dockerBase.dockerYums(dockerFile, from);
                dockerBase.dockerRuns(dockerFile);
            }
            FileWriteUtil.write(dockerFile, "COPY docker.sh /docker.sh");
            FileWriteUtil.write(dockerFile, "RUN chmod 755 /*.sh");
            FileWriteUtil.write(dockerFile, "CMD /docker.sh");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void buildDockerShell(String dockerFilePath, List<DockerBase> dockerBases) throws Exception {
        File dockerFile = new File(dockerFilePath);
        FileWriteUtil.writeStart(dockerFile);
        FileWriteUtil.write(dockerFile, "mkdir -p " + PropsDockerUtil.dockerDir);
        FileWriteUtil.write(dockerFile, "/usr/sbin/sshd");
        for (DockerBase dockerBase : dockerBases) {
            dockerBase.dockerShellStart(dockerFile);
            dockerBase.dockerShell(dockerFile);
        }
    }
}
