package service;

import java.util.*;

import ifc.*;

public class Person implements IIdentity<Integer>, ILayout, ITree<Article>, IData {
	private Map<Integer, Article> arti;
	
	public Person() {
		
	}
	
	public Person(int index) {
		this.setID(index);
		this.load(false);
	}

	@Override
	public void setID(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Article getChild(int index) {
		// TODO Auto-generated method stub
		return (Article)arti.get(index);
	}

	@Override
	public void addChild(int index, Article node) {
		// TODO Auto-generated method stub
		arti.put(index, node);
	}

	@Override
	public void removeChild(int index) {
		// TODO Auto-generated method stub
		arti.remove(index);
	}

	@Override
	public ILayout listChild() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(boolean overwrite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load(boolean overwrite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Object> getDetail(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[][] getRecord(Map<String, Object> cond, Map<String, String> order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPageDiv(int div) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPage(int page) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPage() {
		// TODO Auto-generated method stub
		return 0;
	}
}
