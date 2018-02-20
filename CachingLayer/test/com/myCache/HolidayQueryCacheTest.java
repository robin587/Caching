package com.myCache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.myCache.daoImpl.HolidayDAOImpl;
import com.myCache.dataObjects.Holiday;

public class HolidayQueryCacheTest {

	private HolidayQueryCache holidayQueryCache = null;
	@Before
	public void setUp() throws Exception {
		holidayQueryCache= new HolidayQueryCache();
		HolidayDAOImpl hDaoImpl = holidayQueryCache.getHolidayImpl();
		Holiday holiday1 = new Holiday(0, "H1", "EASY");
        Holiday holiday2 = new Holiday(1, "H2", "LAKE");	
        Holiday holiday3 = new Holiday(3, "H3", "SEA");	
        hDaoImpl.add(holiday1);
        hDaoImpl.add(holiday2);
        hDaoImpl.add(holiday3);
	}

	@After
	public void tearDown() throws Exception {
		holidayQueryCache=null;
	}

	@Test
	public void testPopulateCachedData() {
		List<Object> list = holidayQueryCache.getHolidayImpl().getAll();
		holidayQueryCache.populateCachedData(list);

		ConcurrentHashMap<String, List<Object>> map = holidayQueryCache.cachedData
				.get(holidayQueryCache.getPrimaryKeyName());
		List<Object> objects = (List<Object>) map.get("1");
		Holiday h1 = (Holiday) objects.get(0);
		assertEquals("Checking data in Cache", "H2", h1.getHolidayCode());

		List<Object> objects1 = (List<Object>) map.get("0");
		Holiday h2 = (Holiday) objects1.get(0);
		assertEquals("Checking data in Cache", "H1", h2.getHolidayCode());
	}

	@Test
	public void testReload() {
		Holiday holiday4 = new Holiday(4, "H4", "RESR");	
		Holiday holiday5 = new Holiday(5, "H5", "OYO");	
		holidayQueryCache.getHolidayImpl().add(holiday4);
		holidayQueryCache.getHolidayImpl().add(holiday5);

		holidayQueryCache.reload();

		ConcurrentHashMap<String, List<Object>> map = holidayQueryCache.cachedData
				.get(holidayQueryCache.getPrimaryKeyName());
		List<Object> objects = (List<Object>) map.get("4");
		Holiday h = (Holiday) objects.get(0);
		assertEquals("Checking data in Cache", "RESR", h.getHolidayName());
	}

	@Test
	public void testDelete(){
		Holiday holiday4 = new Holiday(4, "H4", "RESR");	
		holidayQueryCache.getHolidayImpl().delete(holiday4);
		holidayQueryCache.deleteObject(holidayQueryCache.getPrimaryKeyName(), "4");
		List<Object> objects = (List<Object>) holidayQueryCache.
				queryTable(holidayQueryCache.getPrimaryKeyName(), "4");
		assertNull("Checking delete",objects);		
	}
}
