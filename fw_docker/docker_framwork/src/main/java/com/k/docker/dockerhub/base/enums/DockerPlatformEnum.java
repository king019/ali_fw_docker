package com.k.docker.dockerhub.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DockerPlatformEnum {
    AMD("x86"),
    ARM64("arm"),
    ;
    private String platform;
}
