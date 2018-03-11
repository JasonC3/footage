package ifc;

public interface ILayout {
	public Object getDetail(int index);
	public Object getRecord(int[] condid, Object[] condition, int[] orderid);
	public String[] getColumn(KeyID key);
}
