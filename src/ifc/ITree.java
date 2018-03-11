package ifc;

public interface ITree {
	public Object[] getChild();
	public Object getParent();
	public boolean addChild(Object[] nodes, int[] index);
	public boolean deleteChild(int[] index);
	public Object listChild();
}
