package com.zangs.crow.common.util;


import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

public class MessageSendUtil {

    static String host = YamlPropertyUtil.getCommonYml("message.host");
    static String path = YamlPropertyUtil.getCommonYml("message.path");
    static String method = YamlPropertyUtil.getCommonYml("message.method");
    static String appcode = YamlPropertyUtil.getCommonYml("aliyun.appcode");
    static String RegisterTemplate = YamlPropertyUtil.getCommonYml("message.template.register");

    public static HttpResponse sendRegister(String mobile, String verifyCode) {

        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        //测试可用默认短信模板,测试模板为专用模板不可修改,如需自定义短信内容或改动任意字符,请联系旺旺或QQ726980650进行申请
        querys.put("content", concatRegisterMessage(RegisterTemplate, mobile, verifyCode));
        querys.put("mobile", mobile);
        Map<String, String> bodys = new HashMap<String, String>();

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            System.out.println(EntityUtils.toString(response.getEntity()));
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String concatRegisterMessage(String template, String... param) {
        return String.format(template, param[0].substring(param[0].length() - 4), param[1]);
    }

    public static void main(String[] args) {
        sendRegister("18150637359", RR.num(6));
    }
//public static void main(String[] args) {
//    String host = "https://dxyzm.market.alicloudapi.com";
//    String path = "/chuangxin/dxjk";
//    String method = "POST";
//    String appcode = "9e1586f743b3497c88873d13b410a156";
//    Map<String, String> headers = new HashMap<String, String>();
//    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
//    headers.put("Authorization", "APPCODE " + appcode);
//    Map<String, String> querys = new HashMap<String, String>();
//    //测试可用默认短信模板,测试模板为专用模板不可修改,如需自定义短信内容或改动任意字符,请联系旺旺或QQ726980650进行申请
//    querys.put("content", "【创信】你的验证码是：5873，3分钟内有效！");
//    querys.put("mobile", "18150637359");
//    Map<String, String> bodys = new HashMap<String, String>();
//
//    try {
//        /**
//         * 重要提示如下:
//         * HttpUtils请从
//         * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
//         * 下载
//         *
//         * 相应的依赖请参照
//         * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
//         */
//        HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
//        System.out.println(response.toString());
//        //获取response的body
//        //System.out.println(EntityUtils.toString(response.getEntity()));
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//}
}
