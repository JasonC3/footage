package ifc;

/**
 * Single row of data
 * 
 * @author Administrator
 * @version 0.30a
 * @param <K>
 *            type of Key, string, integer etc.
 */
public interface IRecord<K> extends IIdentity<K>, IData {
	public ICell<?> getCell(int index);

	public ICell<?> getCell(String key);

	public void setCell(ICell<?> cell);
}
