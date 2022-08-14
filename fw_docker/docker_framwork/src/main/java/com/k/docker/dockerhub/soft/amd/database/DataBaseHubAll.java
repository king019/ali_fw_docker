package com.k.docker.dockerhub.soft.amd.database;

import com.k.docker.dockerhub.soft.amd.database.db.MysqlDockerInstall;
import com.k.docker.dockerhub.soft.amd.database.db.PostgresDockerInstall;
import com.k.docker.dockerhub.soft.amd.database.db.SqlServerDockerInstall;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({MysqlDockerInstall.class, PostgresDockerInstall.class, SqlServerDockerInstall.class})
public class DataBaseHubAll {

}
