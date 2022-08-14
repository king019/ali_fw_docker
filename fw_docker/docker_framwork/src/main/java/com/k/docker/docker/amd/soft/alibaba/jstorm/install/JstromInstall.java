package com.k.docker.docker.amd.soft.alibaba.jstorm.install;

import com.google.common.collect.Lists;
import com.k.docker.docker.base.soft.base.DockerBase;
import com.k.docker.docker.base.soft.base.impl.DockerBaseImpl;
import com.k.docker.docker.util.docker.SoftVersion;
import com.k.docker.docker.util.file.FileWriteUtil;
import com.k.docker.docker.util.file.ShellWriteUtil;
import com.k.docker.docker.util.props.PropsDockerUtil;
import com.k.docker.docker.util.vo.DockerBuildVO;

import java.io.File;
import java.util.List;

public class JstromInstall extends DockerBaseImpl {
    public JstromInstall(final List<String> hosts) {
        dockerBuildVOs.add(SoftVersion.jstormSoft);
        dockerBuildVOs.add(SoftVersion.jstormUiSoft);
        this.hosts = hosts;
    }

    @Override
    public List<String> dockerSpecialYum() {
        final List<String> yums = Lists.newArrayList();
        yums.add("unzip which");
        return yums;
    }

    @Override
    public void dockerShell(final File file) throws Exception {
        ShellWriteUtil.writeCatAppend(file, "profileJstorm", "/etc/profile");
        ShellWriteUtil.writeSourceProfile(file);
        ShellWriteUtil.writeShell(file, "java -verbose >> java_version");
        final String compareParam = "$1";
        final String param = "$2";
        ShellWriteUtil.writeSleep(file, "70");
        for (final DockerBuildVO dockerBuildVO : dockerBuildVOs) {
            final String tar = dockerBuildVO.getTar();
            final String unpressc = dockerBuildVO.getUnpress();
            final String mvDirc = dockerBuildVO.getMvDir();
            ShellWriteUtil.writeIfStartParam(file, mvDirc, compareParam);
            if (tar.endsWith(".war")) {
                tomcatWar(file, tar, unpressc, mvDirc);
            } else {
                binZip(file, tar, unpressc, mvDirc, param);
            }
            ShellWriteUtil.writeIfEnd(file);
        }
    }

    @Override
    public List<File> buildConfigFile() {
        final List<File> files = Lists.newArrayList();
        try {
            File profile;
            {
                profile = File.createTempFile("profileJstorm" + DockerBase.tempSub, DockerBase.tempSub);
                {
                    FileWriteUtil.write(profile, "export JSTORM_HOME=" + PropsDockerUtil.dockerDir + "jstorm");
                    FileWriteUtil.write(profile, "export PATH=$JSTORM_HOME/bin:$PATH");
                }
                files.add(profile);
            }
            {
                profile = File.createTempFile("storm.yaml.bak" + DockerBase.tempSub, DockerBase.tempSub);
                {
                    FileWriteUtil.writeNoneLineFeed(profile, "storm.zookeeper.servers:");
                    for (final String host : hosts) {
                        FileWriteUtil.write(profile, "     - \"" + host + "\"");
                    }
                    FileWriteUtil.write(profile, "storm.zookeeper.root: \"/jstorm\"");
                    FileWriteUtil.write(profile, "storm.local.dir: \"" + PropsDockerUtil.dockerDir + "jstorm/data\"");
                    FileWriteUtil.write(profile, "supervisor.slots.ports:");
                    FileWriteUtil.write(profile, "    - 6800");
                    FileWriteUtil.write(profile, "    - 6801");
                    FileWriteUtil.write(profile, "    - 6802");
                    FileWriteUtil.write(profile, "    - 6803");
                    FileWriteUtil.write(profile, "    - 6804");
                    // FileWriteUtil.write(profile, " - 6805");
                    // FileWriteUtil.write(profile, " - 6806");
                    // FileWriteUtil.write(profile, " - 6807");
                    // FileWriteUtil.write(profile, " - 6808");
                    // FileWriteUtil.write(profile, " - 6809");
                    // FileWriteUtil.write(profile, " - 6810");
                    // FileWriteUtil.write(profile, " - 6811");
                    final boolean bui = true;
                    if (bui) {
                        FileWriteUtil.write(profile, "ui.clusters:");
                        FileWriteUtil.write(profile, "    - {");
                        FileWriteUtil.write(profile, "       name: \"jstorm\",");
                        FileWriteUtil.write(profile, "       zkRoot: \"/jstorm\",");
                        FileWriteUtil.write(profile, "       zkServers:");
                        String hostStr = "";
                        hostStr += "           [ ";
                        int num = 0;
                        for (final String host : hosts) {
                            num++;
                            hostStr += " \"" + host + "\"" + (num != hosts.size() ? "," : "");
                        }
                        hostStr += "], ";
                        FileWriteUtil.write(profile, hostStr);
                        FileWriteUtil.write(profile, "       zkPort: 2181");
                        FileWriteUtil.write(profile, "     }");
                    }
                }
                files.add(profile);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return files;
    }

    private void tomcatWar(final File file, final String tarc, final String unpressc, String mvDirc) throws Exception {
        mvDirc = "tomcat/webapps/ROOT.war";
        String mvSrc = "tomcat/webapps";
        ShellWriteUtil.writeRm(file, mvSrc);
        ShellWriteUtil.writeMkdir(file, mvSrc);
        ShellWriteUtil.writeSleep(file);
        ShellWriteUtil.writeCp(file, tarc, mvDirc);
        {
            mvSrc = "/root/.jstorm";
            ShellWriteUtil.writeRmAbs(file, mvSrc);
            ShellWriteUtil.writeMkdirAbs(file, mvSrc);
            mvSrc = mvSrc + "/storm.yaml";
            ShellWriteUtil.writeCpAbs(file, "storm.yaml.bak", mvSrc);
        }
        final List<String> lines = Lists.newArrayList();
        lines.add("source /etc/profile");
        lines.add(PropsDockerUtil.dockerDir + "tomcat/bin/shutdown.sh");
        lines.add(PropsDockerUtil.dockerDir + "tomcat/bin/startup.sh");
        ShellWriteUtil.writeListTail(file, lines);
    }

    private void binZip(final File file, final String tarc, final String unpressc, final String mvDirc, final String compareParam) throws Exception {
        ShellWriteUtil.writeUnzip(file, tarc);
        ShellWriteUtil.writeMv(file, unpressc, mvDirc);
        {
            final String mvSrc = PropsDockerUtil.dockerDir + "jstorm/conf/storm.yaml";
            ShellWriteUtil.writeRmAbs(file, mvSrc);
            ShellWriteUtil.writeCpAbs(file, "storm.yaml.bak", mvSrc);
        }
        {
            String mvSrc = "/root/.jstorm";
            ShellWriteUtil.writeRm(file, mvSrc);
            ShellWriteUtil.writeMkdirAbs(file, mvSrc);
            mvSrc = mvSrc + "/storm.yaml";
            ShellWriteUtil.writeCpAbs(file, "storm.yaml.bak", mvSrc);
        }
        final List<String> lines = Lists.newArrayList();
        lines.add("source /etc/profile");
        // lines.add("jstorm " + compareParam);
        lines.add("nohup " + PropsDockerUtil.dockerDir + mvDirc + "/bin/jstorm " + compareParam + " &");
        ShellWriteUtil.writeListTail(file, lines);
    }
}
