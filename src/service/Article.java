package service;

import java.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import dao.*;
import ifc.*;

public class Article implements IData, ILayout, ISumary<Integer> {
	public static int getIncreasedID(int period) {
		int i=-1;
		try {
			String sql;
			if(period==-1)
				sql=new String("select max(aid) from article;");
			else
				sql=new String("select max(aid) from article where tid=?;");
			DBConnection db=new DBConnection(sql);
			if(period!=-1)
				db.state.setInt(1, period);
			ResultSet rs=db.state.executeQuery();
			rs.next();
			i=rs.getInt(1)+1;
			rs.close();
			db.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	private int id;
	private Author au;
	private Department sent_dept;
	private Status stat;
	private Category cate;
	private String title, fin_title, content;
	private Date wrote, received;
	private Term period, term;
	private TermPage page;
	private boolean void_flag, post_flag;
	private int length;
	private double score;
	private Map<Integer, Person> jury, co_oper;
	private Map<Integer, String> co_opca;
	private Map<Integer, Double> grade;

	public Article() {
		this(getIncreasedID(-1), false);
	}
	
	public Article(int id) {
		this(id, false);
	}
	
	public Article(int id, boolean loading) {
		this.setID(id);
		if(loading)
			this.load(true);
	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id=id;
	}

	public Department getSentDepartment() {
		return sent_dept;
	}

	public void setSentDepartment(Department dept) {
		sent_dept=dept;
	}

	public Author getAuthor() {
		return au;
	}

	public void setAuthor(Author author) {
		au=author;
	}

	public Status getStatus() {
		return stat;
	}

	public void setStatus(Status stat) {
		this.stat=stat;
	}

	public Category getCategory() {
		return cate;
	}

	public void setCategory(Category cate) {
		this.cate=cate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title=title;
	}

	public String getFinalTitle() {
		return fin_title;
	}

	public void setFinalTitle(String title) {
		fin_title=title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String text) {
		content=text;
	}

	public int getSize() {
		length=content.length();
		return length;
	}

	public Date getWroteDate() {
		return wrote;
	}

	public void setWroteDate(Date date) {
		wrote=date;
	}

	public Date getReceivedDate() {
		return received;
	}

	public void setReceivedDate(Date date) {
		received=date;
	}

	public Term getPeriod() {
		return period;
	}

	public void setPeriod(Term term) {
		period=term;
	}

	public Term getTerm() {
		return term;
	}

	public TermPage getPage() {
		return page;
	}

	public boolean isVoid() {
		return void_flag;
	}

	public boolean isPost() {
		return post_flag;
	}

	public double getScore() {
		return score;
	}

	public double[] getGrade() {
		double[] r=new double[grade.size()];
		for(int i=r.length;i>1;i--)
			r[i-1]=grade.get(i);
		return r;		
	}

	public Judge[] getJury() {
		Judge[] j=new Judge[jury.size()];
		for(int i=j.length;i>1;i--)
			j[i-1]=(Judge)jury.get(i);
		return j;
	}

	public Author getCooperator() {
		
	}

	public String getCooperateCause() {
		
	}

	public int getCooperationTimes() {
		return co_oper.size();
	}
	
	public void addCooperation(Author opertor, String cause) {
		
	}
	
	public void removeCooperation(int rank) {
		
	}

	public void judge(Judge man, double grade) {
		
	}
	
	public void judge(Judge[] man, double[] grade) {
		
	}

	public void computeScore() {
		
	}

	public void postAt() {
		
	}
	
	public void disable() {
		String sql=new String("update article set isVoid=true where AID=?;");
		try {
			DBConnection db=new DBConnection(sql);
			db.state.setInt(1, id);
			db.state.executeUpdate();
			db.close();
			void_flag=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void save(boolean overwrite) {
		// TODO Auto-generated method stub
	}

	@Override
	public void load(boolean overwrite) {
		// TODO Auto-generated method stub
		try {
			String sql=new String("select SID, GID, DID, PID, Title, DateWrote, DateReceived, Score, TID, Content, Length, IsVoid, IsPost, PostTID, PageTID, PostTitle from article where AID=?;");
			DBConnection db=new DBConnection(sql);
			db.state.setInt(1, this.id);
			if(db.state.execute()) {
				ResultSet rs=db.state.getResultSet();
				int i=rs.getInt(1);
				Configures cfg=new Configures();
				if(this.stat==null)
					this.stat=new Status(i,true);
				else if(this.stat.getID()!=i&&overwrite)
					this.stat.setID(i,true);
				else
					this.stat.setID(cfg.defaults.getProperty("STATUS"),true);
				rs.close();
				db.close();
			}
			else {
				db.close();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Integer getSum(Map<String, Object> cond) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ILayout[] listDetail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getDetail(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getRecord(Map<String, Object> cond, Map<String, String> order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getColumn() {
		// TODO Auto-generated method stub
		return null;
	}
}
