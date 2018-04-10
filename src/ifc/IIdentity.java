package ifc;

/**
 * Declared unique identity
 * 
 * @author Administrator
 * @version 0.3a
 * @param <T>
 *            type of Key, string, integer etc.
 */
public interface IIdentity<T> {
	public void setID(T id);

	public T getID();

	public boolean exists(T id);
}
