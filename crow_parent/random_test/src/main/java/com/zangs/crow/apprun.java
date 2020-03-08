package com.zangs.crow;

import com.zangs.crow.common.configpropertys.TokenSettings;
import com.zangs.crow.common.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class apprun implements ApplicationRunner {
    @Autowired
    com c;
    @Autowired
    TokenSettings tokenSettings;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("---------");
        System.out.println(JwtTokenUtil.secretKey);
        System.out.println(c.get());
    }
}
