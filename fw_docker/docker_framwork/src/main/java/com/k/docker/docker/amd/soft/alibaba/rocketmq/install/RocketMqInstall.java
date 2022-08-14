package com.k.docker.docker.amd.soft.alibaba.rocketmq.install;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.softmix.alibaba.rocketmq.rocketmq.config.host.RocketmqHost;
import com.k.docker.docker.base.soft.base.DockerBase;
import com.k.docker.docker.base.soft.base.impl.DockerBaseImpl;
import com.k.docker.docker.util.docker.SoftVersion;
import com.k.docker.docker.util.file.FileWriteUtil;
import com.k.docker.docker.util.file.ShellWriteUtil;
import com.k.docker.docker.util.props.PropsDockerUtil;
import com.k.docker.docker.util.vo.DockerBuildVO;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.List;

public class RocketMqInstall extends DockerBaseImpl {
    private static String tarc;
    private static String mvDirc;

    public RocketMqInstall() {
        dockerBuildVOs.add(SoftVersion.rockmqBrokerSoft);
        dockerBuildVOs.add(SoftVersion.rockmqConsoleSoft);
    }

    @Override
    public void dockerShell(final File file) throws Exception {
        final String compareParam = "$1";
        for (final DockerBuildVO dockerBuildVO : dockerBuildVOs) {
            buildNoConsole(file, dockerBuildVO, compareParam);
            buildConsole(file, dockerBuildVO, compareParam);
        }
    }

    private void buildNoConsole(final File file, final DockerBuildVO dockerBuildVO, final String compareParam) throws Exception {
        if (dockerBuildVO.tar.contains(".gz")) {
            ShellWriteUtil.writeIfContainParam(file, "mq", compareParam);
            {
                ShellWriteUtil.writeTar(file, dockerBuildVO.tar);
                ShellWriteUtil.writeMv(file, dockerBuildVO.unpress, dockerBuildVO.mvDir);
                {
                    ShellWriteUtil.writeCatAppend(file, "profileRocketmq", "/etc/profile");
                    ShellWriteUtil.writeSourceProfile(file);
                    ShellWriteUtil.writeShell(file, "java -verbose >> java_version");
                }
                {
                    ShellWriteUtil.writeMkdirAbs(file, " /root/tmpfs");
                    ShellWriteUtil.writeMkdirAbs(file, "  /root/tmpfs/logs/");
                    ShellWriteUtil.writeMkdir(file, dockerBuildVO.mvDir + "/log");
                }
                cpfile(file, dockerBuildVO);
                nameServer(file, dockerBuildVO, compareParam);
                nameBroker(file, dockerBuildVO, compareParam, compareParam);
            }
            ShellWriteUtil.writeIfEnd(file);
        }
    }

    private void buildConsole(final File file, final DockerBuildVO dockerBuildVO, final String compareParam) throws Exception {
        if (dockerBuildVO.tar.contains(".war")) {
            ShellWriteUtil.writeIfContainParam(file, "console", compareParam);
            mvDirc = "tomcat/webapps/ROOT.war";
            final String mvSrc = "tomcat/webapps";
            ShellWriteUtil.writeRm(file, mvSrc);
            ShellWriteUtil.writeMkdir(file, mvSrc);
            ShellWriteUtil.writeSleep(file);
            ShellWriteUtil.writeCp(file, tarc, mvDirc);
            final List<String> lines = Lists.newArrayList();
            lines.add(PropsDockerUtil.dockerDir + "tomcat/bin/shutdown.sh");
            lines.add(PropsDockerUtil.dockerDir + "tomcat/bin/startup.sh");
            ShellWriteUtil.writeListTail(file, lines);
            ShellWriteUtil.writeIfEnd(file);
        }
    }

    private void cpfile(final File file, final DockerBuildVO dockerBuildVO) throws Exception {
        final RocketmqHost host = new RocketmqHost();
        final List<String> masters = host.rocketmq_master();
        String prop;
        for (int i = 0; i < masters.size(); i++) {
            {
                prop = "mqbrokerMaster" + i + ".properties";
                ShellWriteUtil.writeCpAbs(file, prop, PropsDockerUtil.desDir(dockerBuildVO.mvDir) + "/conf/" + prop);
                prop = "mqbrokerSlave" + i + ".properties";
                ShellWriteUtil.writeCpAbs(file, prop, PropsDockerUtil.desDir(dockerBuildVO.mvDir) + "/conf/" + prop);
            }
        }
    }

