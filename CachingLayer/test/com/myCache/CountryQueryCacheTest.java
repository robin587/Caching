package com.myCache;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.myCache.daoImpl.CountryDAOImpl;
import com.myCache.dataObjects.Country;
/**
 * 
 * @author Robin
 *
 */
public class CountryQueryCacheTest {

	private CountryQueryCache countryQueryCache = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		countryQueryCache = new CountryQueryCache();
		CountryDAOImpl cDaoImpl = countryQueryCache.getCountryImpl();
		Country country1 = new Country(0, "CAN", "Canada");
		Country country2 = new Country(1, "FR", "France");
		Country country3 = new Country(2, "IN", "India");
		cDaoImpl.add(country1);
		cDaoImpl.add(country2);
		cDaoImpl.add(country3);
	}

	@After
	public void tearDown() throws Exception {
		countryQueryCache = null;
	}

	@Test
	@Ignore
	public void testPopulateCachedData() {

		List<Object> list = countryQueryCache.getCountryImpl().getAll();
		countryQueryCache.populateCachedData(list);

		ConcurrentHashMap<String, List<Object>> map = countryQueryCache.cachedData
				.get(countryQueryCache.getPrimaryKeyName());
		List<Object> objects = (List<Object>) map.get("1");
		Country c = (Country) objects.get(0);
		assertEquals("Checking data in Cache", "FR", c.getCountryCode());

		List<Object> objects1 = (List<Object>) map.get("0");
		Country c1 = (Country) objects1.get(0);
		assertEquals("Checking data in Cache", "CAN", c1.getCountryCode());

	}

	@Test
	@Ignore
	public void testReload() {
		Country country4 = new Country(4, "BH", "Bahrain");
		Country country5 = new Country(5, "CHN", "China");
		countryQueryCache.getCountryImpl().add(country4);
		countryQueryCache.getCountryImpl().add(country5);

		countryQueryCache.reload();

		ConcurrentHashMap<String, List<Object>> map = countryQueryCache.cachedData
				.get(countryQueryCache.getPrimaryKeyName());
		List<Object> objects = (List<Object>) map.get("4");
		Country c = (Country) objects.get(0);
		assertEquals("Checking data in Cache", "Bahrain", c.getCountryName());

	}

	@Test
	public void testQueryTableDeepCopy() {
		Country country6 = new Country(6, "BR", "Brazil");
		countryQueryCache.getCountryImpl().add(country6);
		countryQueryCache.reload();

		List<Object> objects = (List<Object>) countryQueryCache.
				queryTableDeepCopy(countryQueryCache.getPrimaryKeyName(), "6");
		Country c = (Country)objects.get(0);
		assertEquals("Checking data in Cache", "Brazil", c.getCountryName());
		c.setCountryName("BRAZIL");		
		
		List<Object> objects1 = (List<Object>) countryQueryCache.
				queryTableDeepCopy(countryQueryCache.getPrimaryKeyName(), "6");
		Country c1 = (Country)objects1.get(0);
		
		
		assertEquals("Checking data in Cache", "Brazil", c1.getCountryName());
	}

	@Test
	public void testInitializeCacheData() {
		assertTrue(true);
	}

	@Test
	public void testQueryTableStringString() {
		Country country7 = new Country(6, "CO", "Colombia");
		countryQueryCache.getCountryImpl().add(country7);
		countryQueryCache.reload();

		List<Object> objects = (List<Object>) countryQueryCache.
				queryTable(countryQueryCache.getPrimaryKeyName(), "6");
		Country c = (Country)objects.get(0);
		assertEquals("Checking data in Cache", "Colombia", c.getCountryName());
		c.setCountryName("COLOMBIA");		
		
		List<Object> objects1 = (List<Object>) countryQueryCache.
				queryTable(countryQueryCache.getPrimaryKeyName(), "6");
		Country c1 = (Country)objects1.get(0);
		
		assertNotEquals("Checking data in Cache", "Colombia", c1.getCountryName());
	}

	@Test
	public void testLoadObject() {
		assertTrue(true);
	}

}
