package com.myCache;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

/**
 * 
 * @author Robin
 *
 */
public final class CacheMgr {
	private static Logger logger = Logger.getLogger(CacheMgr.class);
	private static Map<String, ICacheable> tableToCacheMap = new ConcurrentHashMap<String, ICacheable>();

	/**
	 * Method to initializeCache It loads all the persistent objects from
	 * database.
	 */
	public final static void initializeCache() {
		StringBuilder buf = new StringBuilder();
		if (buf != null) {
			buf.append("CacheMgr.initializeCache()").append("\r\n");
		}

		for (Class<?> c : DataToBeCachedUtil.getDatatobecached()) {
			try {
				if (buf != null) {
					buf.append("Cache Package" + c.getName());
				}
				ICacheable instance = (ICacheable) c.newInstance();
				instance.reload();
				tableToCacheMap.put(instance.getDBTableName(), instance);
			} catch (Exception e) {
				logger.error("Exception", e);
			} finally {
				if (buf != null) {
					logger.info(buf.toString());
				}
			}
		}
	}

	/**
	 * Read the object from the cache.If not present will read from the database
	 * 
	 * @param tableName
	 * @param key
	 * @return
	 */
	public final static Object loadDataRead(String tableName, String key) {

		Object result = null;
		try {
			result = getObjectFromCache(tableName, key, false);
		} catch (EntityNotFoundException e) {
			logger.error("Data not found in cache for tableName:" + tableName + " key:" + key);
		}

		if (result == null) {
			// Need to query the data from DB,may be cache is not updated

			// We can then call reload/update functionality to update the cache
			// for that respective table
		}
		return result;
	}

	/**
	 * This method should be used when the client needs a modifiable reference
	 * 
	 * @param tableName
	 * @param key
	 * @return
	 */
	public final static Object loadDataWrite(String tableName, String key) {

		Object result = null;
		try {
			result = getObjectFromCache(tableName, key, true);
		} catch (EntityNotFoundException e) {
			logger.error("Data not found in cache for tableName:" + tableName + " key:" + key);
		}

		if (result == null) {
			// Need to query the data from DB,may be cache is not updated

			// We can then call reload/update functionality to update the cache
			// for that respective table
		}
		return result;
	}

	/**
	 * Getting data from custom implemented Cache
	 * 
	 * @param tableName
	 * @param key
	 * @param deepCopy indicates if a deep copy of the object should be returned
     *                   instead of a reference
	 * @return
	 * @throws EntityNotFoundException
	 */
	public final static Object getObjectFromCache(String tableName, String key, Boolean deepCopy)
			throws EntityNotFoundException {

		if (tableToCacheMap != null && tableToCacheMap.size() > 0) {
			StringBuilder valueBuf = new StringBuilder();
			StringBuilder buf = null;

			if (logger.isDebugEnabled()) {
				buf = new StringBuilder(32);
				valueBuf.append("getObjectFromCache:" + "\r\n");
				valueBuf.append("tableName" + tableName + "\r\n");
				valueBuf.append("key" + key + "\r\n");
			}

			ICacheable instance = tableToCacheMap.get(tableName);
			if (instance != null) {
				List<?> theList = null;

				if (deepCopy) {
					theList = instance.queryTableDeepCopy(instance.getPrimaryKeyName(), key);
				} else {
					theList = instance.queryTable(instance.getPrimaryKeyName(), key);
				}
				if ((theList == null) || theList.isEmpty() || (theList.size() > 1)) {
					if (buf != null) {
						valueBuf.append("Entry is not found in cache");
						logger.debug(buf.toString());
					}

					throw new EntityNotFoundException("Data not found in DB cache.Query the table:" + tableName);
				} else {
					if (buf != null) {
						valueBuf.append("dbRec" + theList.get(0).toString());
						logger.debug(buf.toString());
					}

					return theList.get(0);
				}
			}
		}

		return null;
	}

	public static void wipeCache() {
		if (tableToCacheMap != null) {
			tableToCacheMap.clear();
		}
	}
}
