package com.k.docker.docker.util.path;

import com.google.common.collect.Lists;
import com.k.docker.docker.util.props.PropsDockerUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

public class FWPathUtil {
    private static final String speter = "/";

    public static String getResPath(final Class<?> clazz) {
        String res = clazz.getPackage().getName();
        res = res.substring(0, res.lastIndexOf(".")).replace(".", speter);
        res = "src/main/java/" + res + "/res/";
        return res;
    }

    public static String getDockerDirPath(final Class<?> clazz) {
        String res = clazz.getPackage().getName();
        res = res.substring(0, res.lastIndexOf(".")).replace(".", speter);
        res = PropsDockerUtil.dockerDir + "docker/" + res + "/";
        return res;
    }

    public static String getFilePath(final Class<?> clazz) {
        String res = clazz.getPackage().getName();
        res = res.substring(0, res.lastIndexOf(".")).replace(".", speter);
        res = "src/main/java/" + res + "/file/";
        return res;
    }

    public static String getTestResPath(final Class<?> clazz) {
        String res = clazz.getPackage().getName();
        res = res.substring(0, res.lastIndexOf(".")).replace(".", speter);
        res = "src/test/java/" + res + "/res/";
        return res;
    }

    public static String getTestFilePath(final Class<?> clazz) {
        String res = clazz.getPackage().getName();
        res = res.substring(0, res.lastIndexOf(".")).replace(".", speter);
        res = "src/test/java/" + res + "/file/";
        return res;
    }

    public static String getTargetPath(final Class<?> clazz) {
        String res = clazz.getPackage().getName();
        res = res.replace(".", speter);
        res = "target/" + res;
        return res;
    }

    public static String getTargetPath() {
        final String res;
        res = "target/com";
        return res;
    }

    public static String getClassPath(final Class<?> clazz) {
        String res = clazz.getPackage().getName();
        res = res.replace(".", speter);
        res = PropsDockerUtil.dockerAbsDockerDir + res;
        return res;
    }

    public static List<String> getDirFilesPath(final Class<?> clazz) {
        String res = clazz.getPackage().getName();
        res = res.replace(".", speter);
        res = "src/main/java/" + res + "/";
        final File dir = new File(res);
        final FilenameFilter fileter = new FilenameFilter() {
            @Override
            public boolean accept(final File dir, final String name) {
                return !name.contains(".java");
            }
        };
        return Lists.newArrayList(dir.list(fileter));
    }

    public static void copyFileToDir(final Class<?> srcClazz, final Class<?> desClazz) throws Exception {
        String res = srcClazz.getPackage().getName();
        res = res.replace(".", speter);
        res = "src/main/java/" + res + "/";
        final File dir = new File(res);
        final FilenameFilter fileter = new FilenameFilter() {
            @Override
            public boolean accept(final File dir, final String name) {
                return !name.contains(".java");
            }
        };
        String des = desClazz.getPackage().getName();
        des = des.replace(".", speter);
        des = "target/" + des + "/";
        final File[] srcs = dir.listFiles(fileter);
        File destFile;
        for (final File srcFile : srcs) {
            destFile = new File(des + srcFile.getName());
            if (destFile.exists()) {
                FileUtils.forceDelete(destFile);
            }
            FileUtils.copyFile(srcFile, destFile);
        }
    }
}
