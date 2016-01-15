package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.DBHelper;

public class Test4 {
	public static void main(String[]args) {
//		String email = "2222@22.com";
//		int id = 0;
		Connection conn = null;            
	    PreparedStatement stmt = null;   
	    ResultSet rs = null;
	    String sql = null;
	    
	    try {      
	    	conn = DBHelper.getConnection();
	    	if (conn != null) {
	    		sql = "select * from tip;";
	    		
	            stmt = conn.prepareStatement(sql);
//	            stmt.setString(1, email);
	            rs = stmt.executeQuery(); 
	
	            while(rs.next()){ 
//	                id = rs.getInt(1);
	            	System.out.println(rs.getString("content"));
	            }                   
	    		
	    	}
//	    	System.out.println(id);
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
	}

}
