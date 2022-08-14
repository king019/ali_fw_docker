package com.k.docker.dockerhub.soft.common.github.ci.drone;

import com.k.docker.dockerhub.soft.common.github.ci.drone.item.CommonDroneInstall;
import com.k.docker.dockerhub.soft.common.github.ci.drone.item.CommonDroneRunnerInstall;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CommonDroneInstall.class, CommonDroneRunnerInstall.class})
public class CommonDroneHubAll {

}
