package ifc;

/**
 * Sort of formula, like Order, Conditional etc.
 * 
 * @author Jason Chen
 * @version 0.30a
 */
public interface IFormula<T> {
	public String parseOperator(int op);

	public void setOperand(T value);

	public T getOperand();

	public String toString();
}
