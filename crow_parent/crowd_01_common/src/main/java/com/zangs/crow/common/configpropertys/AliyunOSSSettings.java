package com.zangs.crow.common.configpropertys;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "aliyun.oss")

public class AliyunOSSSettings {
    String aliyun;
    String appcode;
    String userName;
    String password;
    String accessKeyID;
    String accessKeySecret;
    String endPoint;
    String crowBucketName;
}
