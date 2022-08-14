package com.k.docker.docker.amd.soft.rabbitmq.single.install;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.softmix.rabbitmq.cluster.config.host.RabbitmqClusterHost;
import com.k.docker.docker.amd.softmix.rabbitmq.single.config.host.RabbitmqHost;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.soft.base.DockerBase;
import com.k.docker.docker.base.soft.base.impl.DockerBaseImpl;
import com.k.docker.docker.util.docker.SoftVersion;
import com.k.docker.docker.util.file.FileWriteUtil;
import com.k.docker.docker.util.file.ShellWriteUtil;
import com.k.docker.docker.util.path.FWPathUtil;
import com.k.docker.docker.util.props.PropsDockerUtil;
import com.k.docker.docker.util.vo.DockerBuildVO;
import org.apache.commons.collections4.CollectionUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RabbitmqSingleInstall extends DockerBaseImpl {
    public RabbitmqSingleInstall() {
        dockerBuildVOs = Lists.newArrayList();
        dockerBuildVOs.add(SoftVersion.rabbitmqRrlangSoft);
        dockerBuildVOs.add(SoftVersion.rabbitmqServerSoft);
    }

    public RabbitmqSingleInstall(List<String> hosts) {
        dockerBuildVOs = Lists.newArrayList();
        dockerBuildVOs.add(SoftVersion.rabbitmqRrlangSoft);
        dockerBuildVOs.add(SoftVersion.rabbitmqServerSoft);
        this.hosts = hosts;
    }

    public static void dockerRun(Class clazz, List<String> lines, String dockerVersion, DockerRunBase dockerRun, RabbitmqHost dockerHost, String param, Map<String, String> hostMap, List<String> ports) {
        String memery = "600m";
        List<String> hosts;
        lines.add("cd " + FWPathUtil.getClassPath(clazz));
        // monitor
        {
            param = "server";
            hosts = dockerHost.rabbitmq_host();
            for (String host : hosts) {
                dockerRun.dockerRun(lines, dockerVersion, host, param, memery);
            }
        }
    }

    public static void dockerRun(Class clazz, List<String> lines, String dockerVersion, DockerRunBase dockerRun, RabbitmqClusterHost dockerHost, String param, Map<String, String> hostMap, List<String> ports) {
        String memery = "600m";
        List<String> hosts;
        lines.add("cd " + FWPathUtil.getClassPath(clazz));
        {
            hosts = dockerHost.rabbitmq_host();
            hosts.get(0);
            for (String host : hosts) {
                param = host;
                dockerRun.dockerRun(lines, dockerVersion, host, param, memery);
            }
        }
    }

    @Override
    public List<String> dockerSpecialYum() {
        List<String> yums = Lists.newArrayList();
        yums.add("logrotate socat");
        return yums;
    }

    @Override
    public void dockerShell(File file) throws Exception {
        List<String> lines = new ArrayList<>();
        for (DockerBuildVO dockerBuildVO : dockerBuildVOs) {
            String tar = dockerBuildVO.getTar();
            ShellWriteUtil.writeRpm(file, tar);
        }
        {
            // lines.add("rabbitmqctl stop_app");
            lines.add("sleep 1");
            String cmd = "ps -ef |grep  rabbitmq|grep -v root|awk '{print \"kill -9 \" $2}'|sh";
            lines.add(cmd);
            // lines.add("cat /usr/soft/rabbitmq.config > /etc/rabbitmq/rabbitmq.config");
            // lines.add(cmd);
            // lines.add(cmd);
            // lines.add("rabbitmq-server stop");
            // lines.add("nohup rabbitmq-server -detached &.");
            // lines.add("nohup rabbitmq-server stop &.");
            lines.add("sleep 1");
            lines.add("nohup  rabbitmq-server   -detached &.");
            lines.add("sleep 10");
            // lines.add("rabbitmqctl reset");
            lines.add("rabbitmq-plugins enable rabbitmq_management");
            lines.add("sleep 1");
            lines.add("rabbitmqctl add_user admin admin");
            lines.add("sleep 1");
            lines.add("rabbitmqctl set_permissions -p '/' admin '.*' '.*' '.*'");
            lines.add("sleep 1");
            // lines.add("rabbitmqctl set_permissions -p \"/\" admin \".*\" \".*\" \".*\"");
            lines.add("rabbitmqctl set_user_tags admin administrator");
            lines.add("cp /var/lib/rabbitmq/.erlang.cookie " + PropsDockerUtil.dockerDir + "data");
            lines.add("sleep 2");
            // lines.add("chmod 777 /var/lib/rabbitmq/.erlang.cookie");
            // lines.add("echo QAZWSX >/var/lib/rabbitmq/.erlang.cookie");
            // lines.add("echo /usr/soft/data/rbcluster1.erlang.cookie >/var/lib/rabbitmq/.erlang.cookie");
            // lines.add("chmod 400 /var/lib/rabbitmq/.erlang.cookie");
            // lines.add("rabbitmqctl stop_app");
            // lines.add("rabbitmqctl reset");
            // lines.add("rabbitmqctl start_app");
        }
        ShellWriteUtil.writeListTail(file, lines);
    }

    @Override
    public List<File> buildConfigFile() {
        List<File> files = Lists.newArrayList();
        try {
            boolean check = false;
            List<String> lines;
            File config = File.createTempFile("rabbitmq.config" + DockerBase.tempSub, DockerBase.tempSub);
            {
                String cluster_nodes = "";
                lines = Lists.newArrayList();
                lines.add("[");
                if (CollectionUtils.isNotEmpty(hosts)) {
                    for (String host : hosts) {
                        if (host.contains("cluster")) {
                            check = true;
                            cluster_nodes += ",'rabbitmq@" + host + "'";
                        }
                    }
                    if (cluster_nodes.length() > 0) {
                        lines.add("{cluster_nodes, {[" + cluster_nodes.substring(1) + "], disc}}");
                    }
                }
                lines.add("].");
                FileWriteUtil.writeLines(config, lines);
            }
            if (check) {
                files.add(config);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return files;
    }
}
