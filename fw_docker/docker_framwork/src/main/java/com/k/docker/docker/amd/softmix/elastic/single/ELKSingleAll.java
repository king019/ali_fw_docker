package com.k.docker.docker.amd.softmix.elastic.single;

import com.k.docker.docker.amd.softmix.elastic.single.elasticsearch.ELKElasticSearchSingleAll;
import com.k.docker.docker.amd.softmix.elastic.single.kibana.ELKKibanaSingleAll;
import com.k.docker.docker.amd.softmix.elastic.single.logstash.ELKLogstashSingleAll;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ELKElasticSearchSingleAll.class, ELKKibanaSingleAll.class, ELKLogstashSingleAll.class})
public class ELKSingleAll {

}