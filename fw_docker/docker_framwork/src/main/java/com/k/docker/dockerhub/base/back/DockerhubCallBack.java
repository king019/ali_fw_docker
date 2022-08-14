package com.k.docker.dockerhub.base.back;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.k.docker.docker.base.docker.image.DockerImageRemoveBase;
import com.k.docker.docker.util.command.container.DockerContainer;
import com.k.docker.docker.util.command.exec.DockerExec;
import com.k.docker.docker.util.command.run.DockerRun;
import com.k.docker.docker.util.file.FileWriteUtil;
import com.k.docker.docker.util.path.FWPathUtil;
import com.k.docker.docker.util.props.PropsDockerUtil;
import com.k.docker.dockerhub.base.enums.DockerPlatformEnum;
import com.k.docker.dockerhub.base.enums.DockerRegionEnum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.Set;

public abstract class DockerhubCallBack {

    public Set<String> hosts = Sets.newLinkedHashSet();
    private String shPath;
    private File shFile;
    private String fileName;
    private List<String> commands = Lists.newArrayList();
    private List<DockerRun> runs = Lists.newArrayList();
    private List<DockerContainer> containers = Lists.newArrayList();
    private List<DockerExec> execs = Lists.newArrayList();

    @Before
    public void bf() {
        fileName = getClass().getSimpleName() + ".sh";
        shPath = FWPathUtil.getTargetPath(getClass()) + "/" + fileName;
        shFile = FileWriteUtil.getFile(shPath);
        {
            writeDockerLines(commands, Lists.newArrayList(hosts));
            writeDockerRuns(runs);
            buildRegion(runs);
            //buildRegion(DockerRegionEnum region,  List<DockerRun> runs);

        }
        {
            putHost();
        }

    }

    public void writeDockerLines(List<String> lines, List<String> hosts) {

    }

    public void writeDockerRuns(List<DockerRun> runs) {
    }

    public void buildRegion(List<DockerRun> runs) {
        for (DockerRegionEnum region : DockerRegionEnum.values()) {
            buildRegion(region, runs);
            //buildModel(name, DockerRegionEnum.getVersion(region, version), runs);
        }

    }

    public void buildRegion(DockerRegionEnum region, List<DockerRun> runs) {

    }

//    public void buildModel(String name, String version, List<DockerRun> runs) {
//    }

    @Test
    public void test() {
        bfBase();
        bfCmd();
        interval();
        {

            start();
            exec();
            //bfDns();
        }
        commands.add("");
        for (DockerContainer container : Sets.newLinkedHashSet(containers)) {
            commands.add(container.toString());
        }
        commands.add("");
        for (DockerExec exec : Sets.newLinkedHashSet(execs)) {
            commands.add(exec.toString());
        }
        commands.add("");
        for (DockerRun run : Sets.newLinkedHashSet(runs)) {
            commands.add(run.toString());
        }
    }

    private void putHost() {
        for (DockerRun run : runs) {
            hosts.add(run.nameCmd());
        }

    }

    private void start() {
        runs.forEach(run -> containers.add(DockerContainer.builder().nameCmd(run.nameCmd()).build()));
    }

    private void exec() {
        runs.forEach(run -> execs.add(DockerExec.builder().nameCmd(run.nameCmd()).build()));
    }

    private void interval() {
        commands.add("");
        commands.add("");
        commands.add("");
    }

    private void bfBase() {
        commands.addAll(DockerImageRemoveBase.dockerRmCommand());
        commands.add("chmod -R 777 " + PropsDockerUtil.dockerDir + "docker");
        for (String host : hosts) {
            commands.add("docker ps -a |grep -w " + host + "|awk '{print \"docker rm -f  \" $1}'|sh");
        }
        for (String host : hosts) {
            commands.add(PropsDockerUtil.dockerDir + "docker/com/k/docker/base/dnsmasq/dnsdelete/dns_del.sh " + host);
        }
        interval();
    }

    private void bfDns() {
        for (String host : hosts) {
            commands.add(PropsDockerUtil.dockerDir + "docker/com/k/docker/base/dnsmasq/dnsdelete/dns_del.sh " + host);
        }
        interval();
    }

    private void bfCmd() {
        //https://blog.csdn.net/xl_lx/article/details/81183956
        commands.add(DockerPlatformEnum.AMD.toString());
        commands.add("scope launch");
        commands.add("docker run --restart=always --name ui-for-docker -d -p 9001:9000 --privileged -v /var/run/docker.sock:/var/run/docker.sock uifd/ui-for-docker");
        commands.add("docker run --restart=always --name portainer -d -p 9000:9000 -v /var/run/docker.sock:/var/run/docker.sock -v /opt/soft/docker/data/portainer:/data portainer/portainer:1.23.2");
        commands.add("docker run --restart=always --name portainer -d -p 9000:9000 -v /var/run/docker.sock:/var/run/docker.sock portainer/portainer:1.23.2");
        commands.add("docker run --restart=always --name portainer -d -p 9000:9000 -v /var/run/docker.sock:/var/run/docker.sock portainer/portainer");
        commands.add("docker run --restart=always --name portainer -d -p 9000:9000 -v /var/run/docker.sock:/var/run/docker.sock portainer/portainer-ce");
        commands.add("arm");
        commands.add("docker run --restart=always --name ui-for-docker -d -p 9001:9000 --privileged -v /var/run/docker.sock:/var/run/docker.sock hypriot/rpi-dockerui");
    }

    @After
    public void af() throws Exception {
        //afDns();
        afExec();
        FileWriteUtil.write(shFile, commands);
    }

    private void afExec() {
        commands.add("   ");

        for (String host : hosts) {
            commands.add("docker exec -it " + host + " /bin/bash");
            commands.add("docker exec -it " + host + " /bin/sh");
            commands.add("docker start " + host);
        }
    }

    private void afDns() {
        for (String host : hosts) {
            commands.add(PropsDockerUtil.dockerDir + "docker/com/k/docker/base/dnsmasq/dnsconfig/dns_dnsmasq.sh   " + host);
        }
    }

}
