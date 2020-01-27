package com.service;

import java.util.ArrayList;

import com.pojo.User;

public interface Userservice {
	public void insertUser(User u);
	public ArrayList<String> showname();
	public User selectUserbyname(String username);
	public ArrayList<User> selectUsersbyname(String keyword);
	public void sendSms(String mobile);
}
