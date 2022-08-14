package com.k.docker.dockerhub.soft.common.special.tools.nginx;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;
import com.k.docker.dockerhub.base.enums.DockerRegionEnum;

import java.util.List;
import java.util.Map;

import static com.k.docker.docker.util.props.PropsDockerUtil.dockerAbsDataDir;

/**
 * https://github.com/king019/nginx-down
 */
public class CommonNginxCdnInstall extends DockerhubCallBack {


    @Override
    public void buildRegion(DockerRegionEnum region, List<DockerRun> runs) {
        String version = "king019/nginx:cdn";
        String name = "nginxcdn";
        buildModel(name, version, region, runs);
    }

    public void buildModel(String name, String version, DockerRegionEnum region, List<DockerRun> runs) {
        {
            version = DockerRegionEnum.getVersion(region, version);
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.region(region);
            builder.nameCmd(name);
            builder.dCmd(true);
            builder.version(version);
            {
                Map<String, String> vCmdMap = Maps.newHashMap();
                vCmdMap.put(dockerAbsDataDir + name, "/var/log/nginx");
                builder.vCmdMap(vCmdMap);
            }
            {
                Map<String, String> pCmdMap = Maps.newHashMap();
                pCmdMap.put("80", "80");
                builder.pCmdMap(pCmdMap);
            }
            builder.dRestart(true);
            runs.add(builder.build());
        }
    }
}
