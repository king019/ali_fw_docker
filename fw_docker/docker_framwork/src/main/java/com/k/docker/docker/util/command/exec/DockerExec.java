package com.k.docker.docker.util.command.exec;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@Builder
public class DockerExec {
    private String nameCmd;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("docker exec -it");
        sb.append(" ");
        sb.append(nameCmd);
        sb.append(" ");
        sb.append("/bin/bash");
        sb.append("\n\r");
        return sb.toString();
    }
}
