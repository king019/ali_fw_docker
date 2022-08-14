package com.k.docker.docker.util.command.run.enums;

import lombok.Getter;

public enum RunCmdEnum {
    CMD_H("-", "h"),
    CMD_D("-", "d"),
    CMD_P("-", "p", ":"),
    CMD_NAME("--", "name"),
    CMD_V("-", "v", ":"),
    CMD_PRIVILEGED("--", "privileged"),
    CMD_RESTART("--", "restart=always"),
    CMD_E("-", "e"),
    CMD_LINK("--", "link"),
    CMD_M("-m", "300m"),
    CMD_ADD_HOST("--", "add-host"),
    CMD_CPUS("--", "cpus"),
    CMD_DNS("--", "dns");
    @Getter
    private String pre;
    @Getter
    private String cmd;
    @Getter
    private String split;

    RunCmdEnum(String pre, String cmd) {
        this.pre = pre;
        this.cmd = cmd;
    }

    RunCmdEnum(String pre, String cmd, String split) {
        this.pre = pre;
        this.cmd = cmd;
        this.split = split;
    }
}
