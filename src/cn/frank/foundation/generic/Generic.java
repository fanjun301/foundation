package cn.frank.foundation.generic;

import java.lang.reflect.ParameterizedType;

public class Generic<T> {
	
	public Class<T> c;

	@SuppressWarnings("unchecked")
	public Generic() {
		super();
		c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]; 
	}
	
	
}
