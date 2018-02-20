package com.myCache;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Categories.ExcludeCategory;

import com.myCache.dataObjects.Country;
import com.myCache.dataObjects.Currency;
import com.myCache.dataObjects.Holiday;
/**
 * 
 * @author Robin
 *
 */
public class CacheMgrTest {

	
	@Before
	public void setUp() throws Exception {
        CacheMgr.initializeCache();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLoadDataRead(){
		Object obj=CacheMgr.loadDataRead("REF_COUNTRY", "0");
		Country c = (Country)obj;
		assertEquals("Check data for Country in cache","CAN", c.getCountryCode());
		
		Object obj1=CacheMgr.loadDataRead("REF_CURRENCY", "1");
		Currency cur = (Currency)obj1;
		assertEquals("Check data for Currency in cache","BHD", cur.getCurrencyCode());
		
		Object obj2=CacheMgr.loadDataRead("REF_HOLIDAY", "1");
		Holiday h = (Holiday)obj2;
		assertEquals("Check data for Holiday object in cache","LAKE", h.getHolidayName());
	}
	
	@Test
	public void testLoadDataWrite(){
		Object obj=CacheMgr.loadDataWrite("REF_COUNTRY", "0");
		Country c = (Country)obj;
		c.setCountryName("CANADA");
		
		Object obj1=CacheMgr.loadDataWrite("REF_COUNTRY", "0");
		Country c1 = (Country)obj1;
		assertEquals("Check data for Country in cache","Canada", c1.getCountryName());
		
	}
	
	@Test(expected=EntityNotFoundException.class)
	public void testGetObjectFromCache() throws EntityNotFoundException {
		Object obj=CacheMgr.getObjectFromCache("REF_COUNTRY", "100", false);	
	}

	@Test
	public void testWipeCache() throws EntityNotFoundException {
		CacheMgr.wipeCache();
		Object obj=CacheMgr.getObjectFromCache("REF_COUNTRY", "0", true);
		assertNull(obj);
	}
}
