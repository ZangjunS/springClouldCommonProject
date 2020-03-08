package com.zangs.crow;

import com.zangs.crow.common.configpropertys.AliyunOSSSettings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Hello world!
 */
@EnableDiscoveryClient
@SpringBootApplication
public class MainFile {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MainFile.class, args);
        AliyunOSSSettings aliyunSettings = context.getBean(AliyunOSSSettings.class);
        System.out.println(aliyunSettings.getAccessKeySecret());
        System.out.println("Hello MainFile!");

    }
}
