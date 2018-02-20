package com.myCache.daoImpl;

import java.util.List;

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
	public List<Object> getAll()
	{
		if (objects != null && objects.size() <= 0) {
			Country country1 = new Country(0, "CAN", "Canada");
			Country country2 = new Country(1, "FR", "France");
			Country country3 = new Country(2, "IN", "India");
			objects.add(country1);
			objects.add(country2);
			objects.add(country3);
		}
		return objects;
	}
	@Override
	public Country getCountry(int countryID) {
		return (Country)objects.get(countryID);
	}


}
