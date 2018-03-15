package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ifc.ICSet;

public class DBConnection {
	public Connection conn = null;
	public PreparedStatement state = null;
	
	public DBConnection(String sql) {
		try {
			Class.forName(ICSet.DBC_DRIVER);
			conn = DriverManager.getConnection(ICSet.DBC_SOURCE,ICSet.DBC_LOGUSER,ICSet.DBC_LOGPASS);
			if(sql!=null)
				state = conn.prepareStatement(sql);
			else
				state = conn.prepareStatement("", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			this.state.close();
			this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
