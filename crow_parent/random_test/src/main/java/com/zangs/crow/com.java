package com.zangs.crow;

import org.springframework.stereotype.Component;

@Component
public class com {
    @WarpRet
    public String get() {
        return "hello aop";
    }
}
