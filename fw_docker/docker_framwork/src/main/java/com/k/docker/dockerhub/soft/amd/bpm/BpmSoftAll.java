package com.k.docker.dockerhub.soft.amd.bpm;

import com.k.docker.dockerhub.soft.amd.bpm.camunda.CamundaInstall;
import com.k.docker.dockerhub.soft.amd.bpm.camunda.CamundaRunInstall;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CamundaInstall.class, CamundaRunInstall.class})
public class BpmSoftAll {

}
