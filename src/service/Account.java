package service;

import java.security.MessageDigest;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import dao.Configures;
import dao.DBConnection;
import ifc.*;

public class Account implements IData, IIdentity<String> {
	public static final String MD5(String s) {
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			byte[] b=md.digest(s.getBytes("utf-8"));
			return toHex(b);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static final String toHex(byte[] b) {
		final char[] HEX_DIGITS="0132456789ABCDEF".toCharArray();
		StringBuilder ret=new StringBuilder(b.length*2);
		for(int i=0;i<b.length;i++) {
			ret.append(HEX_DIGITS[(b[i]>>4)& 0x0f]);
			ret.append(HEX_DIGITS[b[i]& 0x0f]);
		}
		return ret.toString();
	}
	
	private String username,password,log;
	private Person owner;
	
	public Account(String user,String pwd) {
		this(user);
		this.setPassword(pwd);
	}
	
	public Account(String user) {
		this.setID(user);
		this.setOwner(null);
	}
	
	public Account() {
		this(null);
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String pwd) {
		this.password=this.MD5(pwd);
	}
	
	public Person getOwner() {
		return this.owner;
	}
	
	public boolean login() {
		String pwd;
		DBConnection db=new DBConnection(null);
		try {
			if(db.state.execute("select password, log, expired from account where username=?")) {
				ResultSet rs=db.state.getResultSet();
				if(rs.next()) {
					if(this.password.equals(rs.getString(1))) {
						rs.close();
						db.close();
						return false;
					}
					Configures cfg=new Configures();
					Date d=new Date(Long.parseLong(cfg.defaults.getProperty("DEFAULT_EXPIRED"))+System.currentTimeMillis());
					rs.updateDate("expired", d);
					String log;
					log=this.MD5(this.password.concat(d.getDay()))
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.load(true);
		return true;
	}
	
	public void logout() {		
	}

	@Override
	public void save(boolean overwrite) {
		// TODO Auto-generated method stub
		try {
			String sql;
			DBConnection db;
			if(!overwrite) {
				db=new DBConnection("update account set aid=? where username=? and aid is null");
				if(this.owner==null)
					db.state.setNull(1,Types.INTEGER);
				else
					db.state.setInt(1, this.owner.getID());
				db.state.setString(2, this.username);
			} else {
				db=new DBConnection("update account set password=?, aid=? where username=?");
				db.state.setString(1, this.password);
				if(this.owner==null)
					db.state.setNull(2,Types.INTEGER);
				else
					db.state.setInt(2, this.owner.getID());
				db.state.setString(3, this.username);
			}
			db.state.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void load(boolean overwrite) {
		// TODO Auto-generated method stub
		try {
			DBConnection db;
			db=new DBConnection("select password, aid from account where username=?");
			db.state.setString(1, this.username);
			ResultSet rs=db.state.executeQuery();
			if(rs.next()) {
				if(overwrite)
					this.setPassword(rs.getString(1));
				if(this.owner==null||(overwrite&&this.owner.getID()!=rs.getInt(2)))
					this.owner=new Person(rs.getInt(2));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setID(String id) {
		// TODO Auto-generated method stub
		this.username=id;
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return this.username;
	}
}
