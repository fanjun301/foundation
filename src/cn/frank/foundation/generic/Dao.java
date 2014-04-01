package cn.frank.foundation.generic;

public interface Dao<T> {

	void add(T t);
	void update(T t);
	void delete(T t);
	T queryById(Long id);
}
