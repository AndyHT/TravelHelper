package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.json.JSONObject;

import com.google.gson.JsonObject;

@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public register() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("charset=utf-8");
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setHeader("Access-Control-Max-Age", "86400");

		/**
		 * 用户注册字段：userName, gender, email, password
		 * 
		 */
		String userName = null;
		String gender = null;
		String email = null;
		String password = null;
		String jsonStr = readJSONString(request);
		JSONObject obj = null;
		System.out.println(jsonStr);

		try {
			/**
			 * 获取用户注册字段
			 */
			obj = new JSONObject(jsonStr);
			userName = obj.getString("userName");
			gender = obj.getString("gender");
			email = obj.getString("email");
			password = obj.getString("password");
//			System.out.println("userName: " + userName);

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String registerDate = sdf.format(date);
		// 头像默认为空
		String profile = "aaa";
		PrintWriter out = response.getWriter();
		boolean confirm = confirmRegiser(email);
		JsonObject myObj = new JsonObject();
		if (confirm) {

			int result = insertUserInfo(userName, gender, email, password,
					profile, registerDate);

			/**
			 * 返回JSON {"success" : true, "result" : "Register succeeded"} or
			 * {"success" : false, "result" : "Register failed"}
			 */

			
			if (result == 1) {
				myObj.addProperty("result", true);
			} else {
				myObj.addProperty("result", false);
			}

//			out.println(myObj.toString());
		} else {
			myObj.addProperty("result", false);
			
		}
		out.println(myObj.toString());
		out.close();
	}

	private int insertUserInfo(String name, String gender, String email,
			String password, String profile, String registerDate) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
		int i = 0;
		try {
			Context ctx = (Context) new InitialContext()
					.lookup("java:comp/env");
			conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection();

			sql = "insert into user (userName, gender, email, password, profile, registerDate) values (?, ?, ?, ?, ?, ?);";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, gender);
			stmt.setString(3, email);
			stmt.setString(4, password);
			stmt.setString(5, profile);
			stmt.setString(6, registerDate);

			i = stmt.executeUpdate();

			stmt.close();
			stmt = null;
			conn.close();
			conn = null;

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlex) {
				}
				stmt = null;
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException sqlex) {
				}
				conn = null;
			}
		}
		return i;
	}

	private String readJSONString(HttpServletRequest request) {
		StringBuffer json = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null) {
				json.append(line);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return json.toString();
	}

	private boolean confirmRegiser(String email) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
		boolean result = true;
		try {
			Context ctx = (Context) new InitialContext()
					.lookup("java:comp/env");
			conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection();

			sql = "select email from user;";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if (rs.getString(1).equals(email)) {
					return false;
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlex) {
				}
				stmt = null;
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException sqlex) {
				}
				conn = null;
			}
		}
		return result;

	}
	
	

}
