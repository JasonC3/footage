package ifc;

/**
 * Extend for the <code>IList</code>, divide several pages, also can manipulate
 * the page cursor.
 * 
 * @author Jason Chen
 * @version 0.30a
 * @param <K>
 *            type of Key, string, integer etc.
 */
public interface IPage<K> extends IList<K> {
	public void divide(int div);

	public int getDivide();

	public int getMaxPage();

	public int page();

	public void nextPage();

	public void previousPage();

	public void absolutePage(int page);
}
