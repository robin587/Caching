package com.myCache;

/**
 * 
 * @author Robin
 *
 */
public final class DataToBeCachedUtil {

	private final static Class<?>[] dataToBeCached = {
			com.myCache.CountryQueryCache.class,
			com.myCache.CurrencyQueryCache.class
	};

	public static Class<?>[] getDatatobecached() {
		return dataToBeCached;
	}
}
