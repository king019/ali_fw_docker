package com.k.docker.docker.amd.soft.apache.hadoop.hbase.single.install;

import com.google.common.collect.Lists;
import com.k.docker.docker.amd.softmix.apache.hadoop.hbase.single.hbase.build.HbaseSingleBuild;
import com.k.docker.docker.amd.softmix.apache.hadoop.hbase.single.hbase.resource.HbaseSingleResDir;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.soft.base.impl.DockerBaseImpl;
import com.k.docker.docker.util.docker.SoftVersion;
import com.k.docker.docker.util.file.FileWriteUtil;
import com.k.docker.docker.util.file.ShellWriteUtil;
import com.k.docker.docker.util.path.FWPathUtil;
import com.k.docker.docker.util.props.PropsDockerUtil;
import com.k.docker.docker.util.vo.DockerBuildVO;

import java.io.File;
import java.util.List;

public class HbaseSingleInstall extends DockerBaseImpl {
    private final String hbaseProfile = "hbaseProfile";

    public HbaseSingleInstall() {
        dockerBuildVOs.add(SoftVersion.hbaseSoft);
    }

    public static void dockerRun(Class clazz, List<String> lines, String dockerVersion, DockerRunBase dockerRun, List<String> hosts, String param) {
        lines.add("cd " + FWPathUtil.getClassPath(clazz));
        String memery = "400m";
        {
            for (String host : hosts) {
                dockerRun.dockerRun(lines, dockerVersion, host, param, memery);
            }
        }
    }

    @Override
    public List<DockerBuildVO> dockerResFile() {
        List<String> files = FWPathUtil.getDirFilesPath(HbaseSingleResDir.class);
        List<DockerBuildVO> dockerBuildVOs = Lists.newArrayList();
        DockerBuildVO vo;
        for (String fname : files) {
            vo = new DockerBuildVO(fname, fname, fname);
            dockerBuildVOs.add(vo);
        }
        return dockerBuildVOs;
    }

    @Override
    public void dockerShell(File file) throws Exception {
        DockerBuildVO dockerSoft = queryTar("hbase");
        {
            FWPathUtil.copyFileToDir(HbaseSingleResDir.class, HbaseSingleBuild.class);
        }
        String hbaseHome = PropsDockerUtil.dockerDir + "hbase";
        ShellWriteUtil.writeTar(file, dockerSoft.tar);
        ShellWriteUtil.writeMv(file, dockerSoft.unpress, dockerSoft.mvDir);
        ShellWriteUtil.writeCatAppend(file, hbaseProfile, "/etc/profile");
        ShellWriteUtil.writeSourceProfile(file);
        ShellWriteUtil.writeShell(file, "java -verbose >> java_version");
        {
            List<DockerBuildVO> dockerBuildVOs = dockerResFile();
            for (DockerBuildVO dockerBuildVO : dockerBuildVOs) {
                ShellWriteUtil.writeCatCover(file, dockerBuildVO.getTar(), PropsDockerUtil.dockerDir + "hbase/conf/" + dockerBuildVO.getTar());
            }
        }
        List<String> lines = Lists.newArrayList();
        ShellWriteUtil.writeCdSoft(file);
        lines.add("cd " + hbaseHome + "/bin");
        lines.add("./start-hbase.sh");
        ShellWriteUtil.writeListTail(file, lines);
    }

    @Override
    public List<File> buildConfigFile() {
        List<File> files = Lists.newArrayList();
        try {
            File profile = File.createTempFile(hbaseProfile + tempSub, tempSub);
            {
                FileWriteUtil.write(profile, "export HBASE_HOME=" + PropsDockerUtil.dockerDir + "hbase");
                FileWriteUtil.write(profile, "export PATH=$HBASE_HOME/bin:$PATH");
            }
            files.add(profile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return files;
    }
}
