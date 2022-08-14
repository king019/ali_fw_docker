package com.k.docker.docker.base.docker.run;

import com.k.docker.docker.amd.soft.docker.install.base.InstallFile;
import com.k.docker.docker.util.file.FileWriteUtil;
import com.k.docker.docker.util.file.ShellWriteUtil;
import com.k.docker.docker.util.path.FWPathUtil;

import java.util.List;

public class DockerRunFileBase extends InstallFile {
    @Override
    public void bf() throws Exception {
        shPath = FWPathUtil.getTargetPath(getClass()) + "/docker_run.sh";
        shFile = FileWriteUtil.getFile(shPath);
        FileWriteUtil.writeStart(shFile);
        ShellWriteUtil.writeChmod(shFile);
        FileWriteUtil.deleteDnsAndImage(shFile);
        FileWriteUtil.write(shFile, "scope launch");
        FileWriteUtil.write(shFile, "docker run -d -p 9000:9000 --privileged -v /var/run/docker.sock:/var/run/docker.sock uifd/ui-for-docker");
        //FileWriteUtil.write(shFile, "docker run -d -p 9000:9000 -v /var/run/docker.sock:/var/run/docker.sock -v /opt/soft/docker/portainer:/data portainer/portainer");
        FileWriteUtil.write(shFile, "docker run -d -p 9000:9000 -v /var/run/docker.sock:/var/run/docker.sock  portainer/portainer");
    }

    @Override
    public void af() throws Exception {
        //http://blog.csdn.net/horsefoot/article/details/51749528
        //sudo wget -O /usr/local/bin/scope https://git.io/scope
        //sudo chmod a+x /usr/local/bin/scope
        //sudo scope launch
        //FileWriteUtil.write(shFile, "scope launch");
    }

    public void ssh(List<String> lines, Class<?> clazz) {
        String buildPath = FWPathUtil.getClassPath(clazz);
        lines.add("cat ~/.ssh/authorized_keys > " + buildPath + "/authorized_keys");
        lines.add("cat ~/.ssh/id_rsa > " + buildPath + "/id_rsa");
        lines.add("cat ~/.ssh/id_rsa.pub > " + buildPath + "/id_rsa.pub");
    }
}
