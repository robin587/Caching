package com.myCache.daoImpl;

import java.util.List;

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
	    public List<Object> getAll(){
	    	if(objects != null && objects.size() <=0){
	    		Holiday holiday1 = new Holiday(0, "H1", "EASY");
	            Holiday holiday2 = new Holiday(1, "H2", "LAKE");	
	            objects.add(holiday1);
	            objects.add(holiday2);
	    	}
	    	return objects;
	    }
	  
	@Override
	public Holiday getHoliday(int holidayID) {
		return (Holiday)objects.get(holidayID);
	}

}
