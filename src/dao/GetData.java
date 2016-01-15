package dao;

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
import entity.Item;
import entity.Note;
import entity.Schedule;
import entity.Tip;

public class GetData {
	public ArrayList<Bill> GetBill(int id) {
		ArrayList<Bill> billList = new ArrayList<Bill>();
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
		ResultSet rs = null;
		try {
			// conn = DBHelper.getConnection();
			Context ctx = (Context) new InitialContext()
					.lookup("java:comp/env");
			conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection();

			sql = "select * from bill where flag = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			rs = stmt.executeQuery();
			while (rs.next()) {
				Bill bill = new Bill();
				bill.setBill_id(rs.getInt("bill_id"));
				bill.setValue(rs.getDouble("value"));
				bill.setBill_description(rs.getString("bill_description"));
				bill.setBill_type(rs.getString("bill_type"));
				bill.setBill_time(rs.getString("bill_time"));
				billList.add(bill);
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
		return billList;
	}

	public ArrayList<Schedule> GetSchedule(int id) {
		ArrayList<Schedule> scheduleList = new ArrayList<Schedule>();
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
		ResultSet rs = null;
		try {
			// conn = DBHelper.getConnection();
			Context ctx = (Context) new InitialContext()
					.lookup("java:comp/env");
			conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection();
			sql = "select * from schedule where flag = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			rs = stmt.executeQuery();
			while (rs.next()) {
				Schedule schedule = new Schedule();
				schedule.setSchedule_id(rs.getInt("schedule_id"));
				schedule.setDestination(rs.getString("destination"));
				schedule.setStrat_date(rs.getString("start_date"));
				schedule.setEnd_date(rs.getString("end_date"));
				schedule.setLatitude(rs.getDouble("latitude"));
				schedule.setLongitude(rs.getDouble("longitude"));
				schedule.setPicture(rs.getString("picture"));
				scheduleList.add(schedule);
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
		return scheduleList;
	}

	public ArrayList<Note> GetNote(int id) {
		ArrayList<Note> noteList = new ArrayList<Note>();
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
		ResultSet rs = null;
		try {
			// conn = DBHelper.getConnection();
			Context ctx = (Context) new InitialContext()
					.lookup("java:comp/env");
			conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection();
			sql = "select * from note where flag = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			rs = stmt.executeQuery();
			while (rs.next()) {
				Note note = new Note();
				note.setNote_id(rs.getInt("note+id"));
				note.setContent(rs.getString("content"));
				note.setTime(rs.getString("time"));
				noteList.add(note);
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
		return noteList;
	}

	public ArrayList<Item> GetItem(int id) {
		ArrayList<Item> itemList = new ArrayList<Item>();
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
		ResultSet rs = null;
		try {
			// conn = DBHelper.getConnection();
			Context ctx = (Context) new InitialContext()
					.lookup("java:comp/env");
			conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection();
			sql = "select * from item where flag = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			rs = stmt.executeQuery();
			while (rs.next()) {
				Item item = new Item();
				item.setItem_id(rs.getInt("item_id"));
				item.setItem_description(rs.getString("item_description"));
				item.setItem_name(rs.getString("item_name"));
				item.setItem_num(rs.getInt("item_num"));
				itemList.add(item);
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
		return itemList;
	}

	public ArrayList<Tip> GetTip(String destination) {
		ArrayList<Tip> tipList = new ArrayList<Tip>();
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
		ResultSet rs = null;
		try {
			// conn = DBHelper.getConnection();
			Context ctx = (Context) new InitialContext()
					.lookup("java:comp/env");
			conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection();
			sql = "select * from tip where destination = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, destination);

			rs = stmt.executeQuery();
			while (rs.next()) {
				Tip tip = new Tip();
				tip.setId(rs.getInt("id"));
				tip.setContent(rs.getString("content"));
				tip.setType(rs.getString("type"));
				tip.setDestination(rs.getString("destination"));
				tip.setTime(rs.getString("time"));
				tip.setPicture(rs.getString("picture"));
				tip.setTitle(rs.getString("title"));
				tipList.add(tip);
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
		return tipList;
	}

	public ArrayList<Tip> GetAllTips() {
		ArrayList<Tip> tipList = new ArrayList<Tip>();
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
		ResultSet rs = null;
		try {
			// conn = DBHelper.getConnection();
			Context ctx = (Context) new InitialContext()
					.lookup("java:comp/env");
			conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection();
			sql = "select * from tip;";
			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();
			while (rs.next()) {
				Tip tip = new Tip();
				tip.setId(rs.getInt("id"));
				tip.setContent(rs.getString("content"));
				tip.setType(rs.getString("type"));
				tip.setDestination(rs.getString("destination"));
				tip.setTime(rs.getString("time"));
				tip.setPicture(rs.getString("picture"));
				tip.setTitle(rs.getString("title"));
				tipList.add(tip);
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
		return tipList;
	}
}
