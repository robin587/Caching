package com.myCache.daoImpl;

import java.util.List;

import com.myCache.dao.ICurrencyDAO;
import com.myCache.dataObjects.Currency;
/**
 * 
 * @author Robin
 *
 */
public class CurrencyDAOImpl extends BaseDAOImpl<Object> implements ICurrencyDAO{

    public CurrencyDAOImpl() {}

    @Override
    public List<Object> getAll(){
    	if(objects != null && objects.size() <=0){
    		Currency currency1 = new Currency(0, "AFN", "Afghanistan");	
            Currency currency2 = new Currency(1, "BHD", "Bahrain");	
            objects.add(currency1);
            objects.add(currency2);
    	}
    	return objects;
    }
	@Override
	public Currency getCurrency(int currencyID) {
		return (Currency)objects.get(currencyID);
	}
}
