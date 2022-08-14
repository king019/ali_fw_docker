package com.k.docker.dockerhub.soft.common.github.apache.kafka.kafka;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.dockerhub.soft.common.special.apache.maven.nexus.NexusMavenBaseInstall;

import java.util.List;
import java.util.Map;

import static com.k.docker.docker.util.props.PropsDockerUtil.dockerAbsDataDir;

/**
 * https://hub.docker.com/r/apache/dubbo-admin/dockerfile
 * https://www.cnblogs.com/shamo89/p/9265220.html
 * https://nodejs.org/en/download/
 * https://www.jianshu.com/p/d68e461f585c
 */
public class CommonKafkaInstall extends NexusMavenBaseInstall {


    @Override
    public void writeDockerRuns(List<DockerRun> runs) {
        String version = "wurstmeister/kafka";
        //kafka(   version,  runs);
        version = "fogsyio/kafka:2.2.0";
        kafka(   version,  runs);
    }
    private void kafka( String version,List<DockerRun> runs){

        String name = "kafka";
        {

            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.nameCmd(name);
            builder.dCmd(true);
            builder.version(version);
            {
                Map<String, String> vCmdMap = Maps.newHashMap();
                vCmdMap.put(dockerAbsDataDir + name, "/kafka");
                builder.vCmdMap(vCmdMap);
            }
            {
                Map<String, String> pCmdMap = Maps.newHashMap();
                pCmdMap.put("9092", "9092");
                builder.pCmdMap(pCmdMap);
            }

            {
                builder.linkMap(Map.of("zookeeper", "zookeeper"));
            }
            {
                Map<String, String> eLinkmap = Maps.newHashMap();

                eLinkmap.put("KAFKA_ZOOKEEPER_CONNECT", "zookeeper:2181");
                eLinkmap.put("KAFKA_ADVERTISED_HOST_NAME", "kafka");
                eLinkmap.put("KAFKA_ADVERTISED_PORT", "9092");
                builder.eMap(eLinkmap);
            }
            builder.dRestart(true);
            runs.add(builder.build());
//            docker run -d
//                -- name kafka
//            --publish 9092:9092
//                -- link zookeeper --env KAFKA_ZOOKEEPER_CONNECT = zookeeper:2181--
//            env KAFKA_ADVERTISED_HOST_NAME = 192.168 .59 .101-- env KAFKA_ADVERTISED_PORT = 9092--
//            volume / etc / localtime:/etc / localtime wurstmeister / kafka:latest
        }
    }
}
