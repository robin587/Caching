package com.myCache.daoImpl;

import com.myCache.dao.CountryDAO;
import com.myCache.dataObjects.Country;
/**
 * 
 * @author Robin
 *
 */
public class CountryDAOImpl extends BaseDAOImpl<Object> implements CountryDAO{

	public CountryDAOImpl() {}
	

	@Override
	public Country getCountry(int countryID) {
		return (Country)objects.get(countryID);
	}


}
