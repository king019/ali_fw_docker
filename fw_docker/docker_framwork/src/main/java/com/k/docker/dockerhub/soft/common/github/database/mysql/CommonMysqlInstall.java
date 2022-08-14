package com.k.docker.dockerhub.soft.common.github.database.mysql;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.docker.util.props.PropsDockerUtil;
import com.k.docker.dockerhub.base.back.DockerhubCallBack;

import java.util.List;
import java.util.Map;

/**
 * https://www.cnblogs.com/chasingdreams2017/p/8977226.html
 * https://www.jianshu.com/p/69e9485c9610
 * https://blog.csdn.net/konglongaa/article/details/80996829
 * mysql -u root -p
 * GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY '111111'  ;
 */

/**
 * mysql -u root -p
 * GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY '111111' WITH GRANT OPTION;
 */
public class CommonMysqlInstall extends DockerhubCallBack {

    @Override
    public void writeDockerRuns(List<DockerRun> runs) {
        String version;
        String note;
        String name = "mysql";
        //String version = "mariadb";
        note = "mac";
        version = "mysql:5.7";
        writeDockerRuns(name, version, note, runs);
        //version = "arm64v8/mariadb";
        //version =  "mariadb:10.2.29";
        version = "mariadb";
        note = "smp";
        writeDockerRuns(name, version, note, runs);
    }

    public void writeDockerRuns(String name, String version, String note, List<DockerRun> runs) {

        //docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -d -p 12345:3306 hypriot/rpi-mysql
        //String version = "hypriot/rpi-mysql";

        //String version = "arm64v8/mariadb";

        {
            DockerRun.DockerRunBuilder builder = DockerRun.builder();
            builder.nameCmd(name);
            builder.dCmd(true);
            builder.version(version);
            {
                Map<String, String> eCmdMap = Maps.newHashMap();
                eCmdMap.put("MYSQL_ROOT_PASSWORD", "111111");
                builder.eMap(eCmdMap);
            }
            {
                Map<String, String> vCmdMap = Maps.newHashMap();
                vCmdMap.put(PropsDockerUtil.dockerAbsDataDir + name, "/var/lib/mysql");
                builder.vCmdMap(vCmdMap);
            }
            {
                Map<String, String> pCmdMap = Maps.newHashMap();
                pCmdMap.put("3306", "3306");
                builder.pCmdMap(pCmdMap);
            }
            builder.notes(List.of(note));
            builder.dRestart(true);
            runs.add(builder.build());
        }
    }
}
