package com.myCache.daoImpl;

import java.util.ArrayList;
import java.util.List;

import com.myCache.dao.IBaseDAO;
/**
 * 
 * @author Robin
 *
 * @param <T>
 * 
 */
public class BaseDAOImpl<T> implements IBaseDAO<T>{

	public List<T> objects = new ArrayList<T>();
	
	@Override
	public List<T> getAll() {
		return objects;
	}

	@Override
	public boolean add(T t) {
		if (t != null && !objects.contains(t))
            objects.add(t);

        return objects.contains(t);
	}

	@Override
	public boolean update(T t) {
		return false;
	}

	@Override
	public boolean delete(T t) {
		return objects.remove(t);
	}

	@Override
	public T get(int id) {
		//dummy implementation.
		return objects.get(id);
	}

}
