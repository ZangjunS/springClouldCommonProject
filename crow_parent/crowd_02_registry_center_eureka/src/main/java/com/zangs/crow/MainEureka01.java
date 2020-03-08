package com.zangs.crow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 15506
 */
@EnableEurekaServer
@SpringBootApplication
public class MainEureka01 {

    public static void main(String[] args) {
        SpringApplication.run(MainEureka01.class, args);
    }
}
