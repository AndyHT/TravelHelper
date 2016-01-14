package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

import org.json.JSONException;
import org.json.JSONObject;

import util.MySessionContext;

import com.google.gson.JsonObject;

@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get request...");
		
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
		 * ÓÃ»§µÇÂ¼×Ö¶Î: userName, password
		 */
		String email = null;
		String password = null;
		
		String jsonStr = readJSONString(request);
		JSONObject obj = null;
		System.out.println(jsonStr);
		try {
			obj = new JSONObject(jsonStr);
			email = obj.getString("email");
			password = obj.getString("password");
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		JsonObject myObj = new JsonObject();
//		out.print("receive....");
		boolean confirm = confirmLogin(email, password);
		if(confirm) {
			myObj.addProperty("result", true);
			HttpSession session = request.getSession();
			
			session.setAttribute("email", email);
			MySessionContext.AddSession(session);
			
			String sessionID = session.getId();
//			String sessionID = "111";
			myObj.addProperty("sessionID", sessionID);
		} else {
			myObj.addProperty("result", false);
		}
		out.println(myObj.toString());
		out.close();
	}
	
	private boolean confirmLogin(String email, String password) {
		Connection conn = null;            
        PreparedStatement stmt = null;     
        String sql = null;
        try {      
            Context ctx = (Context) new InitialContext().lookup("java:comp/env");
            conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection(); 
            
            sql = "select email, password from user;";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(); 
            while(rs.next()){ 
            	if (rs.getString(1).equals(email) && rs.getString(2).equals(password)) {
            		return true;
            	} 
            }
        } catch(Exception e){
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
        
        return false;

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
