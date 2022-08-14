package com.k.docker.docker.util.command.container;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@Builder
public class DockerContainer {
    private String nameCmd;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("docker container start");
        sb.append(" ");
        sb.append(nameCmd);
        sb.append(" ");
        sb.append("\n\r");
        sb.append("docker container stop");
        sb.append(" ");
        sb.append(nameCmd);
        sb.append(" ");
        sb.append("\n\r");
        sb.append("docker container rm");
        sb.append(" ");
        sb.append(nameCmd);
        sb.append(" ");
        sb.append("\n\r");
        return sb.toString();
    }
}
