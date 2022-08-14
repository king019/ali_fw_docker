package com.k.docker.dockerhub.base.config;

import com.k.docker.docker.util.props.PropsContainerUtil;

import java.util.Map;

import static com.k.docker.docker.util.props.PropsDockerUtil.dockerAbsDataDir;

public class InstallConfig {
    public static void putJdk8(Map<String, String> vCmdMap) {
        putJdk8(vCmdMap, true);
    }

    public static void putJdk8(Map<String, String> vCmdMap, boolean rep11) {
        vCmdMap.put(dockerAbsDataDir + "maven/m2/repository", "/root/.m2/repository");

    }

    public static void putJdk11(Map<String, String> vCmdMap) {
        vCmdMap.put(dockerAbsDataDir + "maven/m2", PropsContainerUtil.rootM2Dir);
    }
}
