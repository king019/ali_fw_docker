package com.k.docker.docker.util.docker;

import com.k.docker.docker.util.vo.DockerBuildVO;

public class SoftVersion {
    public static DockerBuildVO JdkSoft8 = new DockerBuildVO("jdk-8u152-linux-x64.tar.gz", "jdk1.8.0_152 ", "jdk");
    public static DockerBuildVO JdkSoft7 = new DockerBuildVO("jdk-7u76-linux-x64.tar.gz", "jdk1.7.0_76 ", "jdk");
    public static DockerBuildVO zookeeperSoft = new DockerBuildVO("zookeeper-3.4.9.tar.gz", "zookeeper-3.4.9 ", "zookeeper");
    public static DockerBuildVO zookeeperUISoft = new DockerBuildVO("zkui-2.0-SNAPSHOT-jar-with-dependencies.jar", "zkui-2.0-SNAPSHOT-jar-with-dependencies.jar ", "zkui.jar");
    public static DockerBuildVO tomcatSoft = new DockerBuildVO("apache-tomcat-7.0.70.tar.gz", "apache-tomcat-7.0.70", "tomcat");
    public static DockerBuildVO stormSoft = new DockerBuildVO("apache-storm-1.0.1.tar.gz", "apache-storm-1.0.1", "storm");
    public static DockerBuildVO hadoopSoft = new DockerBuildVO("hadoop-3.0.0-beta1.tar.gz", "hadoop-3.0.0-beta1", "hadoop");
    public static DockerBuildVO hbaseSoft = new DockerBuildVO("hbase-1.3.1-bin.tar.gz", "hbase-1.3.1", "hbase");
    public static DockerBuildVO hiveSoft = new DockerBuildVO("apache-hive-2.1.1-bin.tar.gz", "apache-hive-2.1.1-bin", "hive");
    // dubbo
    public static DockerBuildVO dubboAdminSoft = new DockerBuildVO("dubbo-admin-2.8.4.war", "dubbo-admin-2.8.4.war", "admin");
    public static DockerBuildVO dubboMonitorSoft = new DockerBuildVO("dubbo-monitor-simple-2.8.4-assembly.tar.gz", "dubbo-monitor-simple-2.8.4", "monitor");
    public static DockerBuildVO dubboConsumerSoft = new DockerBuildVO("dubbo-demo-consumer-2.8.4-assembly.tar.gz", "dubbo-demo-consumer-2.8.4", "consumer");
    public static DockerBuildVO dubboProviderSoft = new DockerBuildVO("dubbo-demo-provider-2.8.4.war", "dubbo-demo-provider-2.8.4.war", "provider");
    public static DockerBuildVO dubboDubbowebSoft = new DockerBuildVO("dubboweb-0.0.1-SNAPSHOT.war", "dubboweb-0.0.1-SNAPSHOT.war", "dubboweb");
    // rabbitmq
    public static DockerBuildVO rabbitmqRrlangSoft = new DockerBuildVO("erlang-19.3.0-1.el7.centos.x86_64.rpm", "erlang-19.3.0-1.el7.centos.x86_64.rpm", "rabbitmq-server");
    public static DockerBuildVO rabbitmqServerSoft = new DockerBuildVO("rabbitmq-server-3.6.8-1.el7.noarch.rpm", "rabbitmq-server-3.6.8-1.el7.noarch.rpm", "rabbitmq-server");
    // rockmq
    public static DockerBuildVO rockmqBrokerSoft = new DockerBuildVO("alibaba-rocketmq-broker.tar.gz", "alibaba-rocketmq", "rocketmq");
    public static DockerBuildVO rockmqConsoleSoft = new DockerBuildVO("rocketmq-console.war", "rocketmq-console.war", "ROOT");
    // redis
    public static DockerBuildVO redisSoft = new DockerBuildVO("redis-4.0.8.tar.gz", "redis-4.0.8", "redis");
    // jstorm
    public static DockerBuildVO jstormSoft = new DockerBuildVO("jstorm-2.1.1.zip", "jstorm-2.1.1", "jstorm");
    public static DockerBuildVO jstormUiSoft = new DockerBuildVO("jstorm-ui-2.1.1.war", "jstorm-ui-2.1.1.war", "ui");
    // elk
    public static DockerBuildVO elasticsearchSoft = new DockerBuildVO("elasticsearch-5.4.0.tar.gz", "elasticsearch-5.4.0", "elasticsearch");
    public static DockerBuildVO logstashSoft = new DockerBuildVO("logstash-5.4.0.tar.gz", "logstash-5.4.0", "logstash");
    public static DockerBuildVO kibanaSoft = new DockerBuildVO("kibana-5.4.0-linux-x86_64.tar.gz", "kibana-5.4.0-linux-x86_64", "kibana");
    // kafka
    public static DockerBuildVO kafkaSoft = new DockerBuildVO("kafka_2.12-0.10.2.1.tgz", "kafka_2.12-0.10.2.1", "kafka");
    public static DockerBuildVO springShowSoft = new DockerBuildVO("spring_boot_show-0.0.1-SNAPSHOT.jar", "spring_boot_show-0.0.1-SNAPSHOT.jar", "show");
}
