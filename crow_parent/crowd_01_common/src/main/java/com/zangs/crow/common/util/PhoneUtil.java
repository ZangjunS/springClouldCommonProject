package com.zangs.crow.common.util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneUtil {
    /**
     * 验证手机号是否合法
     *
     * @return
     */
    public static boolean isMobileNO(String mobile) {
        if (mobile.length() != 11) {
            return false;
        } else {
            /**
             * 移动号段正则表达式
             */
            String pat1 = "^((13[4-9])|(147)|(15[0-2,7-9])|(178)|(18[2-4,7-8])|(198))\\d{8}|(1705)\\d{7}$";
            /**
             * 联通号段正则表达式
             */
            String pat2 = "^((13[0-2])|(145)|(15[5-6])|(166)|(175)|(176)|(18[5,6]))\\d{8}|(1709)\\d{7}$";
            /**
             * 电信号段正则表达式
             */
            String pat3 = "^((133)|(153)|(177)|(18[0,1,9])|(149)|(199))\\d{8}$";
            /**
             * 虚拟运营商正则表达式
             */
            String pat4 = "^((170))\\d{8}|(1718)|(1719)\\d{7}$";

            List<String> pats = Arrays.asList(pat1, pat2, pat3, pat4);

            boolean isPhone = pats.stream().anyMatch(s -> {

                Pattern pattern = Pattern.compile(s);
                Matcher match = pattern.matcher(mobile);
                boolean isMatch = match.matches();
                return isMatch;
            });
            return isPhone;
        }
    }
}