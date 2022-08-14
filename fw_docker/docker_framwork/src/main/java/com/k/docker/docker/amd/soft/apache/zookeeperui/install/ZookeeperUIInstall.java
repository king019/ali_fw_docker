package com.k.docker.docker.amd.soft.apache.zookeeperui.install;

import com.google.common.collect.Lists;
import com.k.docker.docker.base.docker.run.base.DockerRunBase;
import com.k.docker.docker.base.soft.base.DockerBase;
import com.k.docker.docker.base.soft.base.impl.DockerBaseImpl;
import com.k.docker.docker.util.docker.SoftVersion;
import com.k.docker.docker.util.file.FileWriteUtil;
import com.k.docker.docker.util.file.ShellWriteUtil;
import com.k.docker.docker.util.path.FWPathUtil;
import com.k.docker.docker.util.props.PropsDockerUtil;
import com.k.docker.docker.util.vo.DockerBuildVO;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.List;

public class ZookeeperUIInstall extends DockerBaseImpl {
    public ZookeeperUIInstall(List<String> hosts) {
        dockerBuildVOs.add(SoftVersion.zookeeperUISoft);
        this.hosts = hosts;
    }

    @Override
    public void dockerShell(File file) throws Exception {
        DockerBuildVO dockerSoft = queryTar("zkui");
        ShellWriteUtil.writeMv(file, dockerSoft.unpress, dockerSoft.mvDir);
        List<String> lines = Lists.newArrayList();
        lines.add("cd " + PropsDockerUtil.dockerDir);
        lines.add("nohup java -jar ./zkui.jar &");
        ShellWriteUtil.writeListTail(file, lines);
    }

    public static void dockerRun(final Class clazz, final List<String> lines, final String dockerVersion, final DockerRunBase dockerRun, final List<String> hosts, String param) {
        lines.add("cd " + FWPathUtil.getClassPath(clazz));
        for (String host : hosts) {
            dockerRun.dockerRun(lines, dockerVersion, host, param, "500m");
        }
    }

    @Override
    public List<File> buildConfigFile() {
        List<File> files = Lists.newArrayList();
        try {
            List<String> lines;
            File zoo = File.createTempFile("config.cfg" + DockerBase.tempSub, DockerBase.tempSub);
            String zkhost = StringUtils.join(hosts, ":2181,") + ":2181";
            {
                lines = Lists.newArrayList();
                lines.add("#Server Port");
                lines.add("serverPort=9090");
                lines.add("#Comma seperated list of all the zookeeper servers");
                lines.add("zkServer=" + zkhost);
                lines.add("#Http path of the repository. Ignore if you dont intent to upload files from repository.");
                lines.add("scmRepo=http://myserver.com/@rev1=");
                lines.add("#Path appended to the repo url. Ignore if you dont intent to upload files from repository.");
                lines.add("scmRepoPath=//appconfig.txt");
                lines.add("#if set to true then userSet is used for authentication, else ldap authentication is used.");
                lines.add("ldapAuth=false");
                lines.add("ldapDomain=mycompany,mydomain");
                lines.add("#ldap authentication url. Ignore if using file based authentication.");
                lines.add("ldapUrl=ldap://<ldap_host>:<ldap_port>/dc=mycom,dc=com");
                lines.add("#Specific roles for ldap authenticated users. Ignore if using file based authentication.");
                lines.add("ldapRoleSet={\"users\": [{\"username\":\"domain\\\\user1\" , \"role\": \"ADMIN\" }]}");
                lines.add("userSet = {\"users\": [{ \"username\":\"admin\" , \"password\":\"admin\",\"role\":\"ADMIN\" },{ \"username\":\"appconfig\" ,\"password\":\"appconfig\",\"role\": \"USER\" }]}");
                // lines.add("ldapRoleSet={'users': [{ 'username':'domain\\\\user1' , 'role': 'ADMIN' }]}");
                // lines.add("userSet = {'users': [{'username':'admin', 'password':'manager','role': 'ADMIN'},{ 'username':'appconfig' , 'password':'appconfig','role': 'USER' }]}");
                lines.add("#Set to prod in production and dev in local. Setting to dev will clear history each time.");
                lines.add("env=prod");
                lines.add("jdbcClass=org.h2.Driver");
                lines.add("jdbcUrl=jdbc:h2:zkui");
                lines.add("jdbcUser=root");
                lines.add("jdbcPwd=manager");
                lines.add("#If you want to use mysql db to store history then comment the h2 db section.");
                lines.add("#jdbcClass=com.mysql.jdbc.Driver");
                lines.add("#jdbcUrl=jdbc:mysql://localhost:3306/zkui");
                lines.add("#jdbcUser=root");
                lines.add("#jdbcPwd=manager");
                lines.add("loginMessage=Please login using admin/manager or appconfig/appconfig.");
                lines.add("#session timeout 5 mins/300 secs.");
                lines.add("sessionTimeout=300");
                lines.add("#Default 5 seconds to keep short lived zk sessions. If you have large data then the read will take more than 30 seconds so increase this accordingly. ");
                lines.add("#A bigger zkSessionTimeout means the connection will be held longer and resource consumption will be high.");
                lines.add("zkSessionTimeout=5");
                lines.add("#Block PWD exposure over rest call.");
                lines.add("blockPwdOverRest=false");
                lines.add("#ignore rest of the props below if https=false.");
                lines.add("https=false");
                lines.add("keystoreFile=/home/user/keystore.jks");
                lines.add("keystorePwd=password");
                lines.add("keystoreManagerPwd=password");
                lines.add("# The default ACL to use for all creation of nodes. If left blank, then all nodes will be universally accessible");
                lines.add("# Permissions are based on single character flags: c (Create), r (read), w (write), d (delete), a (admin), * (all)");
                // lines.add("# For example defaultAcl={"acls": [{"scheme":"ip", "id":"192.168.1.192", "perms":"*"}, {"scheme":"ip", id":"192.168.1.0/24", "perms":"r"}]");
                lines.add("defaultAcl=");
                lines.add("X-Forwarded-For=false");
                FileWriteUtil.writeLines(zoo, lines);
            }
            files.add(zoo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return files;
    }
}
