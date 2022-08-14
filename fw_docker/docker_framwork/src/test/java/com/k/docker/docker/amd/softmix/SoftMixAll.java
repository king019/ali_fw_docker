package com.k.docker.docker.amd.softmix;

import com.k.docker.docker.amd.softmix.alibaba.AlibabaAll;
import com.k.docker.docker.amd.softmix.apache.ApacheAll;
import com.k.docker.docker.amd.softmix.cache.CacheAll;
import com.k.docker.docker.amd.softmix.common.CommonAll;
import com.k.docker.docker.amd.softmix.docker.DockerAll;
import com.k.docker.docker.amd.softmix.elastic.single.ELKSingleAll;
import com.k.docker.docker.amd.softmix.proxy.ProxyAll;
import com.k.docker.docker.amd.softmix.rabbitmq.RabbitmqAll;
import com.k.docker.docker.amd.softmix.server.ServerAll;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ProxyAll.class, AlibabaAll.class, CacheAll.class, ApacheAll.class,
        DockerAll.class, CacheAll.class, CommonAll.class, RabbitmqAll.class,
        ELKSingleAll.class, ServerAll.class})
public class SoftMixAll {
    /// ---------------/opt/soft/桌面
}
