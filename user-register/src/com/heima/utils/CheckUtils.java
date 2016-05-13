package com.heima.utils;

import java.util.HashMap;
import java.util.Map;

import com.heima.entity.User;

public class CheckUtils {
	public static Map<String,String> checkUser(User user){
		Map<String,String> map = new HashMap<String,String>();
		if(user.getName()==null||"".equals(user.getName().trim())){
			map.put("name", "用户名不能为空");
		}
		if(user.getPassword()==null||"".equals(user.getPassword().trim())){
			map.put("password", "密码不能为空");
		}
		return map;
	}

}
