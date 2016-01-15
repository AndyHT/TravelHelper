package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

import com.google.gson.JsonObject;

@WebServlet("/PushRecommends")
public class PushRecommends extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PushRecommends() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("charset=utf-8");
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setHeader("Access-Control-Max-Age", "86400");

		/**
		 * 推送内容所需字段： 推送文章: content 目的地: destination 推荐类型: type 
		 * 推荐时间: time 图片: picture 标题: title
		 */

		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createDate = sdf.format(date);

		String content = request.getParameter("content");
		String destination = request.getParameter("destination");
		String type = request.getParameter("type");
		String time = createDate;
		String title = request.getParameter("title");
		
		String picture = request.getParameter("picture");

		PrintWriter out = response.getWriter();
		JsonObject myObj = new JsonObject();
		System.out.println(content + " " + destination + " " + type + " "
				+ time + " " + picture);
		push(content, destination, type, time, picture, title);
//		if (pushResult != 0) {
//			myObj.addProperty("result", true);
//		} else {
//			myObj.addProperty("result", false);
//		}

		out.println(myObj.toString());
		out.close();
	}

	private void push(String content, String destination, String type,
			String time, String picture, String title) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
		int i = 0;
		try {
			Context ctx = (Context) new InitialContext()
					.lookup("java:comp/env");
			conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection();
//			conn = DBHelper.getConnection();

			sql = "insert into tip (content, destination, type, time, picture, title) values (?, ?, ?, ?, ?, ?);";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, content);
			stmt.setString(2, destination);
			stmt.setString(3, type);
			stmt.setString(4, time);
			stmt.setString(5, picture);
			stmt.setString(6, title);

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
	}

}
