package ifc;

import java.util.Map;

public interface ISumary<T> {
	public T getSum(Map<String, Object> cond);

	public ILayout listDetail();
}
