package com.zangs.crow.common.util;

import org.apache.commons.lang.RandomStringUtils;
import org.nutz.lang.random.R;

public class RR extends R {
    public static String strWithNum(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public static String str(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }
    public static String num(int length) {
        return RandomStringUtils.randomNumeric(length);
    }

}
