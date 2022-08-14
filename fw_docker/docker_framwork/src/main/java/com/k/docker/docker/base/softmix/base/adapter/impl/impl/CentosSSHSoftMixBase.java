package com.k.docker.docker.base.softmix.base.adapter.impl.impl;

import com.k.docker.docker.base.softmix.base.adapter.SoftMixBaseAdapter;
import com.k.docker.docker.util.path.FWPathUtil;
import org.apache.commons.io.FileUtils;
import org.junit.Before;

import java.io.File;

public class CentosSSHSoftMixBase extends SoftMixBaseAdapter {
    @Before
    public void bf() {
        from = "registry.cn-hangzhou.aliyuncs.com/king019/centosssh";
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
