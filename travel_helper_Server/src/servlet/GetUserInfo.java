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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import dao.UserDAO;
import entity.User;

@WebServlet("/GetUserInfo")
public class GetUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetUserInfo() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String userid = request.getParameter("userId");
		int id = Integer.parseInt(userid);
		PrintWriter out = response.getWriter();

		response.setContentType("text/html");
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setHeader("Access-Control-Max-Age", "86400");

		Gson gson = new Gson();
		JsonObject myObj = new JsonObject();
		
		User user = getInfo(id);
		JsonElement userObj = gson.toJsonTree(user);
		
		try {
			if (user.getUserName() == null) {
				myObj.addProperty("success", false);
			} else {
				myObj.addProperty("success", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		myObj.add("user", userObj);
		
		out.println(myObj.toString());
		out.close();
	}
	
	private User getInfo(int id) {

		User user = new User();
        Connection conn = null;            
        PreparedStatement stmt = null;     
        String sql = null;

        try {      
            Context ctx = (Context) new InitialContext().lookup("java:comp/env");
            conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection(); 

            sql = "select * from user where user_id = ?"; 
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery(); 

            while(rs.next()){ 
                user.setUserName(rs.getString("userName").trim());
                user.setGender(rs.getString("gender").trim());
                user.setMail(rs.getString("mail").trim());
                user.setPassword(rs.getString("password").trim());
                user.setProfile(rs.getString("profile").trim());
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

        return user;

    }   

}
