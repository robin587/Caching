package com.myCache.daoImpl;

import com.myCache.dao.IHolidayDAO;
import com.myCache.dataObjects.Holiday;

/**
 * 
 * @author Robin
 *
 */
public class HolidayDAOImpl extends BaseDAOImpl<Object> implements IHolidayDAO{
	
	public HolidayDAOImpl() {}

	@Override
	public Holiday getHoliday(int holidayID) {
		return (Holiday)objects.get(holidayID);
	}

}
