package com.k.docker.dockerhub.soft.amd.elk;

import com.k.docker.dockerhub.soft.amd.elk.elasticsearch.Elasticsearch;
import com.k.docker.dockerhub.soft.amd.elk.filebeat.FilebeatInstall;
import com.k.docker.dockerhub.soft.amd.elk.kibana.KibanaInstall;
import com.k.docker.dockerhub.soft.amd.elk.logstash.LogstashInstall;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({Elasticsearch.class, FilebeatInstall.class, KibanaInstall.class, LogstashInstall.class})
public class ElkHubAll {

}
