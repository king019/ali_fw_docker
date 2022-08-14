package com.k.docker.docker.amd.soft.dnsmasq.install.ubuntu;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.docker.install.base.InstallFile;
import com.k.docker.docker.util.file.FileWriteUtil;
import com.k.docker.docker.util.path.FWPathUtil;
import org.junit.Test;

import java.util.List;

public class DnsmasqInstall extends InstallFile {

    @Override
    public void bf() {
        shPath = FWPathUtil.getTargetPath(getClass()) + "/dnsmasq.sh";
        shFile = FileWriteUtil.getFile(shPath);
    }

    @Test
    public void test() throws Exception {
        FileWriteUtil.writeStart(shFile);
        List<String> lines = Lists.newArrayList();
        String install = "apt-get install dnsmasq";
        lines.add(install);
        FileWriteUtil.write(shFile, lines);
    }
}
