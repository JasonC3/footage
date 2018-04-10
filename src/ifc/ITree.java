package ifc;

/**
 * One parent and several children, which has unique key
 * 
 * @author Jason Chen
 * @version 0.30a
 * @param <K>
 *            type of Key, string, integer etc.
 */
public interface ITree<K> extends IIdentity<K> {
	public ITree<K> getChild(int index);

	/**
	 * append the child
	 * 
	 * @param node
	 * @param after
	 *            while negative number means backwards.
	 */
	public void addChild(ITree<K> node, int after);

	public void removeChild(int index);

	public IList<K> listChild();

	public IRecord<K> getRow();
}
