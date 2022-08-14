package com.k.docker.docker.util.file;

import com.google.common.collect.Lists;
import com.k.docker.docker.base.dnsmasq.dnsdelete.DnsDeleteBase;
import com.k.docker.docker.base.docker.image.DockerImageRemoveBase;
import com.k.docker.docker.base.soft.base.DockerBase;
import com.k.docker.docker.base.softmix.base.adapter.SoftMixBaseAdapter;
import com.k.docker.docker.util.path.FWPathUtil;
import com.k.docker.docker.util.props.PropsDockerUtil;
import com.k.docker.docker.util.vo.DockerBuildVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FileWriteUtil {
    public static File getFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        return file;
    }

    public static void writeStart(File file) throws Exception {
        String commend;
        commend = "#!/bin/bash";
        FileUtils.write(file, commend, StandardCharsets.UTF_8, true);
        ShellWriteUtil.writeLine(file);
        commend = "set -x";
        FileUtils.write(file, commend, StandardCharsets.UTF_8, true);
        ShellWriteUtil.writeLine(file);
    }

    public static void writeStart(File file, String commend) throws Exception {
        FileUtils.write(file, commend, StandardCharsets.UTF_8, true);
        ShellWriteUtil.writeLine(file);
    }

    public static void writeNoneLineFeed(File file, String commend) throws Exception {
        FileUtils.write(file, commend, StandardCharsets.UTF_8, true);
    }

    public static void write(File file, String commend) throws Exception {
        ShellWriteUtil.writeLine(file);
        FileUtils.write(file, commend, StandardCharsets.UTF_8, true);
        ShellWriteUtil.writeLine(file);
    }

    public static void write(File file, List<String> commends) throws Exception {
        ShellWriteUtil.writeLine(file);
        FileUtils.writeLines(file, commends, PropsDockerUtil.line_feed, true);
    }

    public static void writeShellChmod777(File file, List<String> commends) throws Exception {
        commends.add("chmod -R 777 " + PropsDockerUtil.dockerAbsDataDir);
        ShellWriteUtil.writeLine(file);
        FileUtils.writeLines(file, commends, PropsDockerUtil.line_feed, true);
    }

    public static void writeRun(File file, List<String> commends) throws Exception {
        commends.add("chmod -R 777 " + PropsDockerUtil.dockerAbsDataDir);
        ShellWriteUtil.writeLine(file);
        FileUtils.writeLines(file, commends, PropsDockerUtil.line_feed, true);
    }

    public static void writeRunSleep(File file, List<String> commends) throws Exception {
        commends.add("sleep 20");
        commends.add("chmod -R 777 " + PropsDockerUtil.dockerAbsDataDir);
        ShellWriteUtil.writeLine(file);
        FileUtils.writeLines(file, commends, PropsDockerUtil.line_feed, true);
    }

    public static void writeLines(File file, List<String> commends) throws Exception {
        FileUtils.writeLines(file, commends, PropsDockerUtil.line_feed, true);
    }

    public static void writeEnd(File file, String commend) throws Exception {
        FileUtils.write(file, commend, StandardCharsets.UTF_8, true);
        ShellWriteUtil.writeLine(file);
    }

    @SafeVarargs
    public static void writeCdClassandCopySoft(List<String> lines, Class<? extends SoftMixBaseAdapter>... clazzs) throws Exception {
        lines.add("chmod -R 777 " + PropsDockerUtil.dockerAbsDockerDir);
        for (Class<? extends SoftMixBaseAdapter> clazz : clazzs) {
            writeCdClassandCopySoftOne(lines, clazz);
        }
    }

    public static void writeCdClassandCopySoftOne(List<String> lines,
                                                  Class<? extends SoftMixBaseAdapter> clazz) throws Exception {
        lines.add("cd " + FWPathUtil.getClassPath(clazz));
        SoftMixBaseAdapter obj;
        obj = clazz.newInstance();
        List<DockerBase> docks = obj.queryDocks();
        if (CollectionUtils.isNotEmpty(docks)) {
            for (DockerBase dock : docks) {
                List<DockerBuildVO> buildVOs = dock.queryAllDockerTar();
                for (DockerBuildVO buildVO : buildVOs) {
                    lines.add(FileWriteUtil.writeCopySoft(buildVO.tar));
                }
            }
        }
    }

    public static String writeCopySoft(String tar) {
        String commend;
        commend = "cp " + PropsDockerUtil.dockerAbsSoftDir + tar + "  " + tar;
        return commend;
    }

    public static String writeCopySoft(Class clazz, String tar) {
        String commend;
        commend = "cp " + FWPathUtil.getDockerDirPath(clazz) + tar + "  " + tar;
        return commend;
    }

    public static void writeDnsAndImage(List<String> lines, Class imageCreate) {
        lines.add("cd " + FWPathUtil.getClassPath(imageCreate));
        lines.add("./docker_image_create.sh");
    }

    public static void deleteDnsAndImage(File shFile) throws Exception {
        List<String> lines = Lists.newArrayList();
        lines.add("cd " + FWPathUtil.getClassPath(DnsDeleteBase.class));
        lines.add("./dns_del.sh");
        lines.add("cd " + FWPathUtil.getClassPath(DockerImageRemoveBase.class));
        lines.add("./docker_image_remove.sh");
        write(shFile, lines);
    }
}
