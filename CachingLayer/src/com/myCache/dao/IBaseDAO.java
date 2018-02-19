package com.myCache.dao;

import java.util.List;
/**
 * 
 * @author Robin
 *
 * @param <T>
 */
public interface IBaseDAO<T> {

	public List<T> getAll();
	public boolean add(T t);
	public boolean update(T t);
	public boolean delete(T t);
	public T get(int id);
	
}
