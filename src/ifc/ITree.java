package ifc;

public interface ITree<T> {
	public T getChild(int index);

	public void addChild(int index, T node);

	public void removeChild(int index);

	public ILayout listChild();
}
