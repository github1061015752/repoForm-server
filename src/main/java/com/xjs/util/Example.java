package com.xjs.util;


import com.apistd.uni.Uni;
import com.apistd.uni.UniException;
import com.apistd.uni.UniResponse;
import com.apistd.uni.sms.UniMessage;
import com.apistd.uni.sms.UniSMS;
import kong.unirest.Unirest;
import org.springframework.cache.annotation.Cacheable;

import java.util.HashMap;
import java.util.Map;
public class Example {
   // @Autowired
    //private CacheManager cacheManager;
    public static String ACCESS_KEY_ID = "PC5dnBRsakfzKaN1gY52a6w4E8vwMWJ8fUBcyGRkuXk1TUFCg";
    //private static String ACCESS_KEY_SECRET = "your access key secret";
   /* public static void main(String [] args) {
        // 初始化
        Uni.init(ACCESS_KEY_ID); // 若使用简易验签模式仅传入第一个参数即可
        // 设置自定义参数 (变量短信)
        Map<String, String> templateData = new HashMap<String, String>();
        templateData.put("code", random.getSixRandom());
        String phone="15980386723";

        // 构建信息
        UniMessage message = UniSMS.buildMessage()
                .setTo(phone)
                .setSignature("小爽爽美妆")
                .setTemplateId("pub_verif_ttl3")
                .setTemplateData(templateData);

        // 发送短信
        try {
            Unirest.config().verifySsl(false);
            UniResponse res = message.send();
            System.out.println("返回结果"+res.code);
        } catch (UniException e) {
            System.out.println("Error: " + e);
            System.out.println("RequestId: " + e.requestId);
        }
    }*/
    @Cacheable
    public static UniResponse  sendSms(String phone,String code) {
        // 初始化
        Uni.init(ACCESS_KEY_ID); // 若使用简易验签模式仅传入第一个参数即可
        // 设置自定义参数 (变量短信)
        Map<String, String> templateData = new HashMap<String, String>();
        templateData.put("code", code);
        //String phone="13755123147";
        // 构建信息
        UniMessage message = UniSMS.buildMessage()
                .setTo(phone)
                .setSignature("小爽爽美妆")
                .setTemplateId("pub_verif_ttl3")
                .setTemplateData(templateData);

        // 发送短信
        try {
            getFromDB(code);
            Unirest.config().verifySsl(false);
            UniResponse res = message.send();
            System.out.println("返回结果"+res.code);
            return res;
        } catch (UniException e) {
            System.out.println("Error: " + e);
            System.out.println("RequestId: " + e.requestId);
        }
        return null;
    }

    @Cacheable(cacheNames = "codeCache", key = "#id")
    public  static String getFromDB(String id) {
        System.out.println("模拟去db查询~~~" + id);
        return id;
    }



}
