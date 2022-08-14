package com.k.docker.dockerhub.base.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public enum DockerRegionEnum {
    //HANG_ZHOU("hangzhou", "registry.cn-hangzhou.aliyuncs.com"),
//    SHANG_HAI("aliyun_shanghai", "registry.cn-shanghai.aliyuncs.com"),
//    HUHEHAOTE("aliyun_huhehaote", "registry.cn-huhehaote.aliyuncs.com"),
//    SHENZHEN("aliyun_shenzhen", "registry.cn-shenzhen.aliyuncs.com"),
    BEI_JING("aliyun_beijing", "registry.cn-beijing.aliyuncs.com"),
    DOCKER("docker", ""),
    //SMP("smp", "docker:5000"),
    LOCAL("local_region", "docker:5000", true);
    private String region;
    private String host;
    private boolean special;


    DockerRegionEnum(String region, String host) {
        this.region = region;
        this.host = host;
    }

    DockerRegionEnum(String region, String host, boolean special) {
        this.region = region;
        this.host = host;
        this.special = special;
    }

    public static String getVersion(DockerRegionEnum regionEnum, String version) {
        return StringUtils.isNotBlank(regionEnum.getHost()) ? regionEnum.getHost() + "/" + version : version;
    }
}
