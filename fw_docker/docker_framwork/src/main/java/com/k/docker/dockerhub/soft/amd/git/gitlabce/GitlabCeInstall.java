package com.k.docker.dockerhub.soft.amd.git.gitlabce;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.docker.util.props.PropsDockerUtil;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;

import java.util.List;
import java.util.Map;

public class GitlabCeInstall extends DockerhubCallBack {

    //docker run -d -p 8443:443    -p 80:80   -p 10022:22
// --name gitlab   --restart=unless-stopped
// --volume /opt/soft/docker/data/gitlab/etc:/etc/gitlab
// --volume /opt/soft/docker/data/gitlab/log:/var/log/gitlab
// --volume /opt/soft/docker/data/gitlab/data:/var/opt/gitlab gitlab/gitlab-ce
    @Override
    public void writeDockerRuns(List<DockerRun> runs) {
        String version = "gitlab/gitlab-ce";
        String name = "gitlab";
        {
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.nameCmd(name);
            builder.dCmd(true);
            builder.version(version);
            {
                String base = PropsDockerUtil.dockerAbsDataDir + name + "/gitlabce/";
                Map<String, String> vCmdMap = Maps.newHashMap();
                vCmdMap.put(base + "etc", "/etc/gitlab");
                vCmdMap.put(base + "log", "/var/log/gitlab");
                vCmdMap.put(base + "data", "/var/opt/gitlab");
                builder.vCmdMap(vCmdMap);
            }
            {
                Map<String, String> eCmdMap = Maps.newHashMap();
                builder.eMap(eCmdMap);
            }
            {
                Map<String, String> pCmdMap = Maps.newHashMap();
                pCmdMap.put("80", "80");
                pCmdMap.put("443", "443");
                //pCmdMap.put("22", "22");
                builder.pCmdMap(pCmdMap);
            }
            builder.dRestart(true);
            runs.add(builder.build());
        }
    }
}
