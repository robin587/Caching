package com.myCache;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CacheMgrTest.class, CountryQueryCacheTest.class, CurrencyQueryCacheTest.class,
		HolidayQueryCacheTest.class })
public class AllTests {

}
