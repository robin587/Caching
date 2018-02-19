package com.myCache;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.log4j.Logger;

/**
 * 
 * @author Robin
 *
 */
public abstract class QueryCache implements ICacheable {

	private static Logger logger = Logger.getLogger(QueryCache.class);
	protected String tableName;
	protected HashMap<String, ConcurrentHashMap<String, List<Object>>> cachedData = new HashMap<String, ConcurrentHashMap<String, List<Object>>>(
			10);
	
	public final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	public final Lock read = readWriteLock.readLock();
	public final Lock write = readWriteLock.writeLock();

	public void initializeCacheData(String cacheKey) {
		ConcurrentHashMap<String, List<Object>> data = new ConcurrentHashMap<String, List<Object>>(
				getTableSize());
		cachedData.put(cacheKey, data);
	}

	public QueryCache(){}
	public QueryCache(String tableName,String cacheKey){
		super();
		this.tableName=tableName;
		initializeCacheData(cacheKey);
	}
	
	/**
	 * Returns a list of persistent objects that have the given 'value' for the
	 * specified pKey.
	 * 
	 * @param pKey
	 *            - column name in the database
	 * @param value
	 *            - value within the column.
	 * @return
	 */
	public List<Object> queryTable(String pKey, String value) {
		List<Object> objList = null;
		try {
			read.lock();
			if (cachedData.get(pKey).size() > 0) {
				objList = (List<Object>) cachedData.get(pKey).get(value);
			}
		} catch (Exception e) {
			logger.error("queryTable error for tableName:" + getDBTableName() + " PK:" + pKey + " value:" + value);
			return null;
		} finally {
			read.unlock();
		}
		return objList;
	}

	 /**
	    * Causes the current cache contents for the table to be indexed based
	    * on the given columns.
	    */
	protected abstract void populateCachedData(List<Object> dataList);
	      
	 /**
	   * Re populates the tables based on current persistence state.
	   */
	public abstract void reload();
	
	protected abstract int getTableSize();

	public abstract void deleteObject(String column, String value);

	public abstract void updateObject(String column, String value);

	public abstract void addObject(String column, String value);
	
}
