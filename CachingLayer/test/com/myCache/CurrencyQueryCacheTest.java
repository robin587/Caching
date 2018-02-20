package com.myCache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.myCache.dataObjects.Currency;
/**
 * 
 * @author Robin
 *
 */
public class CurrencyQueryCacheTest {

	private CurrencyQueryCache currencyQueryCache=null;
	

	@Before
	public void setUp() throws Exception {
		currencyQueryCache = new CurrencyQueryCache();
        Currency currency1 = new Currency(0, "AFN", "Afghanistan");	
        Currency currency2 = new Currency(1, "BHD", "Bahrain");	
        Currency currency3 = new Currency(2, "CAD", "Canadian Dollar");
        currencyQueryCache.getCurrencyImpl().add(currency1);
        currencyQueryCache.getCurrencyImpl().add(currency2);
        currencyQueryCache.getCurrencyImpl().add(currency3);
	}

	@After
	public void tearDown() throws Exception {
		currencyQueryCache=null;
	}

	@Test
	public void testPopulateCachedData() {
		List<Object> list = currencyQueryCache.getCurrencyImpl().getAll();
		currencyQueryCache.populateCachedData(list);

		ConcurrentHashMap<String, List<Object>> map = currencyQueryCache.cachedData
				.get(currencyQueryCache.getPrimaryKeyName());
		List<Object> objects = (List<Object>) map.get("1");
		Currency c = (Currency) objects.get(0);
		assertEquals("Checking data in Cache", "BHD", c.getCurrencyCode());

		List<Object> objects1 = (List<Object>) map.get("0");
		Currency c1 = (Currency) objects1.get(0);
		assertEquals("Checking data in Cache", "AFN", c1.getCurrencyCode());
	
	}

	@Test
	public void testReload() {
		Currency currency4 = new Currency(3, "DKK", "Denmark");	
		Currency currency5 = new Currency(4, "EGP", "Egypt");	
		currencyQueryCache.getCurrencyImpl().add(currency4);
		currencyQueryCache.getCurrencyImpl().add(currency5);

		currencyQueryCache.reload();

		ConcurrentHashMap<String, List<Object>> map = currencyQueryCache.cachedData
				.get(currencyQueryCache.getPrimaryKeyName());
		List<Object> objects = (List<Object>) map.get("4");
		Currency c = (Currency) objects.get(0);
		assertEquals("Checking data in Cache", "Egypt", c.getCurrencyName());
	}

	@Test
	public void testQueryTableDeepCopy() {
		Currency currency6 = new Currency(5, "INR", "Indian Rupee");
		currencyQueryCache.getCurrencyImpl().add(currency6);
		currencyQueryCache.reload();

		List<Object> objects = (List<Object>) currencyQueryCache
				.queryTableDeepCopy(currencyQueryCache.getPrimaryKeyName(), "5");
		Currency c = (Currency) objects.get(0);
		assertEquals("Checking data in Cache", "Indian Rupee", c.getCurrencyName());
		c.setCurrencyName("I Rupee");

		List<Object> objects1 = (List<Object>) currencyQueryCache
				.queryTableDeepCopy(currencyQueryCache.getPrimaryKeyName(), "5");
		Currency c1 = (Currency) objects1.get(0);
		assertEquals("Checking data in Cache", "Indian Rupee", c1.getCurrencyName());
	}

	@Test
	public void testQueryTable() {
		Currency currency7 = new Currency(7, "DIN", "Dubai");
		currencyQueryCache.getCurrencyImpl().add(currency7);
		currencyQueryCache.reload();

		List<Object> objects = (List<Object>) currencyQueryCache.queryTable(currencyQueryCache.getPrimaryKeyName(), "7");
		Currency c = (Currency) objects.get(0);
		assertEquals("Checking data in Cache", "Dubai", c.getCurrencyName());
		c.setCurrencyName("Dubai Dinar");

		List<Object> objects1 = (List<Object>) currencyQueryCache.queryTable(currencyQueryCache.getPrimaryKeyName(), "7");
		Currency c1 = (Currency) objects1.get(0);

		assertNotEquals("Checking data in Cache", "Colombia", c1.getCurrencyName());
	}

	@Test
	public void testDelete(){
		Currency currency7 = new Currency(7, "DIN", "Dubai");
		currencyQueryCache.getCurrencyImpl().delete(currency7);
		currencyQueryCache.deleteObject(currencyQueryCache.getPrimaryKeyName(), "7");
		List<Object> objects = (List<Object>) currencyQueryCache.
				queryTable(currencyQueryCache.getPrimaryKeyName(), "7");
		assertNull("Checking delete",objects);
		
	}
}
