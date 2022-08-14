package com.k.docker.docker.util.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DockerBuildVO {
    public String tar;
    public String unpress;
    public String mvDir;
}
