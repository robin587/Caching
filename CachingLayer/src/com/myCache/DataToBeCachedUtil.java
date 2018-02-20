package com.myCache;

/**
 * 
 * @author Robin
 *
 */
public final class DataToBeCachedUtil {

	/**
	 * Used by CacheMgr to retrieve all CacheObjects
	 * If new cache objects are required then add the 
	 * same to dataToBeCached
	 */
	private final static Class<?>[] dataToBeCached = {
			com.myCache.CountryQueryCache.class,
			com.myCache.CurrencyQueryCache.class,
			com.myCache.HolidayQueryCache.class
	};

	public static Class<?>[] getDatatobecached() {
		return dataToBeCached;
	}
}
