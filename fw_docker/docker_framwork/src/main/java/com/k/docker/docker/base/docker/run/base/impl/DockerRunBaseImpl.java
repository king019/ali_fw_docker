package com.k.docker.docker.base.docker.run.base.impl;

import com.k.docker.docker.base.dnsmasq.dnsconfig.DnsConfig;
import com.k.docker.docker.base.dnsmasq.dnsdelete.DnsDeleteBase;
import com.k.docker.docker.base.docker.image.DockerImageRemoveBase;
import com.k.docker.docker.base.docker.image.base.DockerImageBase;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.util.path.FWPathUtil;
import com.k.docker.docker.util.props.PropsContainerUtil;
import com.k.docker.docker.util.props.PropsDockerUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class DockerRunBaseImpl extends DockerRunBase {
    private final String dns_dnsmasq_path = FWPathUtil.getClassPath(DnsConfig.class);

    @Override
    public void dockerRun(List<String> lines, String dockerVersion, String dockerHost) {
        String param = "param";
        dockerRun(lines, dockerVersion, dockerHost, param);
    }

    @Override
    public void dockerRun(List<String> lines, String dockerVersion, String dockerHost, String param) {
        String memery = "300m";
        dockerRun(lines, dockerVersion, dockerHost, param, memery);
    }

    @Override
    public void dockerRunNoLimitMemery(List<String> lines, String dockerVersion, String dockerHost, String param) {
        dockerRun(lines, dockerVersion, dockerHost, param, null);
    }

    @Override
    public void dockerRun(List<String> lines, String dockerVersion, String dockerHost, String param, String memery) {
        dockerRunPrivate(lines, dockerVersion, dockerHost, param, memery, false);
    }

    @Override
    public void dockerRunVolume(List<String> lines, String dockerVersion, String dockerHost, String param, String memery) {
        dockerRunPrivate(lines, dockerVersion, dockerHost, param, memery, true);
    }

    private void dockerRunPrivate(List<String> lines, String dockerVersion, String dockerHost, String param, String memery, boolean volume) {
        if (StringUtils.isNotBlank(memery)) {
            memery = " -m " + memery;
        } else {
            memery = "";
        }
        if (volume) {
            lines.add("volume=" + PropsDockerUtil.dockerAbsDataDir + "volume");
        } else {
            lines.add("volume=" + PropsDockerUtil.dockerAbsDataDir + dockerHost);
        }
        lines.add("target=" + PropsContainerUtil.containSoftDataDir);
        lines.add("docker run --privileged " + memery + "  -d -e TZ='Asia/Shanghai' -v $volume:$target     -h=" + dockerHost + "  --name=" + dockerHost + "   " + dockerVersion + " /docker.sh " + param);
        lines.add(dns_dnsmasq_path + "/dns_dnsmasq.sh   " + dockerHost);
    }

    @Override
    public void dockerRunVolumeNoLimitMemery(List<String> lines, String dockerVersion, String dockerHost, String param) {
        dockerRunVolume(lines, dockerVersion, dockerHost, param, null);
    }

    @Override
    public void dockerLines(List<String> lines, Class<?> ImageCreateClazz) {
        dockerLines(lines, DnsConfig.class, DnsDeleteBase.class, DockerImageRemoveBase.class, ImageCreateClazz);
    }

    private void dockerLines(List<String> lines, Class<?> dnsClazz, Class<?> dnsDelClazz, Class<?> imageRemoveClazz, Class<?> ImageCreateClazz) {
        {
            lines.add("cd " + FWPathUtil.getClassPath(dnsDelClazz));
            lines.add("./" + DnsDeleteBase.shellName);
            lines.add("cd " + FWPathUtil.getClassPath(imageRemoveClazz));
            lines.add("./" + DockerImageRemoveBase.shellName);
            lines.add("cd " + FWPathUtil.getClassPath(ImageCreateClazz));
            lines.add("./" + DockerImageBase.shellName);
        }
    }
}
