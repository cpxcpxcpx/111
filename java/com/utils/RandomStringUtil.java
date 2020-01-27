package com.utils;

import org.springframework.stereotype.Component;

@Component
public class RandomStringUtil {
	public  String randomNumeric() {
		String s="";
		//生成6位随机数字
        int s1=((int)((Math.random()*9+1)*100000));
        
        //生成5位随机数字
        int s2=((int)((Math.random()*9+1)*10000));
        
        //生成4位随机数字
        int s3=((int)((Math.random()*9+1)*1000));
   
        //生成3位随机数字
        int s4=((int)((Math.random()*9+1)*100));
    
        //生成2位随机数字
        int s5=((int)((Math.random()*9+1)*10));
     
        //生成1位随机数字
        int s6=((int)((Math.random()*9+1)));
       int res=s1+s2+s3+s4+s5+s6;
       s=s+res;
        return s;
	}

}
