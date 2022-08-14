package com.k.docker.dockerhub.soft.common.special.database.cache.redis.item;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;
import com.k.docker.dockerhub.base.enums.DockerRegionEnum;

import java.util.List;
import java.util.Map;

/**
 * https://hub.docker.com/r/arm32v7/redis
 */
public class CommonRedisClusterInstall extends DockerhubCallBack {

    @Override
    public void buildRegion(DockerRegionEnum region, List<DockerRun> runs) {
        String name = "redisCluster";
        String version = "king019/redis:cluster";
        buildModel(name, region, DockerRegionEnum.getVersion(region, version), runs);
    }

    public void buildModel(String name, DockerRegionEnum region, String version, List<DockerRun> runs) {

        {
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.nameCmd(name);
            builder.dCmd(true);
            builder.version(version);
            {
                Map<String, String> pCmdMap = Maps.newHashMap();
                for (int i = 7001; i <= 7009; i++) {
                    pCmdMap.put("" + i, "" + i);
                }
                builder.pCmdMap(pCmdMap);
            }
            builder.notes(List.of(region.getRegion()));
            builder.dRestart(true);
            runs.add(builder.build());
        }
    }
}
