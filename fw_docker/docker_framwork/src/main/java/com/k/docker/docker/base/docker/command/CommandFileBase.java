package com.k.docker.docker.base.docker.command;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.soft.docker.install.base.InstallFile;
import com.k.docker.docker.util.file.FileWriteUtil;
import com.k.docker.docker.util.path.FWPathUtil;
import org.junit.After;
import org.junit.Test;

import java.util.List;

public abstract class CommandFileBase extends InstallFile {

    protected List<String> lines = Lists.newArrayList();

    @Override
    public void bf() {
        shPath = FWPathUtil.getTargetPath(getClass()) + "/docker_command.sh";
        shFile = FileWriteUtil.getFile(shPath);
    }

    @Test
    public abstract void test();

    @After
    public void af() throws Exception {
        FileWriteUtil.writeStart(shFile);
        FileWriteUtil.write(shFile, lines);
    }
}
