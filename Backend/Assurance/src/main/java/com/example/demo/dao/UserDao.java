package com.example.demo.dao;

import com.example.demo.entities.User;

public interface UserDao {

	//public User findByUsername(String username);
	
	public User signIn(String username, String password);
}
