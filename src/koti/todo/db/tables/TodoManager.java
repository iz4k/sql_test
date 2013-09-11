package koti.todo.db.tables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import koti.todo.db.beans.Todo;

public class TodoManager {

	public static String allRows() {
		
		try (
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://188.121.44.140/stuffyouwanttodo", "stuffyouwanttodo", "th1sissqL!");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM todo");
				) {
			StringBuffer buf = new StringBuffer();
			while (rs.next()) {
				
				buf.append(rs.getString("name") + ": ");
				buf.append(rs.getString("thing") + ", ");
				buf.append(rs.getInt("done"));
				buf.append("\n");
				//System.out.println(buf.toString());
				
			}
			return buf.toString();
		} catch (Exception e) {
			System.err.println(e);
		}
		return null;
	}
	
	public static String allRows(String name) {
		
		String sql = "SELECT * FROM todo WHERE name = ? OR thing = ?";
		ResultSet rs = null;
		try (
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://188.121.44.140/stuffyouwanttodo", "stuffyouwanttodo", "th1sissqL!");
				PreparedStatement stmt = conn.prepareStatement(sql)
				
				) {
			stmt.setString(1, name);
			stmt.setString(2, name);
			rs = stmt.executeQuery();
			StringBuffer buf = new StringBuffer();
			while (rs.next()) {
				
				buf.append(rs.getString("name") + ": ");
				buf.append(rs.getString("thing") + ", ");
				buf.append(rs.getInt("done"));
				buf.append("\n");
			}return buf.toString();
		} catch (Exception e) {
			System.err.println(e);
		}return null;
	}
	
	public static boolean getBean(Todo bean) throws SQLException {

		String sql = "SELECT * FROM todo WHERE name = ? AND thing = ?";
		ResultSet rs = null;

		try (
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://188.121.44.140/stuffyouwanttodo", "stuffyouwanttodo", "th1sissqL!");
				PreparedStatement stmt = conn.prepareStatement(sql);
				){
			stmt.setString(1, bean.getName());
			stmt.setString(2, bean.getThing());
			rs = stmt.executeQuery();

			if (rs.next()) {
				/*Todo bean = new Todo();
				bean.setName(name);
				bean.setThing(thing);
				bean.setDone(rs.getInt("done"));*/
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			System.err.println(e);
			return false;
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
	}
	
	public static boolean insert(Todo bean) {
		
		String sql = "INSERT into todo (name, thing, done) VALUES (?, ?, 0)";
		
		try (
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://188.121.44.140/stuffyouwanttodo", "stuffyouwanttodo", "th1sissqL!");
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				
				) {
			
			stmt.setString(1, bean.getName());
			stmt.setString(2, bean.getThing());
			stmt.executeUpdate();
			
			
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
				
	}

	public static boolean update(Todo bean) throws SQLException {

		String sql =
				"UPDATE todo SET done = ? WHERE name = ? AND thing = ?";
		
		try (
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://188.121.44.140/stuffyouwanttodo", "stuffyouwanttodo", "th1sissqL!");
				PreparedStatement stmt = conn.prepareStatement(sql);
				){
			
			stmt.setString(2, bean.getName());
			stmt.setString(3, bean.getThing());
			stmt.setInt(1, bean.getDone());
			int success = stmt.executeUpdate();
			
			if (success==1) {
				
				return true;
			} else {
				return false;
			}
			
		}
		catch(SQLException e) {
			System.err.println(e);
			return false;
		}

	}

}
