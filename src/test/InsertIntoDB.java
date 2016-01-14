package test;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DBHelper;

public class InsertIntoDB {

	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		int i = 0;
		try {
			conn = DBHelper.getConnection();
			if (conn != null) {
//				String sql1 = "insert into user (gender, password, mail, profile, userName) values (?, ?, ?, ?, ?);";
//				stmt = conn.prepareStatement(sql1);
//				stmt.setString(1, "female");
//				stmt.setString(2, "222");
//				stmt.setString(3, "222@22.com");
//				stmt.setString(4, null);
//				stmt.setString(5, "Jery");
				
//				i = stmt.executeUpdate();
//				System.out.println(i);
				
				ArrayList<String> nameList = new ArrayList<String>();
		        ArrayList<String> passList = new ArrayList<String>();
				String sql = "select userName, password from user;";
	            stmt = conn.prepareStatement(sql);
	            ResultSet rs = stmt.executeQuery(); 
	            while(rs.next()) {
	            	if (rs.getString(1).equals("Jery") && rs.getString(2).equals("2223")) {
	            		System.out.println("find it!");
	            		return;
	            	} else {
	            		System.out.println("no such record!");
	            		return;
	            	}
	            }
	            System.out.println("aa");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt.close();
		}
		

	}

}
