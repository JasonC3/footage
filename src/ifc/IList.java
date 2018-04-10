package ifc;

/**
 * Two-dimension table, including title row, be able to sort by orders.
 * 
 * @author Jason Chen
 * @version 0.30a
 * @param <K>
 *            type of Key, string, integer etc.
 */
public interface IList<K> {
	public IRecord<K> getTitle();

	public void setTitle(IRecord<K> title);

	public int height();

	public int width();

	public IRecord<K> getRow(int index);

	public IRecord<K> getRowById(K key);

	public void insertRow(IRecord<K> row, int after);

	public void updateRow(IRecord<K> row, int index);

	public void deleteRow(int index);

	public void deleteRowById(K key);
}
