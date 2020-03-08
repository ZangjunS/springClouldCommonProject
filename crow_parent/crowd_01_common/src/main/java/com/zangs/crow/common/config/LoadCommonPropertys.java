package com.zangs.crow.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;

@Order(1)
@Configuration
@PropertySource(value = {"application-common.yml", "application.yml"}, factory = MixPropertySourceFactory.class)
public class LoadCommonPropertys {
}
