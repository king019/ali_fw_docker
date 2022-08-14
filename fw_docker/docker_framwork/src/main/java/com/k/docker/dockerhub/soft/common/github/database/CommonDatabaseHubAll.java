package com.k.docker.dockerhub.soft.common.github.database;

import com.k.docker.dockerhub.soft.common.github.database.cache.CommonCacheHubAll;
import com.k.docker.dockerhub.soft.common.github.database.mongo.CommonMongoInstall;
import com.k.docker.dockerhub.soft.common.github.database.mysql.CommonMysqlInstall;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CommonMysqlInstall.class, CommonCacheHubAll.class, CommonMongoInstall.class})
public class CommonDatabaseHubAll {

}
