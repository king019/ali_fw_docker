package com.k.docker.docker.base.soft.base.impl;

import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.soft.base.DockerBase;
import com.k.docker.docker.util.file.DockerWriteUtil;
import com.k.docker.docker.util.file.ShellWriteUtil;
import com.k.docker.docker.util.vo.DockerBuildVO;
import com.k.docker.docker.util.vo.DockerGitBuildVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DockerBaseImpl extends DockerBase {
    public String memery = "300m";
    public List<String> hosts;
    public List<String> hosts2;
    //
    public boolean tarFlag = true;
    public boolean mvFlag = true;
    public boolean runFlag = true;
    //
    /**
     * 添加特殊tar文件
     */
    public List<DockerBuildVO> dockerBuildVOs = new ArrayList<>();
    /**
     * 添加特殊文件资源(本地存在的文件)
     */
    public List<DockerBuildVO> dockerResBuildVOs = new ArrayList<>();
    /**
     * 添加特殊tar文件(使用git文件)
     */
    public List<DockerGitBuildVO> dockerGitBuildVOs = new ArrayList<>();

    public DockerBaseImpl() {
        dockerResBuildVOs = dockerResFile();
    }

    public static void dealDockerRun(final List<String> lines, final List<String> hosts, final DockerRunBase dockerRun, final String dockerVersion, final String param) {
        for (final String host : hosts) {
            dockerRun.dockerRun(lines, dockerVersion, host, param);
        }
    }

    public static void dealDockerRunNoLimitMemery(final List<String> lines, final List<String> hosts, final DockerRunBase dockerRun, final String dockerVersion, final String param) {
        for (final String host : hosts) {
            dockerRun.dockerRunNoLimitMemery(lines, dockerVersion, host, param);
        }
    }

    public static void dealDockerRun(final List<String> lines, final List<String> hosts, final DockerRunBase dockerRun, final String dockerVersion, final String param, final String memery) {
        for (final String host : hosts) {
            dockerRun.dockerRun(lines, dockerVersion, host, param, memery);
        }
    }

    public static void dealDockerRunVolumeNoLimitMemery(final List<String> lines, final List<String> hosts, final DockerRunBase dockerRun, final String dockerVersion, final String param) {
        for (final String host : hosts) {
            dockerRun.dockerRunVolumeNoLimitMemery(lines, dockerVersion, host, param);
        }
    }

    public static void dealDockerRunParamHost(final List<String> lines, final List<String> hosts, final DockerRunBase dockerRun, final String dockerVersion) {
        for (final String host : hosts) {
            dockerRun.dockerRunNoLimitMemery(lines, dockerVersion, host, host);
        }
    }

    @Override
    public List<DockerBuildVO> queryAllDockerTar() {
        final List<DockerBuildVO> vos = new ArrayList<>();
        if (dockerBuildVOs != null) {
            vos.addAll(dockerBuildVOs);
        }
        return vos;
    }

    @Override
    public void dockerFile(final File file) throws Exception {
        if (CollectionUtils.isNotEmpty(dockerBuildVOs)) {
            for (final DockerBuildVO dockerBuildVO : dockerBuildVOs) {
                DockerWriteUtil.writeCopy(file, dockerBuildVO.getTar());
            }
        }
    }

    @Override
    public void dockerShellStart(final File file) throws Exception {
        ShellWriteUtil.writeCdSoft(file);
    }

    @Override
    public void dockerShell(final File file) throws Exception {
    }

    @Override
    public List<File> buildConfigFile() throws Exception {
        return ListUtils.emptyIfNull(null);
    }

    @Override
    public void buildConfigFile(final List<File> files) throws Exception {
        throw new RuntimeException();
    }

    @Override
    public void buildConfigFile(final List<File> files, final List<String> hostsp) throws Exception {
        throw new RuntimeException();
    }

    @Override
    public List<DockerBuildVO> dockerResFile() {
        return Collections.emptyList();
    }

    public DockerBuildVO queryTar(final String name) {
        DockerBuildVO vo = null;
        for (final DockerBuildVO dockerBuildVO : dockerBuildVOs) {
            if (dockerBuildVO.tar.contains(name)) {
                vo = dockerBuildVO;
                break;
            }
        }
        return vo;
    }

    public DockerGitBuildVO queryGitTar(final String name) {
        DockerGitBuildVO vo = null;
        for (final DockerGitBuildVO dockerBuildVO : dockerGitBuildVOs) {
            if (dockerBuildVO.gittar.contains(name)) {
                vo = dockerBuildVO;
                break;
            }
        }
        return vo;
    }

    @Override
    public void dockerRun(final List<String> lines, final String dockerVersion, final String dockerHost, final String param, final Map<String, String> hostMap, final List<String> ports, final String dns_dnsmasq_path) {
        dockerRun(lines, dockerVersion, dockerHost, param, hostMap, ports, dns_dnsmasq_path, memery);
    }

    @Override
    public void dockerRun(final List<String> lines, final String dockerVersion, final String dockerHost, final String param, final Map<String, String> hostMap, final List<String> ports, final String dns_dnsmasq_path, final String memery) {
    }
}
