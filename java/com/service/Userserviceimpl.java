package com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.consumer.Consumer;
import com.mapper.UserMapper;
import com.pojo.User;
import com.utils.RandomStringUtil;
import com.utils.RedisUtil;

@Component
public class Userserviceimpl implements Userservice{
	@Autowired
	private UserMapper um;
	@Autowired
    private AmqpTemplate rabbitTemplate;
	@Autowired
    private RedisUtil redisutil;
	@Autowired
    private RandomStringUtil randomstringutil;
	
	public ArrayList<String> showname(){
		return um.showname();
	}
	@Override
	public void insertUser(User u) {
		
		um.insertUser(u);
		
	}
	@Override
	public User selectUserbyname(String username) {
		return um.selectUserbyname(username);
		 
	}
	@Override
	public ArrayList<User> selectUsersbyname(String keyword) {
		return um.selectUsersbyname(keyword);
	}
	@Override
	public void sendSms(String mobile) {
	//  * 1）生成六位随机数验证码
        String code = "123456";
        System.out.println("==================code:======="+code);
        //  * 2）以手机号作为key，存入redis
        redisutil.set("mobile", "code");
              //  * 3）将手机号与验证码发送到MQ
         Map<String,String> info = new HashMap<>();
         info.put("mobile",mobile);
         info.put("code",code);
         rabbitTemplate.convertAndSend("rabbitmqqueue", info);
         
		
	}
}
