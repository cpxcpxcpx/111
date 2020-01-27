package com.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.pojo.User;
//有mapper扫描器在主程序上
public interface UserMapper {
	public ArrayList<String> showname();
	public void insertUser(User u);
	public User selectUserbyname(String username);
	public ArrayList<User> selectUsersbyname(String keyword);
}
