package service;

import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Calendar;

import doa.Configures;
import doa.DBConnection;
import ifc.*;

public class Account implements IData, IIdentity<String> {
	public static final String MD5(String s) {
		if (s == null)
			s = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] b = md.digest(s.getBytes("utf-8"));
			return toHex(b);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static final String toHex(byte[] b) {
		final char[] HEX_DIGITS = "0132456789ABCDEF".toCharArray();
		StringBuilder ret = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			ret.append(HEX_DIGITS[(b[i] >> 4) & 0x0f]);
			ret.append(HEX_DIGITS[b[i] & 0x0f]);
		}
		return ret.toString();
	}

	private int permission;
	private String username, password, log;
	private Timestamp expired;
	private Person owner;

	public Account(String user, String pwd) {
		setID(user);
		setPassword(pwd);
		setOwner(null);
	}

	public Account(String user) {
		this(user, "");
	}

	public Account() {
		this("", "");
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String pwd) {
		password = Account.MD5(pwd);
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person man) {
		owner = man;
	}

	public int getPermission() {
		return permission;
	}

	public void setPermission(int right) {
		permission = right;
	}

	public boolean login() {
		// Had User & Password
		if (username.isEmpty() || password.isEmpty())
			return false;
		try {
			DBConnection db = new DBConnection("select password, expired, log, username from account where username=?",
					true);
			db.state.setString(1, username);
			if (db.state.execute()) {
				ResultSet rs = db.state.getResultSet();
				if (rs.next()) {
					// Verify Password
					if (!password.equals(rs.getString(1))) {
						rs.close();
						db.close();
						return false;
					}
					// Create & update log info and expired time
					Configures cfg = new Configures();
					Calendar ca = Calendar.getInstance();
					ca.add(Calendar.HOUR_OF_DAY, Integer.parseInt(cfg.defaults.getProperty("DEFAULT_EXPIRED")));
					this.expired = new Timestamp(ca.getTimeInMillis());
					rs.updateTimestamp(2, expired);
					log = Account.MD5(expired.toString().concat(password));
					rs.updateString("log", log);
					rs.updateRow();
					rs.close();
					db.close();
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		load();
		return true;
	}

	public void logout() {
		try {
			DBConnection db = new DBConnection("update account set expired=null, log=null where username=?");
			db.state.setString(1, username);
			db.state.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean verify() {
		if (this.log == null || this.log.isEmpty())
			return false;
		try {
			DBConnection db = new DBConnection("select expired from account where username=? and log=?");
			db.state.setString(1, username);
			db.state.setString(2, log);
			ResultSet rs = db.state.executeQuery();
			if (rs.next() && rs.getTimestamp(1).after(new Timestamp(System.currentTimeMillis())))
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String getLog() {
		return log;
	}

	public Timestamp getExpiredTime() {
		return expired;
	}

	@Override
	public void setID(String id) {
		// TODO Auto-generated method stub
		if (id != null)
			username = id;
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean exists(String id) {
		try {
			DBConnection db = new DBConnection("select password from account where username=?");
			db.state.setString(1, username);
			ResultSet rs = db.state.executeQuery();
			if (rs.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		try {
			DBConnection db;
			db = new DBConnection("update account set password=?, pid=? where username=?");
			db.state.setString(1, password);
			if (owner == null)
				db.state.setNull(2, Types.INTEGER);
			else
				db.state.setInt(2, owner.getID());
			db.state.setString(3, username);
			db.state.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		try {
			DBConnection db = new DBConnection("select password, pid, permission from account where username=?");
			db.state.setString(1, username);
			ResultSet rs = db.state.executeQuery();
			if (rs.next()) {
				setPassword(rs.getString("password"));
				setPermission(rs.getInt("permission"));
				if (owner == null || owner.getID() != rs.getInt(2))
					owner = new Person(rs.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert() {
		// TODO Auto-generated method stub
		if (!exists(username)) {
			try {
				DBConnection db;
				db = new DBConnection("insert account (username, password, pid, permission) value (?, ?, ?, ?)");
				db.state.setString(1, username);
				db.state.setString(2, password);
				if (owner == null)
					db.state.setNull(3, Types.INTEGER);
				else
					db.state.setInt(3, owner.getID());
				db.state.setInt(4, permission);
				db.state.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		try {
			DBConnection db;
			db = new DBConnection("delete from account where username=?");
			db.state.setString(1, username);
			db.state.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
