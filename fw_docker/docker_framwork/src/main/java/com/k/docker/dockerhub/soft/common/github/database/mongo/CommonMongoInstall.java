package com.k.docker.dockerhub.soft.common.github.database.mongo;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.docker.util.props.PropsDockerUtil;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;

import java.util.List;
import java.util.Map;

public class CommonMongoInstall extends DockerhubCallBack {

    @Override
    public void writeDockerRuns(List<DockerRun> runs) {
        String version;
        String name = "mongo";
        version = "mongo";
        writeDockerRuns(name, version,   runs);
    }

    public void writeDockerRuns(String name, String version,   List<DockerRun> runs) {

        {
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.nameCmd(name);
            builder.dCmd(true);
            builder.version(version);
            {
                Map<String, String> eCmdMap = Maps.newHashMap();
                eCmdMap.put("MONGO_INITDB_ROOT_USERNAME","root");
                eCmdMap.put("MONGO_INITDB_ROOT_PASSWORD","111111");
                builder.eMap(eCmdMap);
            }
            {
                Map<String, String> vCmdMap = Maps.newHashMap();
                builder.vCmdMap(vCmdMap);
            }
            {
                Map<String, String> pCmdMap = Maps.newHashMap();
                pCmdMap.put("27017", "27017");
                builder.pCmdMap(pCmdMap);
            }
            builder.dRestart(true);
            runs.add(builder.build());
        }
    }
}
