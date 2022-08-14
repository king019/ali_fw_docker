package com.k.docker.docker.amd.softmix.apache.hadoop.hadoop.pseudo_distributed.command;

import com.k.docker.docker.base.docker.command.CommandFileBase;

public class HadoopPseudoDistributedCommand extends CommandFileBase {
    @Override
    public void test() {
        lines.add(" ssh localhost");
        lines.add("bin/hdfs namenode -format ");
        lines.add(" sbin/start-dfs.sh ");
        lines.add("50070 ");
        lines.add("bin/hdfs dfs -mkdir /user ");
        lines.add("bin/hdfs dfs -mkdir input ");
        lines.add("bin/hdfs dfs -put etc/hadoop/*.xml input ");
        lines.add("sbin/start-yarn.sh ");
        lines.add("8088 ");
        lines.add("sbin/stop-yarn.sh ");
    }
}
