package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import dao.GetData;
import entity.Bill;
import entity.Item;
import entity.Note;
import entity.Schedule;
import entity.User;
import util.MySessionContext;

@WebServlet("/data")
public class data extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public data() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
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
		GetData getData = new GetData();
		String dataType = null;
		String sessionID = null;
		String email = null;
		String jsonStr = readJSONString(request);
		JSONObject obj = null;
		/**
		 * 取前端JSON
		 */
		try {
			/**
			 * 获取sessionID和dataType
			 */
			obj = new JSONObject(jsonStr);
			dataType = obj.getString("dataType");
			sessionID = obj.getString("sessionID");
			System.out.println(dataType + "&&&" + sessionID);
			HttpSession session = MySessionContext.getSession(sessionID);
			System.out.println(session.toString());
			email = (String) session.getAttribute("email");
			System.out.println(email);
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		JsonArray jsonArr = new JsonArray();
		JsonObject obj2 = new JsonObject();
		
		obj2.addProperty("sessionID", sessionID);
		obj2.addProperty("dataType", dataType);
		jsonArr.add(obj2);
		
		try {
			
			if (email != null) {
				int id = getIdByEmail(email);
				System.out.println(id);
				if (id != 0) {
					/**
					 * 获取bill
					 */
					if (dataType.equals("bill")) {
						/**
						 * 根据id获取bill里的flag
						 * 根据flag获取bill里的元素
						 */
						ArrayList<Bill> bill = getData.GetBill(id);
						for (Bill b : bill) {
							JsonObject myObj = new JsonObject();
							myObj.addProperty("bill_id", b.getBill_id());
							myObj.addProperty("value", b.getValue());
							myObj.addProperty("bill_description", b.getBill_description());
							myObj.addProperty("bill_type", b.getBill_time());
							myObj.addProperty("bill_type", b.getBill_type());
							jsonArr.add(myObj);
						}
						out.println(jsonArr);
					
					}
					/*
					 * 获取note
					 */
					else if (dataType.equals("note")) {
						/**
						 * 根据id获取note
						 */
						ArrayList<Note> note = getData.GetNote(id);
						for (Note n : note) {
							JsonObject myObj = new JsonObject();
							myObj.addProperty("note_id", n.getNote_id());
							myObj.addProperty("content", n.getContent());
							myObj.addProperty("time", n.getTime());
							jsonArr.add(myObj);
						}
						out.println(jsonArr);
					}
					else if (dataType.equals("plan")) {
						/**
						 * 根据id获取plan
						 */
						ArrayList<Schedule> schedule = getData.GetSchedule(id);
						for (Schedule s : schedule) {
							JsonObject myObj = new JsonObject();
							myObj.addProperty("schedule_id", s.getSchedule_id());
							myObj.addProperty("start", s.getStrat_date());
							myObj.addProperty("end", s.getEnd_date());
							myObj.addProperty("destination", s.getDestination());
							myObj.addProperty("picture", s.getPicture());
							jsonArr.add(myObj);
						}
						out.println(jsonArr);
					}
					else if (dataType.equals("item")) {
						/**
						 * 根据id获取item
						 */
						ArrayList<Item> item = getData.GetItem(id);
						for (Item s : item) {
							JsonObject myObj = new JsonObject();
							myObj.addProperty("item_id", s.getItem_id());
							myObj.addProperty("item_num", s.getItem_num());
							myObj.addProperty("item_description", s.getItem_description());
							myObj.addProperty("item_name", s.getItem_name());
							jsonArr.add(myObj);
						}
						out.println(jsonArr);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

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
	
}
