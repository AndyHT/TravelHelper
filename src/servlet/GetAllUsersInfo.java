package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.jws.WebService;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import entity.User;

@WebServlet("/GetAllUsesrInfo")
public class GetAllUsersInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public GetAllUsersInfo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setHeader("Access-Control-Max-Age", "86400");

		PrintWriter out = response.getWriter();
		
		JsonArray jsonArr = new JsonArray();
		JsonObject obj = new JsonObject();
		
		ArrayList<User> users = getUsers();
		if (users.size() >0 ) {
			for(User u : users) {
				JsonObject myObj = new JsonObject();
				myObj.addProperty("userName", u.getUserName());
				myObj.addProperty("gender", u.getGender());
				myObj.addProperty("email", u.getEmail());
				myObj.addProperty("profile", u.getProfile());
				myObj.addProperty("registerDate", u.getRegisterDate());
				jsonArr.add(myObj);
			}
			obj.add("user", jsonArr);
		} else {
			obj.addProperty("result", "None");
		}

		out.println(obj);
		out.close();
	}
	
	private ArrayList<User> getUsers() {
		
		ArrayList<User> userList = new ArrayList<User>();
		Connection conn = null;            
        PreparedStatement stmt = null;     
        String sql = null;
        
        try {      
            Context ctx = (Context) new InitialContext().lookup("java:comp/env");
            conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection(); 

            sql = "select * from user;"; 
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(); 

            while(rs.next()){ 
            	User user = new User();
                user.setUserName(rs.getString("userName").trim());
                user.setGender(rs.getString("gender").trim());
                user.setEmail(rs.getString("email").trim());
                user.setRegisterDate(rs.getString("registerDate").trim());
                user.setProfile("aaa");
                userList.add(user);
            }                      
            rs.close();                                                               
            stmt.close();                                                             
            stmt = null;                                                              


            conn.close();                                                             
            conn = null;                                                   

        }                                                               
        catch(Exception e){System.out.println(e);}                      

        finally {                                                       
 
            if (stmt != null) {                                            
                try {                                                         
                    stmt.close();                                                
                } catch (SQLException sqlex) {                                
                    // ignore -- as we can't do anything about it here           
                }                                                             

                stmt = null;                                            
            }                                                        

            if (conn != null) {                                      
                try {                                                   
                    conn.close();                                          
                } catch (SQLException sqlex) {                          
                    // ignore -- as we can't do anything about it here     
                }                                                       

                conn = null;                                            
            }                                                        
        }              

        return userList;

	}
}
