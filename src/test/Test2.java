package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import util.DBHelper;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import entity.User;

public class Test2 {
	
	private static ArrayList<User> getUsers() {
		User user = new User();
		ArrayList<User> userList = new ArrayList<User>();
		Connection conn = null;            
        PreparedStatement stmt = null;   
        ResultSet rs = null;
        String sql = null;
        
        try {      
//            Context ctx = (Context) new InitialContext().lookup("java:comp/env");
//            conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection(); 
        	
        	conn = DBHelper.getConnection();
        	if (conn != null) {
        		sql = "select * from user;"; 
                stmt = conn.prepareStatement(sql);
                rs = stmt.executeQuery(); 

                while(rs.next()){ 
                    user.setUserName(rs.getString("userName").trim());
                    user.setGender(rs.getString("gender").trim());
                    user.setEmail(rs.getString("email").trim());
                    user.setPassword(rs.getString("password").trim());
                    user.setProfile("aaa");
                    userList.add(user);
                }                   
        		
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
	
	public static void main(String[]args) {
		ArrayList<User> users = getUsers();
		
		Gson gson = new Gson();
		JsonObject myObj = new JsonObject();
		if (users.size() >0 ) {
			myObj.addProperty("success", true);
		} else {
			myObj.addProperty("success", false);
		}
		int flag = 1;
		String data = null;
		for(User u : users) {
			JsonElement userObj = gson.toJsonTree(u);
			data = "user" + flag;
			myObj.add(data, userObj);
			flag += 1;
		}
		
		System.out.println(myObj.toString());
	}
}
