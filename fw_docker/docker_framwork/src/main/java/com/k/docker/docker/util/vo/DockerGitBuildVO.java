package com.k.docker.docker.util.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DockerGitBuildVO {
    public String gittar;
    public String unpress;
    public String mvDir;
}
