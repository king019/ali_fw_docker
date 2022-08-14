package com.k.docker.docker.base.softmix.base.adapter.impl;

import com.k.docker.docker.base.softmix.base.SoftMixBase;
import com.k.docker.docker.util.path.FWPathUtil;
import org.apache.commons.io.FileUtils;
import org.junit.Before;

import java.io.File;

public class UbuntuSoftMixBase extends SoftMixBase {
    @Before
    public void bf() {
        from = "ubuntu:14.04";
        baseDir = FWPathUtil.getTargetPath(getClass());
        File baseDirFile = new File(baseDir);
        try {
            FileUtils.deleteDirectory(baseDirFile);
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
