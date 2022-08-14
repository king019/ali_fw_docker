package com.k.docker.docker.base.docker.image;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.docker.install.base.InstallFile;
import com.k.docker.docker.util.file.FileWriteUtil;
import com.k.docker.docker.util.path.FWPathUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DockerImageRemoveBase extends InstallFile {

    public static String shellName = "docker_image_remove.sh";

    public static List<String> dockerRmCommand() {
        List<String> lines = Lists.newArrayList();
        // lines.add("docker ps -a|grep -v sonarqube |awk '{print \"docker rm -f \" $1}'|sh");
        // lines.add("docker images |grep -v centos|grep -v ubuntu|grep -v sonarqube|awk '{print \"docker rmi -f \" $3}'|sh");
        lines.add("docker ps -a |grep -v nexus|awk '{print \"docker rm -f  \" $1}'|sh");
        lines.add("docker images |grep -vE '" + buildImage() + "'|awk '{print \"docker rmi -f  \" $3}'|sh");
        lines.add("docker images |awk '{print \"docker rmi -f  \" $3}'|sh");
        // lines.add("docker images |grep -v centos|grep -v ubuntu|grep -v mysql|awk '{print \"docker rmi -f  \" $3}'|sh");
        return lines;
    }

    private static String buildImage() {
        ArrayList<String> images = Lists.newArrayList();
        images.add("centos");
        images.add("ubuntu");
        images.add("mysql");
        images.add("registry.cn-hangzhou.aliyuncs.com/king019/centosssh");
        images.add("registry.cn-hangzhou.aliyuncs.com/king019/littleproxy");
        images.add("registry.cn-hangzhou.aliyuncs.com/king019/littleproxyspring");
        images.add("registry.cn-hangzhou.aliyuncs.com/king019/centos");
        images.add("weaveworks/scope");
        //images.add("uifd/ui-for-docker");
        return StringUtils.join(images, "|");
    }

    @Override
    public void bf() {
        shPath = FWPathUtil.getTargetPath(getClass()) + "/" + shellName;
        shFile = FileWriteUtil.getFile(shPath);
    }

    @Test
    public void test() throws Exception {
        List<String> lines = dockerRmCommand();
        FileWriteUtil.writeStart(shFile);
        FileWriteUtil.write(shFile, lines);
    }
}
