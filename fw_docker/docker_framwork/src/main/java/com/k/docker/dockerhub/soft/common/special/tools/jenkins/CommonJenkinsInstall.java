package com.k.docker.dockerhub.soft.common.special.tools.jenkins;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.docker.util.props.PropsContainerUtil;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;
import com.k.docker.dockerhub.base.enums.DockerRegionEnum;

import java.util.List;
import java.util.Map;

import static com.k.docker.docker.util.props.PropsContainerUtil.rootSoftDir;
import static com.k.docker.docker.util.props.PropsDockerUtil.dockerAbsDataDir;

/**
 * https://updates.jenkins-zh.cn/update-center.json
 */
public class CommonJenkinsInstall extends DockerhubCallBack {

    @Override
    public void buildRegion(DockerRegionEnum region, List<DockerRun> runs) {
        String version = "king019/jenkins";
        String name = "jenkins";
        buildModel(name, DockerRegionEnum.getVersion(region, version), runs);
        //name = "jenkins1";
        //buildModel1(name, DockerRegionEnum.getVersion(region, version), runs);
    }

    public void buildModel(String name, String version, List<DockerRun> runs) {
        //jenkins("mac", name, version, "/usr/local/bin/docker", runs);
        jenkins("smp", name, version, "/usr/bin/docker", runs);
    }

    public void buildModel1(String name, String version, List<DockerRun> runs) {
        jenkins1("mac", name, version, "/usr/local/bin/docker", runs);
        jenkins1("smp", name, version, "/usr/bin/docker", runs);
    }

    private void jenkins(String plat, String name, String version, String dockerDir, List<DockerRun> runs) {

        {
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.nameCmd(name);
            builder.dCmd(true);
            builder.version(version);
            {
                Map<String, String> vCmdMap = Maps.newHashMap();
                vCmdMap.put(dockerAbsDataDir + name, rootSoftDir);
                vCmdMap.put(dockerAbsDataDir + name + "/jkwk", "/root/.jenkins");
                //vCmdMap.put(dockerDir, "/bin/docker");
                //vCmdMap.put( dockerDir "/usr/local/bin/docker", "/bin/docker");
                vCmdMap.put(dockerAbsDataDir + "maven/m2", PropsContainerUtil.rootM2Dir);
                vCmdMap.put("/var/run/docker.sock", "/var/run/docker.sock");
                builder.vCmdMap(vCmdMap);
            }
            {
                Map<String, String> pCmdMap = Maps.newHashMap();
                pCmdMap.put("9999", "8080");
                builder.pCmdMap(pCmdMap);
            }
            {
                Map<String, String> linkMap = Maps.newHashMap();
                //linkMap.put("registry", "docker");
                //linkMap.put("nexus3", "nexus3");
                //linkMap.put("gitea", "gitea");
                //linkMap.put("nginxdown", "nginxdown");
                builder.linkMap(linkMap);
            }
            {
                Map<String, String> eMap = Maps.newHashMap();
                eMap.put("JAVA_OPTS", "' -Xms128m -Xmx128m -Xmn64m -Dhudson.model.DownloadService.noSignatureCheck=true'");
                builder.eMap(eMap);
            }
            builder.dRestart(true);
            //builder.notes(List.of(plat));
            runs.add(builder.build());
        }
    }

    private void jenkins1(String plat, String name, String version, String dockerDir, List<DockerRun> runs) {

        {
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.nameCmd(name);
            builder.dCmd(true);
            builder.version(version);
            {
                Map<String, String> vCmdMap = Maps.newHashMap();
                vCmdMap.put(dockerAbsDataDir + name, rootSoftDir);
                vCmdMap.put(dockerAbsDataDir + name + "/jkwk", "/root/.jenkins");
                vCmdMap.put(dockerDir, "/bin/docker");
                builder.vCmdMap(vCmdMap);
            }
            {
                Map<String, String> eMap = Maps.newHashMap();
                eMap.put("JAVA_OPTS", "' -Xms128m -Xmx128m -Xmn64m -Dhudson.model.DownloadService.noSignatureCheck=true'");
                builder.eMap(eMap);
            }
            {
                Map<String, String> pCmdMap = Maps.newHashMap();
                pCmdMap.put("8999", "8080");
                builder.pCmdMap(pCmdMap);
            }
            builder.notes(List.of(plat));
            runs.add(builder.build());
        }
    }
}
