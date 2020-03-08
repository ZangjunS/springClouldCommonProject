package com.zangs.crow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 15506
 */
@EnableDiscoveryClient
@SpringBootApplication
public class MainRedis01 {

    public static void main(String[] args) {
        SpringApplication.run(MainRedis01.class, args);
    }
}

