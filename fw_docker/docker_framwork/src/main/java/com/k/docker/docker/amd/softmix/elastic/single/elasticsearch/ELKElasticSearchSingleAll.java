package com.k.docker.docker.amd.softmix.elastic.single.elasticsearch;

import com.k.docker.docker.amd.softmix.elastic.single.elasticsearch.build.ElasticSearchSingleBuild;
import com.k.docker.docker.amd.softmix.elastic.single.elasticsearch.image.create.ElasticSearchSingleImageCreate;
import com.k.docker.docker.amd.softmix.elastic.single.elasticsearch.run.ElasticSearchSingleRun;
import com.k.docker.docker.amd.softmix.elastic.single.elasticsearch.scan.ElasticSearchSingleWlanScan;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ElasticSearchSingleBuild.class, ElasticSearchSingleImageCreate.class, ElasticSearchSingleRun.class, ElasticSearchSingleWlanScan.class})
public class ELKElasticSearchSingleAll {

}