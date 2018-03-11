package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	public static final String url = "jdbc:mysql://localhost/footage?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "webaccess";
	public static final String password = "SKItys*%275*#$";
	
	public Connection conn = null;
	public PreparedStatement state = null;
	
	public DBConnection(String sql) {
		try {
			Class.forName(name);
			conn = DriverManager.getConnection(url,user,password);
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
