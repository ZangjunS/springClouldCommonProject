package com.zangs.crow;

import lombok.extern.slf4j.Slf4j;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author 15506
 */
@EnableDiscoveryClient
@SpringBootApplication
public class MainDatabase01 {

    public static void main(String[] args) {


        SpringApplication.run(MainDatabase01.class, args);
    }
}

@Component
@Order(value = 10)
@Slf4j
class ApplicationRunnerImpl implements ApplicationRunner {

    /**
     *      * 会在服务启动完成后立即执行
     *      
     */
    @Autowired
    Dao dao;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Daos.createTablesInPackage(dao, "com.zangs.crow.base.bean", false);
        Daos.migration(dao, "com.zangs.crow.base.bean.*", true, false, false);
         log.info("database migration!!!");
    }
}