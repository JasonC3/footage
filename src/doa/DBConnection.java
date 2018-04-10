package doa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ifc.ICSet;

public final class DBConnection {
	public Connection conn = null;
	public PreparedStatement state = null;

	public DBConnection(String sql) {
		this(sql, false);
	}

	public DBConnection(String sql, boolean update) {
		try {
			Class.forName(ICSet.DBC_DRIVER);
			conn = DriverManager.getConnection(ICSet.DBC_SOURCE, ICSet.DBC_LOGUSER, ICSet.DBC_LOGPASS);
			if (sql == null)
				this.setQuery("",update);
			else
				this.setQuery(sql,update);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setQuery(String sql, boolean update) {
		try {
			if(this.state!=null&&!this.state.isClosed())
			this.state.close();
			if (update)
				this.state = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			else
				this.state = conn.prepareStatement(sql);
		} catch(SQLException e) {
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
