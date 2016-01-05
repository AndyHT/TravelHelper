package dao;

import java.util.ArrayList;
import java.sql.*;

import util.DBHelper;
import entity.User;

public class UserDAO {
	
	public static ArrayList<User> getAllUsers() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<User> userList = new ArrayList<User>();
		
		try {
			conn = DBHelper.getConnection();
			String sql = "select * from user;";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setUserName(rs.getString("userName"));
				user.setGender(rs.getString("gender"));
				user.setMail(rs.getString("mail"));
				user.setPassword(rs.getString("password"));
				user.setProfile(rs.getString("profile"));
				userList.add(user);
			}
			return userList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			// 释放数据集对象
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 释放语句对象
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	public static User getUserById(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User user = new User();
		try {
			conn = DBHelper.getConnection();
			String sql = "select * from user where user_id = ?;";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while(rs.next()) {
				user.setUserName(rs.getString("userName"));
				user.setGender(rs.getString("gender"));
				user.setMail(rs.getString("mail"));
				user.setPassword(rs.getString("password"));
				user.setProfile(rs.getString("profile"));
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			// 释放数据集对象
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 释放语句对象
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		UserDAO userDao = new UserDAO();
		ArrayList<User> user = new ArrayList<User>();
		user = userDao.getAllUsers();
		System.out.println(user.get(0).getUserName());
		User user1 = new User();
		
		user1 = userDao.getUserById(1);
		System.out.println(user1.getUserName());
	}

}