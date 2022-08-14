package com.k.docker.docker.amd.soft.apache.zookeeper.install;

import com.google.common.collect.Lists;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.docker.wlan.base.impl.DubboHostBase;
import com.k.docker.docker.base.soft.base.impl.DockerBaseImpl;
import com.k.docker.docker.util.docker.SoftVersion;
import com.k.docker.docker.util.file.FileWriteUtil;
import com.k.docker.docker.util.file.ShellWriteUtil;
import com.k.docker.docker.util.path.FWPathUtil;
import com.k.docker.docker.util.props.PropsDockerUtil;
import com.k.docker.docker.util.vo.DockerBuildVO;
import lombok.Builder;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.List;

@Builder
public class ZookeeperInstall extends DockerBaseImpl {
    public ZookeeperInstall() {
        dockerBuildVOs.add(SoftVersion.zookeeperSoft);
    }

    public ZookeeperInstall(final List<String> hosts) {
        dockerBuildVOs.add(SoftVersion.zookeeperSoft);
        this.hosts = hosts;
    }

    public static void dockerRun(final Class clazz, final List<String> lines, final String dockerVersion, final DockerRunBase dockerRun, final DubboHostBase dubboHost, String param) {
        final List<String> hosts;
        lines.add("cd " + FWPathUtil.getClassPath(clazz));
        // zookeeper
        {
            hosts = dubboHost.dubbo_zookeeper();
            for (final String host : hosts) {
                param = host.substring(host.lastIndexOf("zk") + 2);
                dockerRun.dockerRun(lines, dockerVersion, host, param);
            }
        }
    }

    public static void dockerRun(final Class clazz, final List<String> lines, final String dockerVersion, final DockerRunBase dockerRun, final List<String> hosts, String param) {
        lines.add("cd " + FWPathUtil.getClassPath(clazz));
        // zookeeper
        {
            for (final String host : hosts) {
                param = host.substring(host.lastIndexOf("zk") + 2) + " zookeeper";
                dockerRun.dockerRun(lines, dockerVersion, host, param);
            }
        }
    }

    @Override
    public void dockerShell(final File file) throws Exception {
        final DockerBuildVO dockerSoft = queryTar("zookeeper");
        ShellWriteUtil.writeTar(file, dockerSoft.tar);
        ShellWriteUtil.writeMv(file, dockerSoft.unpress, dockerSoft.mvDir);
        ShellWriteUtil.writeMv(file, "zoo.cfg", "zookeeper/conf/zoo.cfg");
        ShellWriteUtil.writeChmod(file);
        final List<String> lines = Lists.newArrayList();
        lines.add("wk=$1");
        lines.add("echo $wk > " + PropsDockerUtil.dockerDir + "zookeeper/myid");
        // lines.add("sleep 20");
        lines.add(PropsDockerUtil.dockerDir + dockerSoft.mvDir + "/bin/zkServer.sh restart");
        // lines.add("sleep 10");
        // lines.add(PropsUtil.dockerDir + mvDirc + "/bin/zkServer.sh restart");
        ShellWriteUtil.writeListTail(file, lines);
    }

    @Override
    public void buildConfigFile(final List<File> files) throws Exception {
        files.addAll(buildConfigFile());
    }

    @Override
    public void buildConfigFile(final List<File> files, final List<String> hostsp) throws Exception {
        final List<String> lines;
        final File zoo = File.createTempFile("zoo.cfg" + tempSub, tempSub);
        {
            lines = Lists.newArrayList();
            lines.add("tickTime=2000");
            lines.add("initLimit=10");
            lines.add("syncLimit=5");
            lines.add("dataDir=" + PropsDockerUtil.dockerDir + "zookeeper");
            lines.add("clientPort=2181");
            if (CollectionUtils.isNotEmpty(hostsp) && hostsp.size() > 1) {
                int index = 1;
                for (final String host : hostsp) {
                    lines.add("server." + (index++) + "=" + host + ":2888:3888");
                }
            }
            lines.add("#" + StringUtils.join(hostsp, ":2181,") + ":2181");
            FileWriteUtil.writeLines(zoo, lines);
        }
        files.add(zoo);
    }

    @Override
    public List<File> buildConfigFile() {
        final List<File> files = Lists.newArrayList();
        try {
            buildConfigFile(files, hosts);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return files;
    }
}
