package com.k.docker.docker.base.docker.run.base;

import java.util.List;

public abstract class DockerRunBase {
    public abstract void dockerLines(List<String> lines, Class<?> ImageCreateClazz);

    public abstract void dockerRun(List<String> lines, String dockerVersion, String dockerHost);

    public abstract void dockerRun(List<String> lines, String dockerVersion, String dockerHost, String param);

    public abstract void dockerRun(List<String> lines, String dockerVersion, String dockerHost, String param, String memery);

    public abstract void dockerRunVolume(List<String> lines, String dockerVersion, String dockerHost, String param, String memery);

    public abstract void dockerRunNoLimitMemery(List<String> lines, String dockerVersion, String dockerHost, String param);

    public abstract void dockerRunVolumeNoLimitMemery(List<String> lines, String dockerVersion, String dockerHost, String param);
}
