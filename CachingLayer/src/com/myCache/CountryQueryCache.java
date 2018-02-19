
package com.myCache;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.myCache.daoImpl.CountryDAOImpl;
import com.myCache.dataObjects.Country;

/**
 * 
 * @author Robin
 *
 */
public class CountryQueryCache extends QueryCache {

	private static Logger logger = Logger.getLogger(CountryQueryCache.class);
	private static int TABLE_SIZE = 250;
	private static String TABLE_NAME = "REF_COUNTRY";
	private static String PRI_KEY_NAME = Country.COLUMNS.COUNTRY_ID.name();
	private CountryDAOImpl countryImpl = new CountryDAOImpl();
	
	public CountryQueryCache() {
       super(TABLE_NAME,PRI_KEY_NAME);
	}
	
	protected void populateCachedData(List<Object> dataList) {
		if (dataList != null) {
			try {
				ConcurrentHashMap<String, List<Object>> pkIndex = new ConcurrentHashMap<String, List<Object>>();
				dataList.forEach(object->{
					Country c = (Country)object;
					List<Object> elements = (List<Object>) pkIndex.get(Integer.toString(c.getCountryID()));
					if (elements == null) {
						// for primary key search, there should have only one entry
						// for each key
						elements = new ArrayList<Object>(5);
						elements.add(c);
						pkIndex.put(Integer.toString(c.getCountryID()), elements);
					} else {
						// if key is not primary it comes here.TO be used in future for a requirement where key can be non primary key column
					}
				});
				cachedData.remove(getPrimaryKeyName());
				cachedData.put(getPrimaryKeyName(), pkIndex);
			} catch (Exception e) {
				logger.error(e);
			}
			catch(Error er){
				logger.error(er);
			}
		}
	}
	
	public void reload() {
		List<Object> data = countryImpl.getAll();
		write.lock();
		Iterator<ConcurrentHashMap<String, List<Object>>> iter = cachedData.values().iterator();
		while (iter.hasNext()) {
			ConcurrentHashMap<String, List<Object>> map = (ConcurrentHashMap<String, List<Object>>) iter.next();
			map.clear();
		}
		populateCachedData((List<Object>)data);
		write.unlock();
	}
	
	public List<Object> queryTableDeepCopy(String pKey, String value) {
		List<Object> objList = null;
		List<Object> returnList = new ArrayList<Object>();
		try {
			read.lock();
			if (cachedData.get(pKey).size() > 0) {
				objList = (List<Object>) cachedData.get(pKey).get(value);
			}
		} catch (Exception e) {
			return null;
		} finally {
			if (objList != null) {
				// Loop thru all the entries in the objList and place a copy
				// into
				// the returnList.
				for (int i = 0; i < objList.size(); i++) {
					returnList.add(((Country) objList.get(i)).deepCopy());
				}
			}
			read.unlock();
		}
		return returnList;
	}
	
	@Override
	public String getDBTableName() {
		return TABLE_NAME;
	}

	@Override
	public String getPrimaryKeyName() {
		return PRI_KEY_NAME;
	}

	@Override
	protected int getTableSize() {
		return TABLE_SIZE;
	}

	@Override
	public void deleteObject(String column, String value) {
		reload();
	}

	@Override
	public void updateObject(String column, String value) {
		reload();		
	}

	@Override
	public void addObject(String column, String value) {
		reload();		
	}
	
	public CountryDAOImpl getCountryImpl() {
		return countryImpl;
	}

	public void setCountryImpl(CountryDAOImpl countryImpl) {
		this.countryImpl = countryImpl;
	}
}
