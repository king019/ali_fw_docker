package com.k.docker.docker.base.dnsmasq.dnsconfig;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.docker.install.base.InstallFile;
import com.k.docker.docker.util.file.FileWriteUtil;
import com.k.docker.docker.util.path.FWPathUtil;
import com.k.docker.docker.util.props.PropsDockerUtil;
import org.junit.Test;

import java.util.List;

public class DnsConfig extends InstallFile {

    public static String shellName = "dns_dnsmasq.sh";
    public static String shellPath = PropsDockerUtil.dockerAbsDockerDir + FWPathUtil.getTargetPath(DnsConfig.class).substring("target".length() + 1) + "/" + shellName;

    @Override
    public void bf() {
        shPath = FWPathUtil.getTargetPath(getClass()) + "/" + shellName;
        shFile = FileWriteUtil.getFile(shPath);
    }

    @Test
    public void test() throws Exception {
        FileWriteUtil.writeStart(shFile);
        List<String> lines = Lists.newArrayList();
        lines.add("container=$1");
        lines.add("new_ip=$(docker inspect $container | grep IPAddress|tail -1 | cut -f4 -d'\"')");
        lines.add("echo \"host-record=$container,$new_ip\" >     /etc/dnsmasq.d/0host_$container");
        // lines.add("service dnsmasq restart");
        String os = System.getProperty("os.name");
        if (os.toLowerCase().contains("mac")) {
            lines.add("sudo brew services restart dnsmasq");
        } else {
            lines.add("service dnsmasq restart");
        }
        FileWriteUtil.write(shFile, lines);
    }
}
