package com.k.docker.docker.amd.soft.rabbitmq.cluster.install;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.softmix.rabbitmq.cluster.build.RabbitmqClusterBuild;
import com.k.docker.docker.amd.softmix.rabbitmq.cluster.config.host.RabbitmqClusterHost;
import com.k.docker.docker.amd.softmix.rabbitmq.cluster.resource.RabbitmqClusterRes;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.soft.base.impl.DockerBaseImpl;
import com.k.docker.docker.util.docker.SoftVersion;
import com.k.docker.docker.util.file.ShellWriteUtil;
import com.k.docker.docker.util.path.FWPathUtil;
import com.k.docker.docker.util.vo.DockerBuildVO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RabbitmqClusterInstall extends DockerBaseImpl {
    public RabbitmqClusterInstall() {
        dockerBuildVOs = Lists.newArrayList();
        dockerBuildVOs.add(SoftVersion.rabbitmqRrlangSoft);
        dockerBuildVOs.add(SoftVersion.rabbitmqServerSoft);
    }

    public RabbitmqClusterInstall(final List<String> hosts) {
        dockerBuildVOs = Lists.newArrayList();
        dockerBuildVOs.add(SoftVersion.rabbitmqRrlangSoft);
        dockerBuildVOs.add(SoftVersion.rabbitmqServerSoft);
        this.hosts = hosts;
    }

    // public static void dockerRun(Class clazz, List<String> lines, String dockerVersion, DockerRunBase dockerRun, RabbitmqHost dockerHost, String param, Map<String, String> hostMap, List<String> ports) {
    // String memery = "600m";
    // String dns_dnsmasq_path = FWPathUtil.getClassPath(DnsConfig.class);
    // List<String> hosts;
    // lines.add("cd " + FWPathUtil.getClassPath(clazz));
    // // monitor
    // {
    // param = "server";
    // hosts = dockerHost.rabbitmq_host();
    // String firstHost = "rbcluster1";
    // for (String host : hosts) {
    // if (!firstHost.equals(host)) {
    // lines.add("sleep 10");
    // }
    // dockerRun.dockerRun(lines, dockerVersion, host, param, hostMap, ports, dns_dnsmasq_path, memery);
    // }
    // }
    // }
    public static void dockerRun(final Class clazz, final List<String> lines, final String dockerVersion, final DockerRunBase dockerRun, final RabbitmqClusterHost dockerHost, String param, final Map<String, String> hostMap, final List<String> ports) {
        final List<String> hosts;
        lines.add("cd " + FWPathUtil.getClassPath(clazz));
        // monitor
        {
            param = "server";
            hosts = dockerHost.rabbitmq_host();
            final String firstHost = "rbcluster1";
            for (final String host : hosts) {
                dockerRun.dockerRun(lines, dockerVersion, host, param);
                if (firstHost.equals(host)) {
                    lines.add("sleep 30");
                }
            }
        }
    }

    @Override
    public List<String> dockerSpecialYum() {
        final List<String> yums = Lists.newArrayList();
        yums.add("logrotate socat");
        return yums;
    }

    @Override
    public void dockerShell(final File file) throws Exception {
        final String param1 = "$1";
        {
            FWPathUtil.copyFileToDir(RabbitmqClusterRes.class, RabbitmqClusterBuild.class);
        }
        final List<String> lines = new ArrayList<>();
        for (final DockerBuildVO dockerBuildVO : dockerBuildVOs) {
            final String tar = dockerBuildVO.getTar();
            if (tar.contains(".rpm")) {
                ShellWriteUtil.writeRpm(file, tar);
            }
        }
        {
            start(lines);
            restart(lines, param1);
        }
        ShellWriteUtil.writeListTail(file, lines);
    }

    private void start(final List<String> lines) {
        {
            // lines.add("rabbitmqctl stop_app");
            final String cmd = "ps -ef |grep  rabbitmq|grep -v root|awk '{print \"kill -9 \" $2}'|sh";
            lines.add(cmd);
            lines.add("sleep 1");
            lines.add("nohup rabbitmq-server -detached &.");
            lines.add("sleep 10");
            lines.add("chmod 777 /var/lib/rabbitmq/.erlang.cookie  ");
            lines.add("echo 'CWZDQNTMBMFZWWSPJSER' > /var/lib/rabbitmq/.erlang.cookie ");
            lines.add("chmod 400 /var/lib/rabbitmq/.erlang.cookie  ");
            lines.add(cmd);
        }
    }

    private void restart(final List<String> lines, final String param1) {
        final String host = "rbcluster1";
        // ShellWriteUtil.writeIfNoEqualParamToSleep(lines, param1, host);
        lines.add("nohup rabbitmq-server  &.");
        // lines.add("nohup rabbitmq-server -detached &.");
        lines.add("sleep 20");
        lines.add("rabbitmq-plugins enable rabbitmq_management");
        lines.add("rabbitmqctl add_user admin admin");
        lines.add("rabbitmqctl set_permissions -p '/' admin '.*' '.*' '.*'");
        // lines.add("rabbitmqctl set_permissions -p \"/\" admin \".*\" \".*\" \".*\"");
        lines.add("rabbitmqctl set_user_tags admin administrator");
        // lines.add("cp /var/lib/rabbitmq/.erlang.cookie /usr/soft/data");
        ShellWriteUtil.writeIfNoEqualParam(lines, param1, host);
        lines.add("rabbitmqctl stop_app");
        lines.add("sleep 1");
        lines.add("rabbitmqctl join_cluster rabbit@" + host);
        lines.add("rabbitmqctl start_app");
        ShellWriteUtil.writeIfEnd(lines);
    }

    private void restartak(final List<String> lines) {
        {
            // lines.add("rabbitmqctl stop_app");
            lines.add("sleep 1");
            final String cmd = "ps -ef |grep  rabbitmq|grep -v root|awk '{print \"kill -9 \" $2}'|sh";
            lines.add(cmd);
            lines.add("sleep 1");
            // lines.add(cmd);
            // lines.add(cmd);
            // lines.add("rabbitmq-server stop");
            lines.add("nohup rabbitmq-server  &.");
            // lines.add("nohup rabbitmq-server -detached &.");
            // lines.add("nohup rabbitmq-server stop &.");
            // lines.add("sleep 1");
            // lines.add("nohup rabbitmq-server restart &.");
            lines.add("sleep 2");
            // lines.add("rabbitmqctl reset");
            lines.add("rabbitmq-plugins enable rabbitmq_management");
            lines.add("sleep 1");
            lines.add("rabbitmqctl add_user admin admin");
            lines.add("sleep 1");
            lines.add("rabbitmqctl set_permissions -p '/' admin '.*' '.*' '.*'");
            lines.add("sleep 1");
            // lines.add("rabbitmqctl set_permissions -p \"/\" admin \".*\" \".*\" \".*\"");
            lines.add("rabbitmqctl set_user_tags admin administrator");
            // lines.add("cp /var/lib/rabbitmq/.erlang.cookie /usr/soft/data");
            lines.add("sleep 10");
            lines.add("rabbitmqctl join_cluster rabbit@rbmq1");
            lines.add("rabbitmqctl start_app");
        }
    }
}
