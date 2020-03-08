package com.zangs.crow.common.config;

import com.zangs.crow.common.configpropertys.TokenSettings;
import com.zangs.crow.common.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 15506
 */
@Configuration
public class StaticPropertyInjectConfig {
    @Autowired
    TokenSettings tokenSettings;

    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        JwtTokenUtil.setTokenSettings(tokenSettings);
        return new JwtTokenUtil();
    }
}
