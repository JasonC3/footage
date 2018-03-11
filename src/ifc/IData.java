package ifc;

public interface IData {
	public int save(boolean overwrite);
	public int load(boolean overwrite);
	public boolean format();
}
