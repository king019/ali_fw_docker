package com.k.docker.docker.base.dnsmasq.dnsdelete;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.docker.install.base.InstallFile;
import com.k.docker.docker.util.file.FileWriteUtil;
import com.k.docker.docker.util.path.FWPathUtil;
import org.junit.Test;

import java.util.List;

public class DnsDeleteBase extends InstallFile {

    public static String shellName = "dns_del.sh";

    public static List<String> dnsmasqLines() {
        List<String> lines = Lists.newArrayList();
        // lines.add("cd /etc/dnsmasq.d/");
        // lines.add("ls /etc/dnsmasq.d/ |grep -vE '(sonar|network|README)' | xargs rm");
        lines.add("if [ -z \"$1\" ]");
        lines.add("then");
        lines.add("rm -fr /etc/dnsmasq.d/0host_*");
        lines.add("else");
        lines.add("rm -fr /etc/dnsmasq.d/0host_$1");
        lines.add("fi");
        String os = System.getProperty("os.name");
        if (os.toLowerCase().contains("mac")) {
            lines.add("sudo brew services restart dnsmasq");
        } else {
            lines.add("service dnsmasq restart");
        }
        return lines;
    }

    @Override
    public void bf() {
        shPath = FWPathUtil.getTargetPath(getClass()) + "/" + shellName;
        shFile = FileWriteUtil.getFile(shPath);
    }

    @Test
    public void test() throws Exception {
        FileWriteUtil.writeStart(shFile);
        List<String> lines = dnsmasqLines();
        FileWriteUtil.write(shFile, lines);
    }
}
