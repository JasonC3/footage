package ifc;

public interface ITree<T> {
	public T[] getChild();
	public T getParent();
	public void addChild(int index, T node);
	public void removeChild(int index);
	public ILayout[] listChild();
}
