package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import util.MySessionContext;

import com.google.gson.JsonObject;

@WebServlet("/delete")
public class delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public delete() {
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

		PrintWriter out = response.getWriter();

		String dataType = null;
		String sessionID = null;

		String jsonStr = readJSONString(request);
		JSONObject obj = null;
		JsonObject jsonObj = new JsonObject();
		ArrayList<Integer> delArr = new ArrayList();
		int id = 0;
		boolean result;
		try {
			obj = new JSONObject(jsonStr);

			dataType = obj.getString("dataType");
			sessionID = obj.getString("sessionID");
			try {
				id = obj.getInt("deleteID");
				delArr.add(id);

			} catch (JSONException e) {
				e.printStackTrace();
				JSONArray arr = obj.getJSONArray("deleteID");

				for (int i = 0; i < arr.length(); i++) {
					int arrayContent = arr.getInt(i);
					System.out.println("CONT:" + arrayContent);
					delArr.add(arrayContent);
				}
			}

			System.out.println("delete: " + id);
			HttpSession session = MySessionContext.getSession(sessionID);
			String email = (String) session.getAttribute("email");

			if (dataType.equals("bill")) {
				result = deleteBill(delArr);

				if (result) {
					jsonObj.addProperty("sessionID", sessionID);
					jsonObj.addProperty("result", true);
				} else {
					jsonObj.addProperty("sessionID", sessionID);
					jsonObj.addProperty("result", false);
				}

				out.println(jsonObj);
			} else if (dataType.equals("note")) {
				result = deleteNote(delArr);
				if (result) {
					jsonObj.addProperty("sessionID", sessionID);
					jsonObj.addProperty("result", true);
				} else {
					jsonObj.addProperty("sessionID", sessionID);
					jsonObj.addProperty("result", false);
				}
				out.println(jsonObj);
			} else if (dataType.equals("plan")) {
				result = deletePlan(delArr);

				if (result) {
					jsonObj.addProperty("sessionID", sessionID);
					jsonObj.addProperty("result", true);
				} else {
					jsonObj.addProperty("sessionID", sessionID);
					jsonObj.addProperty("result", false);
				}
				out.println(jsonObj);
			} else if (dataType.equals("item")) {
				result = deleteItem(delArr);

				if (result) {
					jsonObj.addProperty("sessionID", sessionID);
					jsonObj.addProperty("result", true);
				} else {
					jsonObj.addProperty("sessionID", sessionID);
					jsonObj.addProperty("result", false);
				}
				out.println(jsonObj);
			}

			out.close();

		} catch (JSONException e) {
			e.printStackTrace();
		}

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

	private boolean deleteBill(ArrayList<Integer> arr) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
		boolean result = false;
		try {
			Context ctx = (Context) new InitialContext()
					.lookup("java:comp/env");
			conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection();

			for (int i = 0; i < arr.size(); i++) {
				sql = "delete from note where note_id = ?;";
				stmt = conn.prepareStatement(sql);
				stmt.setDouble(1, arr.get(i));
				int j = stmt.executeUpdate();
				if (j != 0) {
					result = true;
				} else {
					result = false;
				}
			}

			stmt.close();
			stmt = null;
			conn.close();
			conn = null;
			
			if (result) {
				return true;
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
		return false;

	}

	private boolean deleteNote(ArrayList<Integer> arr) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
		boolean result = false;
		try {
			Context ctx = (Context) new InitialContext()
					.lookup("java:comp/env");
			conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection();

			for (int i = 0; i < arr.size(); i++) {

				sql = "delete from note where note_id = ?;";
				stmt = conn.prepareStatement(sql);
				stmt.setDouble(1, arr.get(i));
				int j = stmt.executeUpdate();
				if (j != 0) {
					result = true;
				} else {
					result = false;
				}
			}

			stmt.close();
			stmt = null;
			conn.close();
			conn = null;

			if (result) {
				return true;
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
		return false;

	}

	private boolean deletePlan(ArrayList<Integer> arr) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
		boolean result = false;
		try {
			Context ctx = (Context) new InitialContext()
					.lookup("java:comp/env");
			conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection();

			for (int i = 0; i < arr.size(); i++) {
				sql = "delete from note where note_id = ?;";
				stmt = conn.prepareStatement(sql);
				stmt.setDouble(1, arr.get(i));
				int j = stmt.executeUpdate();
				if (j != 0) {
					result = true;
				} else {
					result = false;
				}
			}

			stmt.close();
			stmt = null;
			conn.close();
			conn = null;
			
			if (result) {
				return true;
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
		return false;

	}

	private boolean deleteItem(ArrayList<Integer> arr) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
		boolean result = false;
		try {
			Context ctx = (Context) new InitialContext()
					.lookup("java:comp/env");
			conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection();

			for (int i = 0; i < arr.size(); i++) {
				sql = "delete from note where note_id = ?;";
				stmt = conn.prepareStatement(sql);
				stmt.setDouble(1, arr.get(i));
				int j = stmt.executeUpdate();
				if (j != 0) {
					result = true;
				} else {
					result = false;
				}
			}

			stmt.close();
			stmt = null;
			conn.close();
			conn = null;
			
			if (result) {
				return true;
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
		return false;

	}

}
