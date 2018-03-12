package ifc;

import java.util.Map;

public interface ILayout {
	public String[] getDetail(int index);
	public String[] getRecord(Map<String, Object> cond, Map<String, String> order);
	public String[] getColumn();
}
