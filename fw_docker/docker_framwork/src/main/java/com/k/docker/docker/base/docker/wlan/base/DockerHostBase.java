package com.k.docker.docker.base.docker.wlan.base;

import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public abstract class DockerHostBase {
    public abstract List<String> docker_all();

    public Map<String, String> docker_host_map() {
        List<String> hosts = docker_all();
        int num = 100;
        Map<String, String> hostMap = Maps.newHashMap();
        for (String host : hosts) {
            hostMap.put(host, "192.168.31." + num);
            num++;
        }
        return hostMap;
    }

    public void trans(List<String> hosts) {
        String nowHost;
        for (int i = 0; i < hosts.size(); i++) {
            nowHost = hosts.get(i);
            hosts.set(i, nowHost.replace(".", ""));
        }
    }
}
