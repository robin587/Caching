package com.myCache;

import java.util.List;

/**
 * 
 * @author Robin
 *
 */
public interface ICacheable {

	public String getDBTableName();
	public String getPrimaryKeyName();
	public void reload();
	public List<Object> queryTable(String index,String value);
	public List<Object> queryTableDeepCopy(String index,String value);
	public void addObject(String column, String value);
	public void deleteObject(String column, String value);
	public void updateObject(String column, String value);
	
}
