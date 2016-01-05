package servlet;

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

import com.google.gson.JsonObject;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Login() {
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
		 * ÓÃ»§µÇÂ¼×Ö¶Î: userName, password
		 */
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		JsonObject myObj = new JsonObject();
		boolean confirm = confirmLogin(userName, password);
		if(confirm) {
			myObj.addProperty("result", true);
			HttpSession session = request.getSession();
			session.setAttribute("userName", userName);
			session.setAttribute("password", password);
			String sessionID = session.getId();
			myObj.addProperty("sessionId", sessionID);
			myObj.addProperty("msg", "Login succeeded");
		} else {
			myObj.addProperty("result", false);
			myObj.addProperty("msg", "Login failed");
		}
		out.println(myObj.toString());
		out.close();
	}
	
	private boolean confirmLogin(String name, String password) {
		Connection conn = null;            
        PreparedStatement stmt = null;     
        String sql = null;
        try {      
            Context ctx = (Context) new InitialContext().lookup("java:comp/env");
            conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection(); 
            
            sql = "select userName, password from user;";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(); 
            while(rs.next()){ 
            	if (rs.getString(1).equals(name) && rs.getString(2).equals(password)) {
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

}
