package com.heima.service;

import java.util.List;

import com.heima.dao.UserDao;
import com.heima.dao.impl.UserDaoImpl;
import com.heima.entity.User;

public class UserService {
	private UserDao ud = new UserDaoImpl();
	public void regist(User user) {
		//check duplicate
		User existUser = ud.getUserByName(user.getName());
		if(existUser!=null){
			throw new RuntimeException("用户名已经存在");
		}
		
		try {
			ud.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("保存用户失败");
		}
	}
	
	public User login(User user){
		//find user in DB
		User dbUser = ud.getUserByName(user.getName());
		if(dbUser==null){
			throw new RuntimeException("不存在此用户名");
		}
		//check the password
		if(!user.getPassword().equals(dbUser.getPassword())){
			throw new RuntimeException("密码错误");
		}
		return dbUser;
	}
	
	public List<User> getAllUser(){
		return ud.getAllUser();
	}
}
