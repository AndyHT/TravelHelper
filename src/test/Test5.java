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
import entity.Bill;

public class Test5 {
	
//	public static void main(String[]args) {
//		Test5 test = new Test5();
//		ArrayList<Bill> bill = test.GetFlag(1, "bill");
//		System.out.println(bill.size());
//	}
//	
	public static void main(String[]args) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
		ResultSet rs = null;
		try {
//			Context ctx = (Context) new InitialContext()
//					.lookup("java:comp/env");
//			conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection();
			conn = DBHelper.getConnection();
//			if (dataType == "bill") {
				ArrayList<Bill> billList = new ArrayList<Bill>();
				sql = "select * from bill where flag = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, 1);
				
				rs = stmt.executeQuery();
				while (rs.next()) {
					Bill bill = new Bill();
					bill.setValue(rs.getDouble("value"));
					bill.setBill_description(rs.getString("bill_description"));
					bill.setBill_type(rs.getString("bill_type"));
					bill.setBill_time(rs.getString("bill_time"));
					billList.add(bill);
				}
				System.out.println(billList.get(0).toString());
//				return billList;
//			}
//			sql = "select userID from user where email = ?;";
//			stmt = conn.prepareStatement(sql);
//			stmt.setString(1, email);
//
//			while (rs.next()) {
//				id = rs.getInt(1);
//			}
//
//			rs = stmt.executeQuery();

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
//		return null;
	}
	
	
}
