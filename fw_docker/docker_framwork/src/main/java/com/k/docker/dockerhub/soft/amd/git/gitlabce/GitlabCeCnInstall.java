package com.k.docker.dockerhub.soft.amd.git.gitlabce;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.docker.util.props.PropsDockerUtil;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;

import java.util.List;
import java.util.Map;

/**
 * https://www.jianshu.com/p/c99a40e5255d
 */
public class GitlabCeCnInstall extends DockerhubCallBack {

    //docker run -d    -p 80:80  -p 443:443  -p 22:22
// --name gitlab  --restart unless-stopped
// -v /opt/soft/docker/data/gitlab/gitlab-config:/etc/gitlab
// -v /opt/soft/docker/data/gitlab/gitlab-logs:/var/log/gitlab
// -v /opt/soft/docker/data/gitlab/gitlab-data:/var/opt/gitlab  twang2218/gitlab-ce-zh
    @Override
    public void writeDockerRuns(List<DockerRun> runs) {
        String version = "twang2218/gitlab-ce-zh";
        String name = "gitlab";
        {
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.nameCmd(name);
            builder.dCmd(true);
            builder.version(version);
            {
                String base = PropsDockerUtil.dockerAbsDataDir + name + "/gitlab/";
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
