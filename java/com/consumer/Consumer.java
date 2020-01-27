package com.consumer;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.aliyuncs.exceptions.ClientException;
import com.utils.Smsutil;

@Component

public class Consumer {
    @Autowired
    private  Smsutil smsutil;
    @Value("${aliyun.sms.template_code}")//@value注解用于取到配置文件yml的信息
    private String template_code;//模板编号
    @Value("${aliyun.sms.sign_name}")
    private String sign_name;//签名
    @RabbitListener(queues ="rabbitmqqueue")
    @RabbitHandler
    public void sendSms(Map<String,String> map){
        System.out.println("手机号："+map.get("mobile"));
        System.out.println("验证码："+map.get("code"));
        System.out.println("template_code:"+template_code);
        System.out.println("sign_name:"+sign_name);
        try {
        	 smsutil.sendsms(map.get("mobile"),template_code,sign_name,"{\"code\":\""+map.get("code")+"\"}");
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}