package com.k.docker.docker.base.soft.base;

import com.k.docker.docker.util.file.DockerWriteUtil;
import com.k.docker.docker.util.vo.DockerBuildVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class DockerBase {
    public final static String tempSub = ".tmp";

    /**
     * 处理软件文件
     */
    public abstract void dockerFile(File file) throws Exception;

    public abstract void dockerShellStart(File file) throws Exception;

    public abstract void dockerShell(File file) throws Exception;

    /**
     * 添加配置文件
     */
    public abstract List<File> buildConfigFile() throws Exception;

    public abstract void buildConfigFile(List<File> files) throws Exception;

    public abstract void buildConfigFile(List<File> files, List<String> hosts) throws Exception;

    public List<String> dockerSpecialYum() throws Exception {
        return null;
    }

    /**
     * 添加需要执行的命名
     */
    public List<String> dockerSpecialRuns() {
        return null;
    }

    public void dockerYums(final File dockerFile, final String from) throws Exception {
        final List<String> yums = dockerSpecialYum();
        if (CollectionUtils.isNotEmpty(yums)) {
            String cmds = "";
            for (final String yumPlugin : yums) {
                cmds += yumPlugin + " ";
            }
            if (from.contains("ubuntu")) {
                DockerWriteUtil.writeRun(dockerFile, "apt-get -y install " + cmds);
            } else if (from.contains("centos")) {
                DockerWriteUtil.writeRun(dockerFile, "yum -y install " + cmds);
            }
        }
    }

    public void dockerRuns(final File dockerFile) throws Exception {
        final List<String> runs = dockerSpecialRuns();
        if (CollectionUtils.isNotEmpty(runs)) {
            for (final String run : runs) {
                DockerWriteUtil.writeRun(dockerFile, run);
            }
        }
    }

    public void dockerDealWithConfigFile(final File file) throws Exception {
        final List<File> files = buildConfigFile();
        File destFile;
        if (CollectionUtils.isNotEmpty(files)) {
            for (final File fspecial : files) {
                final String fileName = fspecial.getName().substring(0, fspecial.getName().indexOf(tempSub));
                DockerWriteUtil.writeCopy(file, fileName);
                destFile = new File(file.getParentFile().getAbsolutePath() + "/" + fileName);
                FileUtils.moveFile(fspecial, destFile);
            }
        }
    }

    public void dockerDealWithResFile(final File file) throws Exception {
        List<DockerBuildVO> builds = dockerResFile();
        if (CollectionUtils.isNotEmpty(builds)) {
            for (DockerBuildVO build : builds) {
                DockerWriteUtil.writeCopy(file, build.getTar());
            }
        }
    }

    /**
     * 添加特殊配置文件
     */
    public List<DockerBuildVO> dockerResFile() throws Exception {
        return Collections.emptyList();
    }

    public abstract List<DockerBuildVO> queryAllDockerTar() throws Exception;

    public abstract void dockerRun(List<String> lines, String dockerVersion, String dockerHost, String param, Map<String, String> hostMap, List<String> ports, String dns_dnsmasq_path) throws Exception;

    public abstract void dockerRun(List<String> lines, String dockerVersion, String dockerHost, String param, Map<String, String> hostMap, List<String> ports, String dns_dnsmasq_path, String memery) throws Exception;
}
