package com.myCache;

import com.myCache.dataObjects.Country;
/**
 * 
 * @author Robin
 *
 */
public class Main {

	public static void main(String[] args) {
	
		CacheMgr.initializeCache();

		try {
			Country c = (Country)CacheMgr.getObjectFromCache("REF_COUNTRY", "1", false);
			System.out.println("value:"+c.getCountryName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
