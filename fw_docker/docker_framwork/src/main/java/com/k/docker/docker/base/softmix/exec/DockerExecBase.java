package com.k.docker.docker.base.softmix.exec;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.docker.install.base.InstallFile;
import com.k.docker.docker.util.file.FileWriteUtil;
import com.k.docker.docker.util.path.FWPathUtil;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class DockerExecBase extends InstallFile {

    public String dockerVersion;

    public void setDockerVersion() {
    }

    @Override
    public void bf() {
        shPath = FWPathUtil.getTargetPath(getClass()) + "/docker_exec.sh";
        shFile = FileWriteUtil.getFile(shPath);
    }

    public abstract Map<String, String> hostIps();

    @Test
    public void test() throws Exception {
        setDockerVersion();
        String host;
        Map<String, String> hostIps = hostIps();
        FileWriteUtil.writeStart(shFile);
        List<String> lines = Lists.newArrayList();
        for (Entry<String, String> hostEntry : hostIps.entrySet()) {
            host = hostEntry.getKey();
            lines.add("docker exec -it " + host + " /bin/bash");
        }
        lines.add("");
        lines.add("");
        lines.add("");
        for (Entry<String, String> hostEntry : hostIps.entrySet()) {
            host = hostEntry.getKey();
            lines.add("docker run -it " + host + " /bin/bash");
        }
        lines.add("");
        lines.add("");
        lines.add("");
        for (Entry<String, String> hostEntry : hostIps.entrySet()) {
            host = hostEntry.getKey();
            lines.add("docker start " + host);
        }
        lines.add("");
        lines.add("");
        lines.add("");
        for (Entry<String, String> hostEntry : hostIps.entrySet()) {
            host = hostEntry.getKey();
            lines.add("docker rm -f " + host);
        }
        lines.add("");
        lines.add("");
        lines.add("");
        for (Entry<String, String> hostEntry : hostIps.entrySet()) {
            host = hostEntry.getKey();
            lines.add("docker run -it     " + dockerVersion + " /docker.sh server");
        }
        FileWriteUtil.write(shFile, lines);
    }
}
