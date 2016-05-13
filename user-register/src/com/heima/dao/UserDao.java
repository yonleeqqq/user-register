package com.heima.dao;

import java.util.List;

import com.heima.entity.User;

public interface UserDao {
	void save(User user) ;
	User getUserByName(String name);
	List<User> getAllUser();
}
