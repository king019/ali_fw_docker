package com.k.docker.docker.util.file;

import com.k.docker.docker.util.props.PropsDockerUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class DockerWriteUtil {
    public static void writeCopy(final File file, final String tar) throws Exception {
        final String commend;
        commend = copyCommand(tar);
        ShellWriteUtil.writeLine(file);
        FileUtils.write(file, commend, StandardCharsets.UTF_8, true);
        ShellWriteUtil.writeLine(file);
    }

    public static void writeRun(final File file, final String line) throws Exception {
        final String commend;
        commend = "RUN " + line;
        ShellWriteUtil.writeLine(file);
        FileUtils.write(file, commend, StandardCharsets.UTF_8, true);
        ShellWriteUtil.writeLine(file);
    }

    private static String copyCommand(final String tar) {
        String commend = "COPY " + tar + " " + PropsDockerUtil.dockerDir + tar;
        return commend;
    }
}
