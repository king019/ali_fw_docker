package com.k.docker.docker.util.props;

public class PropsDockerUtil {
    public static String line_feed = "\n";
    public static String dockerDir = "~/soft/";
    public static String dockerAbsDockerDir = dockerDir + "docker/";
    public static String dockerAbsSoftDir = dockerAbsDockerDir + "soft/";
    public static String dockerAbsDataDir = dockerAbsDockerDir + "data/";
    public static String dockerAbsComDir = dockerAbsDockerDir + "com/";

    public static String desDir(final String dir) {
        return dockerDir + dir;
    }

    public static String desDirBin(final String dir) {
        return dockerDir + dir + "/bin";
    }
}