    private void nameServer(final File file, final DockerBuildVO dockerBuildVO, final String compareParam) throws Exception {
        ShellWriteUtil.writeIfStartParam(file, "mqnamesrv", compareParam);
        ShellWriteUtil.writeCdSoft(file);
        final List<String> lines = Lists.newArrayList();
        lines.add("cd " + PropsDockerUtil.desDirBin(dockerBuildVO.mvDir));
        lines.add("nohup ./mqnamesrv &");
        ShellWriteUtil.writeListTail(file, lines);
        ShellWriteUtil.writeIfEnd(file);
    }

    private void nameBroker(final File file, final DockerBuildVO dockerBuildVO, final String compareParam, final String brockProps) throws Exception {
        ShellWriteUtil.writeIfContainParam(file, "mqbroker", compareParam);
        final RocketmqHost hosts = new RocketmqHost();
        final List<String> nss = hosts.rocketmq_nameServer();
        final String host = nss.get(0);
        ShellWriteUtil.writeCdSoft(file);
        final List<String> lines = Lists.newArrayList();
        lines.add("cd " + PropsDockerUtil.desDirBin(dockerBuildVO.mvDir));
        final String prop = brockProps + ".properties";
        // nohup sh mqbroker -n 192.168.1.102:9876 -c $ROCKETMQ_HOME/conf/2m-2s-async/broker-b.properties >$ROCKETMQ_HOME/log/mq.log &
        lines.add("nohup ./mqbroker -n " + host + ":9876 -c $ROCKETMQ_HOME/conf/" + prop + " >$ROCKETMQ_HOME/log/mq.log &");
        // lines.add("nohup ./mqbroker &");
        ShellWriteUtil.writeListTail(file, lines);
        ShellWriteUtil.writeIfEnd(file);
    }

    @Override
    public List<File> buildConfigFile() {
        final RocketmqHost host = new RocketmqHost();
        final List<String> masters = host.rocketmq_master();
        final List<String> nameServers = host.rocketmq_nameServer();
        final List<File> files = Lists.newArrayList();
        final String ns = buildNameServer(nameServers);
        try {
            File profile;
            {
                profile = File.createTempFile("profileRocketmq" + DockerBase.tempSub, DockerBase.tempSub);
                {
                    FileWriteUtil.write(profile, "export ROCKETMQ_HOME=" + PropsDockerUtil.dockerDir + "rocketmq");
                    FileWriteUtil.write(profile, "export PATH=$ROCKETMQ_HOME/bin:$PATH");
                }
                files.add(profile);
            }
            {
                profile = File.createTempFile("config.properties" + DockerBase.tempSub, DockerBase.tempSub);
                {
                    FileWriteUtil.write(profile, "rocketmq.namesrv.addr=" + ns);
                    FileWriteUtil.write(profile, "throwDone=true");
                }
                files.add(profile);
            }
            for (int i = 0; i < masters.size(); i++) {
                {
                    buildBrock(files, "Master", i, 0, ns);
                    buildBrock(files, "Slave", i, 1, ns);
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return files;
    }

    private String buildNameServer(final List<String> hosts) {
        return StringUtils.join(hosts, ":9876;") + ":9876";
    }

    private void buildBrock(final List<File> files, final String master_slave, final int index, final int brokerId, final String nameServers) throws Exception {
        final File profile;
        profile = File.createTempFile("mqbroker" + master_slave + +index + ".properties" + DockerBase.tempSub, DockerBase.tempSub);
        {
            FileWriteUtil.write(profile, "brokerClusterName=DefaultCluster");
            FileWriteUtil.write(profile, "brokerName=broker-" + index);
            FileWriteUtil.write(profile, "brokerId=" + brokerId);
            FileWriteUtil.write(profile, "deleteWhen=04");
            FileWriteUtil.write(profile, "fileReservedTime=48");
            FileWriteUtil.write(profile, "brokerRole=ASYNC_MASTER");
            FileWriteUtil.write(profile, "flushDiskType=ASYNC_FLUSH");
            FileWriteUtil.write(profile, "namesrvAddr=" + nameServers);
        }
        files.add(profile);
    }
}
