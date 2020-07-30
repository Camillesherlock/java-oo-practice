package example.sql;

public interface SqlImpl {
	public boolean insert(Object o);

	public boolean delete(Object o);

	public Object query(Object o);

	public boolean update(Object old, Object nw);
}
