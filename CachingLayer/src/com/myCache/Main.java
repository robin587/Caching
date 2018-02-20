package com.myCache;

import org.apache.log4j.Logger;

import com.myCache.dataObjects.Country;
import com.myCache.dataObjects.Holiday;
/**
 * 
 * @author Robin
 *
 */
public class Main {

	private static Logger logger = Logger.getLogger(Main.class);
	public static void main(String[] args) {

		
		CacheMgr.initializeCache();

		try {
			Country c = (Country)CacheMgr.loadDataRead("REF_COUNTRY", "1");
			System.out.println("value:"+c.getCountryName());
			
			Holiday h = (Holiday)CacheMgr.loadDataWrite("REF_HOLIDAY", "0");
			System.out.println("value:"+h.getHolidayName());
		} catch (Exception e) {
			logger.error(e);
		}
		
	}
}
