package com.heima.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.heima.dao.UserDao;
import com.heima.entity.User;
import com.heima.utils.JDBCUtils;

public class UserDaoImpl implements UserDao {

	public void save(User user) {
		// get conn
		Connection conn = JDBCUtils.getConnection();
		// prepare sql
		String sql = "INSERT INTO `user`          "
				+ "(`name`,                        "
				+ "   `password`,                     "
				+ "   `email`)                        "
				+ "   VALUES (?,?,?)                ";
		// get prepareStatement
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			// set params
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			// execute sql
			int result = ps.executeUpdate();
			if (result != 1) {
				throw new RuntimeException("保存用户失败");
			}
			// close conn
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("保存失败");
		} finally {
			JDBCUtils.close(conn, ps, null);
		}
	}

	public User getUserByName(String name) {
		User user = null;
		// get conn
		Connection conn = JDBCUtils.getConnection();
		// write sql
		String sql = "select * from user where name=?";
		// get preparedStatement
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			// set params
			ps.setString(1, name);
			// executeQuery
			rs = ps.executeQuery();
			// resultSet to User
			if (rs.next()) {
				user = new User();
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("查询用户失败！");
		} finally {
			//close conn
			JDBCUtils.close(conn, ps, rs);
		}
		return user;
	}
	
	public List<User> getAllUser(){
		List<User> userList = new ArrayList<User>();
		Connection conn = JDBCUtils.getConnection();
		String sql = "select * from user";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setId(rs.getLong("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				userList.add(user);
			}
			return userList;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("读取用户列表失败");
		}finally{
			JDBCUtils.close(conn, ps, rs);
		}
	}

}
