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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonObject;

import util.MySessionContext;

@WebServlet("/returnData")
public class returnData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public returnData() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("charset=utf-8");
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setHeader("Access-Control-Max-Age", "86400");
		PrintWriter out = response.getWriter();
		/**
		 * 获取创建bill, note, item的时间
		 */
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createDate = sdf.format(date);
		
		String dataType = null;
		String sessionID = null;
		int flag = 0;
		String jsonStr = readJSONString(request);
		System.out.println(jsonStr);
		JSONObject obj = null;
		
		boolean result;
		try {
			JsonObject myObj = new JsonObject();;
			obj = new JSONObject(jsonStr);
			dataType = obj.getString("dataType");
			sessionID = obj.getString("sessionID");
			HttpSession session = MySessionContext.getSession(sessionID);
			String email = (String) session.getAttribute("email");
			flag = getIdByEmail(email);
			myObj.addProperty("sessionID", sessionID);
			if (dataType.equals("bill")) {
				double value = obj.getDouble("value");
				String bill_description = obj.getString("bill_description");
				String bill_type = obj.getString("bill_type");
//				String bill_time = obj.getString("bill_time");
				
				result = insertBill(value, bill_description, bill_type, createDate, flag); 
				
				if (result) {
					myObj.addProperty("result", true);
//					myObj.addProperty("sessionID", sessionID);
				} else {
//					myObj.addProperty("sessionID", sessionID);
					myObj.addProperty("result", false);
				}
				
				out.println(myObj);
			} else if (dataType.equals("note")) {
				String content = obj.getString("content");
//				String time = obj.getString("time");
				
				result = insertNote(content, createDate, flag);
				System.out.println(result);
				if (result) {
					myObj.addProperty("result", true);
//					myObj.addProperty("sessionID", sessionID);
					
				} else {
//					myObj.addProperty("sessionID", sessionID);
					myObj.addProperty("result", false);
				}
				out.println(myObj);
			} else if (dataType.equals("plan")) {
				String start_date = obj.getString("start_date");
				String end_date = obj.getString("end_date");
				String destination = obj.getString("destination");
				String picture = obj.getString("picture");
				
				result = insertPlan(picture, start_date, end_date, destination, flag);
				
				if (result) {
//					myObj.addProperty("sessionID", sessionID);
					myObj.addProperty("result", true);
				} else {
//					myObj.addProperty("sessionID", sessionID);
					myObj.addProperty("result", false);
				}
				out.println(myObj);
			} else if (dataType.equals("item")) {
//				String item_time = obj.getString("item_time");
				String item_description = obj.getString("item_description");
				int item_num = obj.getInt("item_num");
				String item_name = obj.getString("item_name");
				
				result = insertItem(createDate, item_description, item_num, item_name, flag);
				
				if (result) {
//					myObj.addProperty("sessionID", sessionID);
					myObj.addProperty("result", true);
				} else {
//					myObj.addProperty("sessionID", sessionID);
					myObj.addProperty("result", false);
				}
				out.println(myObj);
			}
			
			out.close();
		
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	
	private String readJSONString(HttpServletRequest request){
        StringBuffer json = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while((line = reader.readLine()) != null) {
                json.append(line);
            }
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }
        return json.toString();
    }
	
	private boolean insertBill(double value, String bill_description, String bill_type, String bill_time, int flag) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
		try {
			Context ctx = (Context) new InitialContext()
					.lookup("java:comp/env");
			conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection();

			sql = "insert into bill (value, bill_description, bill_type, bill_time, flag) values (?, ?, ?, ?, ?);";
			stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, value);
			stmt.setString(2, bill_description);
			stmt.setString(3, bill_type);
			stmt.setString(4, bill_time);
			stmt.setInt(5, flag);

			int i = stmt.executeUpdate();
			if (i != 0) {
				return true;
			}

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
		return false;

	}
	
	private boolean insertNote(String content, String time, int flag) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
		try {
			Context ctx = (Context) new InitialContext()
					.lookup("java:comp/env");
			conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection();

			sql = "insert into note (content, time, flag) values (?, ?, ?);";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, content);
			stmt.setString(2, time);
			stmt.setInt(3, flag);

			int i = stmt.executeUpdate();
			if (i != 0) {
				return true;
			}

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
		return false;

	}
	
	private boolean insertPlan(String start_date, String end_date, String destination, String picture, int flag) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
		try {
			Context ctx = (Context) new InitialContext()
					.lookup("java:comp/env");
			conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection();

			sql = "insert into note (plan_num, start_date, end_date, description, flag) values (?, ?, ?, ?, ?);";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, start_date);
			stmt.setString(2, end_date);
			stmt.setString(3, destination);
			stmt.setString(4, picture);
			stmt.setInt(5, flag);

			int i = stmt.executeUpdate();
			if (i != 0) {
				return true;
			}

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
		return false;

	}
	
	private boolean insertItem(String item_time, String item_description, int item_num, String item_name, int flag) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
		try {
			Context ctx = (Context) new InitialContext()
					.lookup("java:comp/env");
			conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection();

			sql = "insert into note (item_time, item_description, item_num, item_name, flag) values (?, ?, ?, ?, ?);";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, item_time);
			stmt.setString(2, item_description);
			stmt.setInt(3, item_num);
			stmt.setString(4, item_name);
			stmt.setInt(5, flag);

			int i = stmt.executeUpdate();
			if (i != 0) {
				return true;
			}

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
		return false;

	}
	
	private int getIdByEmail(String email) {
		int id = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
		ResultSet rs = null;
		try {
			Context ctx = (Context) new InitialContext()
					.lookup("java:comp/env");
			conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection();

			sql = "select userID from user where email = ?;";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				id = rs.getInt(1);
			}	

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
		return id;

	}

}
