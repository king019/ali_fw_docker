package com.k.docker.docker.amd.softmix.apache.hadoop.hbase.pseudo_distributed.hbase.command;

import com.k.docker.docker.base.docker.command.CommandFileBase;
import com.k.docker.docker.util.props.PropsDockerUtil;

public class HbasePseudoDistributedCommand extends CommandFileBase {
    @Override
    public void test() {
        final String hbaseHome = PropsDockerUtil.dockerDir + "hbase";
        lines.add("cd " + hbaseHome + "/bin");
        lines.add("./start-hbase.sh");
        lines.add("16010");
    }
}
