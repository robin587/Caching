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
/**
 * 
 * @author Robin
 *
 */
public class CacheMgrTest {

	//private CacheMgr cacheMgr;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	//	cacheMgr = new CacheMgr();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInitializeCache() {
	  CacheMgr.initializeCache();
	}

	@Test(expected=EntityNotFoundException.class)
	public void testGetObjectFromCache() throws EntityNotFoundException {
		Object obj=CacheMgr.getObjectFromCache("REF_COUNTRY", "0", true);	
	}

	@Test
	public void testWipeCache() throws EntityNotFoundException {
		CacheMgr.wipeCache();
		Object obj=CacheMgr.getObjectFromCache("REF_COUNTRY", "0", true);
		assertNull(obj);
	}
}
