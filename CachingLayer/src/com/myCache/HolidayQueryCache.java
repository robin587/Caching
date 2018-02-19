package com.myCache;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.myCache.daoImpl.HolidayDAOImpl;
import com.myCache.dataObjects.Holiday;

/**
 * 
 * @author Robin
 *
 */
public class HolidayQueryCache extends QueryCache {
	
	private static Logger logger = Logger.getLogger(HolidayQueryCache.class);
	private static int TABLE_SIZE = 250;
	private static String TABLE_NAME = "REF_HOLIDAY";
	private static String PRI_KEY_NAME = Holiday.COLUMNS.HOLIDAY_ID.name();
	private HolidayDAOImpl holidayImpl = new HolidayDAOImpl();
	
	public static void setLogger(Logger logger) {
		HolidayQueryCache.logger = logger;
	}

	public static void setTABLE_SIZE(int tABLE_SIZE) {
		TABLE_SIZE = tABLE_SIZE;
	}

	public static void setTABLE_NAME(String tABLE_NAME) {
		TABLE_NAME = tABLE_NAME;
	}

	public static void setPRI_KEY_NAME(String pRI_KEY_NAME) {
		PRI_KEY_NAME = pRI_KEY_NAME;
	}

	public HolidayQueryCache() {
       super(TABLE_NAME,PRI_KEY_NAME);
	}

	protected void populateCachedData(List<Object> dataList) {
		if (dataList != null) {
			try {
				ConcurrentHashMap<String, List<Object>> pkIndex = new ConcurrentHashMap<String, List<Object>>();
				dataList.forEach(object->{
					Holiday c = (Holiday)object;
					List<Object> elements = (List<Object>) pkIndex.get(Integer.toString(c.getHolidayID()));
					if (elements == null) {
						// for primary key search, there should have only one entry
						// for each key
						elements = new ArrayList<Object>(5);
						elements.add(c);
						pkIndex.put(Integer.toString(c.getHolidayID()), elements);
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
		List<Object> data = holidayImpl.getAll();
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
					returnList.add(((Holiday) objList.get(i)).deepCopy());
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
	
	public HolidayDAOImpl getHolidayImpl() {
		return holidayImpl;
	}
}
