package ifc;

/**
 * Tiny datum unit
 * 
 * @author Jason Chen
 * @version 0.3a
 * @param <T>
 */
public interface ICell<T> {
	public static final int TYPE_INT = 0;
	public static final int TYPE_TEXT = 1;
	public static final int TYPE_DATE = 2;
	public static final int TYPE_TIME = 3;
	public static final int TYPE_TIMESTAMP = 4;
	public static final int TYPE_DECIMAL = 5;
	public static final int TYPE_FLOAT = 6;

	public void setKey(String key);

	public String getKey();

	public void setContent(T value);

	public T getContent();

	public void setType(int type);

	public int getType();

	public String getTypeNane();
}
