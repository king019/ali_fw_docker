package com.k.docker.docker.amd.softmix.elastic.single.elasticsearch.image.create;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.elastic.elasticsearch.single.image.ElasticSearchSingleDockerImage;
import com.k.docker.docker.amd.soft.elastic.kibana.single.image.KibanaSingleDockerImage;
import com.k.docker.docker.amd.soft.elastic.logstash.single.image.LogstashSingleDockerImage;
import com.k.docker.docker.amd.softmix.elastic.single.elasticsearch.build.ElasticSearchSingleBuild;
import com.k.docker.docker.amd.softmix.elastic.single.kibana.build.KibanaSingleBuild;
import com.k.docker.docker.amd.softmix.elastic.single.logstash.build.LogstashSingleBuild;
import com.k.docker.docker.base.docker.image.DockerImageBuildBase;
import com.k.docker.docker.base.docker.image.base.DockerImageBase;
import org.junit.Test;

import java.util.List;

public class ElasticSearchSingleImageCreate extends DockerImageBase {

    @Test
    public void test() throws Exception {
        List<DockerImageBuildBase> list = Lists.newArrayList();
        list.add(new ElasticSearchSingleDockerImage(ElasticSearchSingleBuild.class));
        list.add(new KibanaSingleDockerImage(KibanaSingleBuild.class));
        list.add(new LogstashSingleDockerImage(LogstashSingleBuild.class));
        buildList(list);
    }
}
