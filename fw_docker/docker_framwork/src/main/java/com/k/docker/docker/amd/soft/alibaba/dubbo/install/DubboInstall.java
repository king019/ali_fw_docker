package com.k.docker.docker.amd.soft.alibaba.dubbo.install;

import com.google.common.collect.Lists;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.docker.wlan.base.impl.DubboHostBase;
import com.k.docker.docker.base.soft.base.impl.DockerBaseImpl;
import com.k.docker.docker.util.docker.SoftVersion;
import com.k.docker.docker.util.file.ShellWriteUtil;
import com.k.docker.docker.util.path.FWPathUtil;
import com.k.docker.docker.util.props.PropsDockerUtil;
import com.k.docker.docker.util.vo.DockerBuildVO;

import java.io.File;
import java.util.List;

public class DubboInstall extends DockerBaseImpl {
    public DubboInstall() {
        dockerBuildVOs = Lists.newArrayList();
        dockerBuildVOs.add(SoftVersion.dubboAdminSoft);
        dockerBuildVOs.add(SoftVersion.dubboMonitorSoft);
        dockerBuildVOs.add(SoftVersion.dubboConsumerSoft);
        dockerBuildVOs.add(SoftVersion.dubboProviderSoft);
        dockerBuildVOs.add(SoftVersion.dubboDubbowebSoft);
    }

    public static void dockerRun(final Class clazz, final List<String> lines, final String dockerVersion, final DockerRunBase dockerRun, final DubboHostBase dubboHost, String param) {
        List<String> hosts;
        final String memery = "500m";
        lines.add("cd " + FWPathUtil.getClassPath(clazz));
        // admin
        {
            param = "admin";
            hosts = dubboHost.dubbo_admin();
            dealDockerRunNoLimitMemery(lines, hosts, dockerRun, dockerVersion, param);
        }
        // monitor
        {
            param = "monitor";
            hosts = dubboHost.dubbo_monitorr();
            dealDockerRun(lines, hosts, dockerRun, dockerVersion, param);
        }
        // provider
        {
            param = "provider";
            hosts = dubboHost.dubbo_provider();
            dealDockerRun(lines, hosts, dockerRun, dockerVersion, param, memery);
        }
        // consumer
        {
            String localmemery = "800m";
            param = "consumer";
            hosts = dubboHost.dubbo_consumer();
            dealDockerRun(lines, hosts, dockerRun, dockerVersion, param, localmemery);
        }
        // dubboweb
        {
            param = "dubboweb";
            hosts = dubboHost.dubbo_dubboweb();
            dealDockerRun(lines, hosts, dockerRun, dockerVersion, param, memery);
        }
    }

    @Override
    public List<String> dockerSpecialYum() {
        final List<String> yums = Lists.newArrayList();
        yums.add(" net-tools");
        return yums;
    }

    @Override
    public void dockerShell(final File file) throws Exception {
        final List<String> lines = Lists.newArrayList();
        lines.add("sleep 50");
        lines.add("mkdir -p /monitor/statistics");
        lines.add("mkdir -p /monitor/charts");
        ShellWriteUtil.writeList(file, lines);
        final String compareParam = "$1";
        for (final DockerBuildVO dockerBuildVO : dockerBuildVOs) {
            final String tar = dockerBuildVO.getTar();
            final String unpressc = dockerBuildVO.getUnpress();
            final String mvDirc = dockerBuildVO.getMvDir();
            ShellWriteUtil.writeIfStartParam(file, mvDirc, compareParam);
            if (tar.endsWith(".war")) {
                tomcatWar(file, tar, unpressc, mvDirc);
            } else {
                binGz(file, tar, unpressc, mvDirc);
            }
            ShellWriteUtil.writeIfEnd(file);
        }
    }

    private void tomcatWar(final File file, final String tarc, final String unpressc, String mvDirc) throws Exception {
        mvDirc = "tomcat/webapps/ROOT.war";
        final String mvSrc = "tomcat/webapps";
        ShellWriteUtil.writeRm(file, mvSrc);
        ShellWriteUtil.writeMkdir(file, mvSrc);
        ShellWriteUtil.writeChmod(file);
        ShellWriteUtil.writeSleep(file);
        ShellWriteUtil.writeCp(file, tarc, mvDirc);
        final List<String> lines = Lists.newArrayList();
        lines.add(PropsDockerUtil.dockerDir + "tomcat/bin/shutdown.sh");
        lines.add(PropsDockerUtil.dockerDir + "tomcat/bin/startup.sh");
        ShellWriteUtil.writeListTail(file, lines);
    }

    private void binGz(final File file, final String tarc, final String unpressc, final String mvDirc) throws Exception {
        ShellWriteUtil.writeTar(file, tarc);
        ShellWriteUtil.writeMv(file, unpressc, mvDirc);
        final List<String> lines = Lists.newArrayList();
        if (tarc.contains("consumer")) {
            lines.add("sleep 10");
        }
        lines.add(PropsDockerUtil.dockerDir + mvDirc + "/bin/restart.sh");
        ShellWriteUtil.writeListTail(file, lines);
    }
}
