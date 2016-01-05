package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


@WebServlet("/RegisterServlet")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Register() {
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
		
		/**
		 * 用户注册字段：userName, gender, mail, password
		 * 
		 */
		String userName = request.getParameter("userName");
		String gender = request.getParameter("gender");
		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		// 头像默认为空
		String profile = null;
		PrintWriter out = response.getWriter();
		
		int result = insertUserInfo(userName, gender, mail, password, profile);
		
		/**
		 * 返回JSON {"success" : true, "result" : "Register succeeded"} or 
		 *         {"success" : false, "result" : "Register failed"}  
		 */
		
		JsonObject myObj = new JsonObject();
		if (result == 1) {
			myObj.addProperty("success", true);
			myObj.addProperty("result", "Register succeeded");
		} else {
			myObj.addProperty("success", false);
			myObj.addProperty("result", "Register failed");
		}
		
		
		out.println(myObj.toString());
		out.close();
	}
	
	private int insertUserInfo(String name, String gender, String mail, String password, String profile) {
		Connection conn = null;            
        PreparedStatement stmt = null;     
        String sql = null;
        int i = 0;
        try {      
            Context ctx = (Context) new InitialContext().lookup("java:comp/env");
            conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection(); 

            sql = "insert into user (userName, gender, mail, password, profile) values (?, ?, ?, ?, ?);"; 
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
			stmt.setString(2, gender);
			stmt.setString(3, mail);
			stmt.setString(4, password);
			stmt.setString(5, profile);
       
			i = stmt.executeUpdate();                                                                          
			
            stmt.close();                                                             
            stmt = null;                                                              
            conn.close();                                                             
            conn = null;                                                   

        }                                                               
        catch(Exception e){
        	System.out.println(e);
        }                      
        finally {                                                       
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

}
