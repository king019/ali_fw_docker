package com.k.docker.docker.amd.softmix.elastic.single.elasticsearch.run;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.elastic.elasticsearch.single.install.ElasticSearchSingleInstall;
import com.k.docker.docker.amd.soft.elastic.kibana.single.install.KibanaSingleInstall;
import com.k.docker.docker.amd.soft.elastic.logstash.single.install.LogstashSingleInstall;
import com.k.docker.docker.amd.softmix.elastic.single.elasticsearch.build.ElasticSearchSingleBuild;
import com.k.docker.docker.amd.softmix.elastic.single.elasticsearch.config.host.ElasticSearchSingleHost;
import com.k.docker.docker.amd.softmix.elastic.single.elasticsearch.image.create.ElasticSearchSingleImageCreate;
import com.k.docker.docker.amd.softmix.elastic.single.kibana.build.KibanaSingleBuild;
import com.k.docker.docker.amd.softmix.elastic.single.logstash.build.LogstashSingleBuild;
import com.k.docker.docker.base.docker.run.DockerRunFileBase;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.docker.run.base.impl.DockerRunBaseImpl;
import com.k.docker.docker.util.docker.DockerVersion;
import com.k.docker.docker.util.file.FileWriteUtil;
import org.junit.Test;

import java.util.List;

public class ElasticSearchSingleRun extends DockerRunFileBase {

    @Test
    public void test() throws Exception {
        String elk_e_Version = DockerVersion.elasticsearchSingleVersion;
        String elk_l_Version = DockerVersion.logstashSingleVersion;
        String elk_k_Version = DockerVersion.kibanaSingleVersion;
        DockerRunBase dockerRun = new DockerRunBaseImpl();
        ElasticSearchSingleHost hosts = new ElasticSearchSingleHost();
        List<String> lines = Lists.newArrayList();
        {
            FileWriteUtil.writeCdClassandCopySoft(lines, ElasticSearchSingleBuild.class, KibanaSingleBuild.class, LogstashSingleBuild.class);
        }
        {
            FileWriteUtil.writeDnsAndImage(lines, ElasticSearchSingleImageCreate.class);
        }
        {
            ElasticSearchSingleInstall.dockerRun(ElasticSearchSingleBuild.class, lines, elk_e_Version, dockerRun, hosts.elk_e());
            LogstashSingleInstall.dockerRun(LogstashSingleBuild.class, lines, elk_l_Version, dockerRun, hosts.elk_l());
            KibanaSingleInstall.dockerRun(KibanaSingleBuild.class, lines, elk_k_Version, dockerRun, hosts.elk_k());
        }
        FileWriteUtil.writeShellChmod777(shFile, lines);
    }
}
