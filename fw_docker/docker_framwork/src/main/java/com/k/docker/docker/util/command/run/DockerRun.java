package com.k.docker.docker.util.command.run;

import com.google.common.collect.Maps;
import com.k.docker.docker.util.command.run.enums.RunCmdEnum;
import com.k.docker.dockerhub.base.enums.DockerRegionEnum;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

@Data
@Accessors(fluent = true)
@Builder
public class DockerRun {
    private DockerRegionEnum region;
    private static String nextLine = "\n\r";
    private String nameCmd;
    private Map<String, String> vCmdMap;
    private Map<String, String> pCmdMap;
    private Map<String, String> linkMap;
    private Map<String, String> addHostMap;
    private Map<String, String> eMap;
    private List<String> consumerCmds;
    private List<String> consumerCmdsAfter;
    private String version;
    private boolean dCmd = true;
    private boolean dDns = true;
    private boolean privilegedCmd = true;
    private boolean dRestart;
    private boolean mMem;
    private String mem;
    private int cpus;
    private List<String> dnss;
    private List<String> notes;

    @Override
    public String toString() {
        if (CollectionUtils.isEmpty(dnss)) {
            dnss = List.of("172.17.0.1");
        }
        eMap = Objects.isNull(eMap) ? Maps.newHashMap() : eMap;
        StringBuilder sb = new StringBuilder();
        if (Objects.nonNull(region)) {
            sb.append(nextLine);
            sb.append(region.getHost());
        }
        for (String note : ListUtils.emptyIfNull(notes)) {
            sb.append(nextLine);
            sb.append(note);
        }
        sb.append(nextLine);
        sb.append("docker run");
        if (StringUtils.isNotBlank(nameCmd)) {
            sb.append(" ");
            sb.append(RunCmdEnum.CMD_NAME.getPre()).append(RunCmdEnum.CMD_NAME.getCmd()).append(" ").append(nameCmd);
            sb.append(" ");
            sb.append(RunCmdEnum.CMD_H.getPre()).append(RunCmdEnum.CMD_H.getCmd()).append(" ").append(nameCmd);
        }
        if (dDns) {
            for (String dns : dnss) {
                sb.append(" ");
                sb.append(RunCmdEnum.CMD_DNS.getPre()).append(RunCmdEnum.CMD_DNS.getCmd()).append(" ").append(dns);
            }
        }
        if (dCmd) {
            sb.append(" ");
            sb.append(RunCmdEnum.CMD_D.getPre()).append(RunCmdEnum.CMD_D.getCmd());
        }
        if (mMem) {
            sb.append(" ");
            sb.append(RunCmdEnum.CMD_M.getPre()).append(" ").append(StringUtils.defaultString(mem, RunCmdEnum.CMD_M.getCmd()));
        }
        if (cpus > 0) {
            sb.append(" ");
            sb.append(RunCmdEnum.CMD_CPUS.getPre()).append(RunCmdEnum.CMD_CPUS.getCmd()).append("=").append(cpus);
        }
        if (dRestart) {
            sb.append(" ");
            sb.append(RunCmdEnum.CMD_RESTART.getPre()).append(RunCmdEnum.CMD_RESTART.getCmd());
        }
        //if (privilegedCmd) {
        sb.append(" ");
        sb.append(RunCmdEnum.CMD_PRIVILEGED.getPre()).append(RunCmdEnum.CMD_PRIVILEGED.getCmd());
        //}
        MapUtils.emptyIfNull(vCmdMap).forEach((key, val) -> {
            sb.append(" ");
            sb.append(RunCmdEnum.CMD_V.getPre()).append(RunCmdEnum.CMD_V.getCmd()).append(" ").append(key).append(":").append(val);
        });
        MapUtils.emptyIfNull(linkMap).forEach((key, val) -> {
            sb.append(" ");
            sb.append(RunCmdEnum.CMD_LINK.getPre()).append(RunCmdEnum.CMD_LINK.getCmd()).append(" ").append(key).append(":").append(val);
        });
        eMap.put("TZ", "'Asia/Shanghai'");

        MapUtils.emptyIfNull(eMap).forEach((key, val) -> {
            sb.append(" ");
            sb.append(RunCmdEnum.CMD_E.getPre()).append(RunCmdEnum.CMD_E.getCmd()).append(" ").append(key).append("=").append(val);
        });
        MapUtils.emptyIfNull(pCmdMap).forEach((key, val) -> {
            sb.append(" ");
            sb.append(RunCmdEnum.CMD_P.getPre()).append(RunCmdEnum.CMD_P.getCmd()).append(" ").append(key).append(":").append(val);
        });
        MapUtils.emptyIfNull(addHostMap).forEach((key, val) -> {
            sb.append(" ");
            sb.append(RunCmdEnum.CMD_ADD_HOST.getPre()).append(RunCmdEnum.CMD_ADD_HOST.getCmd()).append(" ").append(key).append(":").append(val);
        });
        ListUtils.emptyIfNull(consumerCmds).forEach(new Consumer<>() {
            @Override
            public void accept(String cmd) {
                sb.append(" ");
                sb.append(cmd);
            }
        });
        sb.append(" --security-opt seccomp:unconfined ");
        sb.append(" ");
        sb.append(version);
        ListUtils.emptyIfNull(consumerCmdsAfter).forEach(new Consumer<>() {
            @Override
            public void accept(String cmd) {
                sb.append(" ");
                sb.append(cmd);
            }
        });
        return sb.toString();
    }
}
