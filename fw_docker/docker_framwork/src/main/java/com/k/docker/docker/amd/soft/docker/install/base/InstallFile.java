package com.k.docker.docker.amd.soft.docker.install.base;

import org.junit.After;
import org.junit.Before;

import java.io.File;

public abstract class InstallFile {
    public String shPath;
    public File shFile;

    @Before
    public abstract void bf() throws Exception;

    @After
    public void af() throws Exception {
    }
}
