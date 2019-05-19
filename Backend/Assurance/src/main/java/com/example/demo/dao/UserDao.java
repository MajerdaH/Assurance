package com.example.demo.dao;

import java.math.BigDecimal;

import com.example.demo.entities.User;

public interface UserDao {

	//public User findByUsername(String username);
	
	public User signIn(String username, String password);

	public int changePassword(String oldPassword, String newPassword, BigDecimal ponum, String mat );
	public int addUser(String newPassword, String username, BigDecimal ponum, String mat);

}
