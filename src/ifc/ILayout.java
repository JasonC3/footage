package ifc;

import java.util.Map;

public interface ILayout {
	public Map<String, Object> getDetail(int index);

	public String[][] getRecord(Map<String, Object> cond, Map<String, String> order);

	public String[] getKey();

	public String[] getTitle();

	public int getCount();

	public void setPageDiv(int div);

	public void setPage(int page);

	public int getPage();
}
