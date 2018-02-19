package com.myCache.daoImpl;

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
	public Currency getCurrency(int currencyID) {
		return (Currency)objects.get(currencyID);
	}
}
