package com.k.docker.docker.amd.softmix.alibaba.dubbo.dubbo.config.host;

import com.google.common.collect.Lists;
import com.k.docker.docker.base.docker.wlan.base.impl.DubboHostBase;

import java.util.List;

public class DubboHost extends DubboHostBase {
    @Override
    public List<String> docker_all() {
        List<String> hosts = Lists.newArrayList();
        hosts.addAll(dubbo_zookeeper());
        hosts.addAll(dubbo_zkui());
        hosts.addAll(dubbo_admin());
        hosts.addAll(dubbo_consumer());
        hosts.addAll(dubbo_monitorr());
        hosts.addAll(dubbo_provider());
        hosts.addAll(dubbo_dubboweb());
        trans(hosts);
        return hosts;
    }

    @Override
    public List<String> dubbo_zookeeper() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("zk.1");
        hosts.add("zk.2");
        hosts.add("zk.3");
        trans(hosts);
        return hosts;
    }

    public List<String> dubbo_zkui() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("zkui.1");
        trans(hosts);
        return hosts;
    }

    @Override
    public List<String> dubbo_admin() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("du.du.a.1");
        //hosts.add("du.du.a.2");
        //hosts.add("du.du.a.3");
        trans(hosts);
        return hosts;
    }

    @Override
    public List<String> dubbo_consumer() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("du.du.c.1");
        // hosts.add("du.du.c.2");
        // hosts.add("du.du.c.3");
        trans(hosts);
        return hosts;
    }

    @Override
    public List<String> dubbo_monitorr() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("du.du.m.1");
        // hosts.add("du.du.m.2");
        // hosts.add("du.du.m.3");
        trans(hosts);
        return hosts;
    }

    @Override
    public List<String> dubbo_provider() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("du.du.p.1");
        // hosts.add("du.du.p.2");
        // hosts.add("du.du.p.3");
        trans(hosts);
        return hosts;
    }

    @Override
    public List<String> dubbo_dubboweb() {
        List<String> hosts = Lists.newArrayList();
        hosts.add("du.du.w.1");
        // hosts.add("du.du.w.2");
        // hosts.add("du.du.w.3");
        trans(hosts);
        return hosts;
    }
}
