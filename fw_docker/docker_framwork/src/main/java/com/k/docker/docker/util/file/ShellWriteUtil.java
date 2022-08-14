package com.k.docker.docker.util.file;

import com.google.common.collect.Lists;
import com.k.docker.docker.util.props.PropsDockerUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author k
 */
public class ShellWriteUtil {
    public static void writeShellTop(final File file) throws Exception {
        String commend = "#!/bin/bash";
        writeShell(file, commend);
        commend = "set -x";
        writeShell(file, commend);
    }

    public static void writeTar(final File file, final String tar) throws Exception {
        final String commend = "tar -xzf " + tar;
        writeShell(file, commend);
    }

    public static void writeUnzip(final File file, final String tar) throws Exception {
        final String commend = "unzip " + tar;
        writeShell(file, commend);
    }

    public static void writeCdSoft(final File file) throws Exception {
        final String commend = "cd " + PropsDockerUtil.dockerDir;
        writeShell(file, commend);
    }

    public static void writeCdTar(final File file, final String tar) throws Exception {
        final String commend = "cd " + tar;
        writeShell(file, commend);
    }

    public static void writeCatAppend(final File file, final String src, final String des) throws Exception {
        final String commend = "cat  " + src + " >>  " + des;
        writeShell(file, commend);
    }

    public static void writeCatCover(final File file, final String src, final String des) throws Exception {
        final String commend = "cat  " + src + " >  " + des;
        writeShell(file, commend);
    }

    public static void writeMv(final File file, final String mvSrc, final String mvDes) throws Exception {
        final String commend =
                "mv " + PropsDockerUtil.dockerDir + mvSrc + " " + PropsDockerUtil.dockerDir + mvDes;
        writeShell(file, commend);
    }

    public static void writeCp(final File file, final String mvSrc, final String mvDes) throws Exception {
        final String commend =
                "cp " + PropsDockerUtil.dockerDir + mvSrc + " " + PropsDockerUtil.dockerDir + mvDes;
        writeShell(file, commend);
    }

    public static void writeCpAbs(final File file, final String mvSrc, final String mvDes) throws Exception {
        final String commend = "cp " + PropsDockerUtil.dockerDir + mvSrc + " " + mvDes;
        writeShell(file, commend);
    }

    public static void writeRm(final File file, final String mvSrc) throws Exception {
        final String commend = "rm -fr " + PropsDockerUtil.dockerDir + mvSrc;
        writeShell(file, commend);
    }

    public static void writeRmAbs(final File file, final String mvSrc) throws Exception {
        final String commend = "rm -fr " + mvSrc;
        writeShell(file, commend);
    }

    public static void writeSleep(final File file, final String sleep) throws Exception {
        final String commend = "sleep " + sleep;
        writeShell(file, commend);
    }

    public static void writeSleep(final File file) throws Exception {
        final String commend = "sleep 10";
        writeShell(file, commend);
    }

    public static void writeSourceProfile(final File file) throws Exception {
        final String commend = "source /etc/profile";
        writeShell(file, commend);
    }

    public static void writeTail(final File file) throws Exception {
        final String commend = "tail -f /docker.sh";
        writeShell(file, commend);
    }

    public static void writeMkdir(final File file, final String path) throws Exception {
        final String commend = "mkdir -p " + PropsDockerUtil.dockerDir + path;
        writeShell(file, commend);
    }

    public static void writeMkdirAbs(final File file, final String path) throws Exception {
        final String commend = "mkdir -p " + path;
        writeShell(file, commend);
    }

    public static void writeSysctl(final File file) throws Exception {
        final String commend = "sysctl -p";
        writeShell(file, commend);
    }

    public static void writeList(final File file, final List<String> lines) throws Exception {
        for (final String commend : lines) {
            writeShell(file, commend);
        }
    }

    public static void writeChmod(final File file) throws Exception {
        final String commend = "chmod -R 777 " + PropsDockerUtil.dockerDir;
        writeShell(file, commend);
    }

    public static void writeIfStartStr(final File file, final String srcStr, final String compareStr) throws Exception {
        final List<String> lines = Lists.newArrayList();
        lines.add("if [ \"" + srcStr + "\" == \"" + compareStr + "\"']");
        lines.add("then");
        FileWriteUtil.write(file, lines);
    }

    public static void writeIfStartParam(final File file, final String srcStr, final String compareParam) throws Exception {
        final List<String> lines = Lists.newArrayList();
        lines.add("if [ \"" + srcStr + "\"  == \"" + compareParam + "\" ]");
        lines.add("then");
        FileWriteUtil.write(file, lines);
    }

    public static void writeIfContainParam(final File file, final String srcStr, final String compareParam) throws Exception {
        final List<String> lines = Lists.newArrayList();
        lines.add("if [[    \"" + compareParam + "\" =~  \"" + srcStr + "\" ]]");
        lines.add("then");
        FileWriteUtil.write(file, lines);
    }

    public static void writeIfNoEqualParam(final List<String> lines, final String srcStr, final String compareParam) {
        lines.add("if [[    \"" + compareParam + "\" !=  \"" + srcStr + "\" ]]");
        lines.add("then");
    }

    public static void writeIfEnd(final List<String> lines) {
        lines.add("fi");
    }

    public static void writeRpm(final File file, final String rpm) throws Exception {
        final List<String> lines = Lists.newArrayList();
        lines.add("rpm -ivh " + rpm);
        FileWriteUtil.write(file, lines);
    }

    public static void writeIfEnd(final File file) throws Exception {
        final List<String> lines = Lists.newArrayList();
        lines.add("fi");
        FileWriteUtil.write(file, lines);
    }

    public static void writeListTail(final File file, final List<String> lines) throws Exception {
        for (final String commend : lines) {
            writeShell(file, commend);
        }
        writeTail(file);
    }

    public static void writeShell(final File file, final String command) throws Exception {
        FileUtils.write(file, command, StandardCharsets.UTF_8, true);
        writeLine(file);
    }

    public static void writeAddUser(final File file, final String user) throws Exception {
        writeLine(file);
        ShellWriteUtil.writeShell(file, "groupadd " + user);
        writeLine(file);
        ShellWriteUtil.writeShell(file, "useradd " + user + " -g " + user + " -p " + user + "");
        writeLine(file);
    }

    public static void writeSuUserFile(final File file, final String user, final String filePath) throws Exception {
        writeLine(file);
        ShellWriteUtil.writeShell(file, "su  " + user + "  -s  /bin/bash " + filePath);
        writeLine(file);
    }

    public static void writeLine(final File file) throws Exception {
        FileUtils.write(file, PropsDockerUtil.line_feed, StandardCharsets.UTF_8, true);
    }
}
