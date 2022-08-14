package com.k.docker.docker.amd.softmix.elastic.single.logstash.build;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.elastic.logstash.single.install.LogstashSingleInstall;
import com.k.docker.docker.amd.soft.jdk.JdkInstall;
import com.k.docker.docker.amd.softmix.elastic.single.elasticsearch.config.host.ElasticSearchSingleHost;
import com.k.docker.docker.base.soft.base.DockerBase;
import com.k.docker.docker.base.softmix.adapter.SoftMixAdapter;

import java.util.List;

public class LogstashSingleBuild extends SoftMixAdapter {
    @Override
    public List<DockerBase> queryDocks() {
        ElasticSearchSingleHost hosts = new ElasticSearchSingleHost();
        dockerBases = Lists.newArrayList();
        dockerBases.add(new JdkInstall());
        dockerBases.add(new LogstashSingleInstall(hosts.elk_l(), hosts.elk_e()));
        return dockerBases;
    }
}
