package com.myCache.dataObjects;

import java.util.Date;

import com.myCache.IDeepCopyable;
/**
 * 
 * @author Robin
 *
 */
public class Currency implements java.io.Serializable,IDeepCopyable{

	private int currencyID;
	private String currencyCode;
	private String currencyName;
	private String currencySymbol;
	private int statusIndicator;
	private Date lastModifyDate;
	private int modifyPersonNum;
	
	public static enum COLUMNS {CURR_ID,CURR_CD,CURR_NAME,CURRENCY_SYMBOL,STATUS_IND,LAST_MODIFY_DT,MODIFY_PERSON_NUM}

	public Currency(){}
	
	public Currency(int currencyID,String currencyCode,String currencyName){
		this.currencyID=currencyID;
		this.currencyCode=currencyCode;
		this.currencyName=currencyName;
	}
	
	public int getCurrencyID() {
		return currencyID;
	}
	public void setCurrencyID(int currencyID) {
		this.currencyID = currencyID;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	public String getCurrencySymbol() {
		return currencySymbol;
	}
	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}
	public int getStatusIndicator() {
		return statusIndicator;
	}
	public void setStatusIndicator(int statusIndicator) {
		this.statusIndicator = statusIndicator;
	}
	public Date getLastModifyDate() {
		return lastModifyDate;
	}
	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	public int getModifyPersonNum() {
		return modifyPersonNum;
	}
	public void setModifyPersonNum(int modifyPersonNum) {
		this.modifyPersonNum = modifyPersonNum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currencyCode == null) ? 0 : currencyCode.hashCode());
		result = prime * result + currencyID;
		result = prime * result + ((currencyName == null) ? 0 : currencyName.hashCode());
		result = prime * result + ((currencySymbol == null) ? 0 : currencySymbol.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Currency other = (Currency) obj;
		if (currencyCode == null) {
			if (other.currencyCode != null)
				return false;
		} else if (!currencyCode.equals(other.currencyCode))
			return false;
		if (currencyID != other.currencyID)
			return false;
		if (currencyName == null) {
			if (other.currencyName != null)
				return false;
		} else if (!currencyName.equals(other.currencyName))
			return false;
		if (currencySymbol == null) {
			if (other.currencySymbol != null)
				return false;
		} else if (!currencySymbol.equals(other.currencySymbol))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Currency [currencyID=" + currencyID + ", currencyCode=" + currencyCode + ", currencyName="
				+ currencyName + ", currencySymbol=" + currencySymbol + ", statusIndicator=" + statusIndicator
				+ ", lastModifyDate=" + lastModifyDate + ", modifyPersonNum=" + modifyPersonNum + "]";
	}
	
	@Override
	public Currency deepCopy() {
		Currency currency = new Currency();
		currency.setCurrencyCode(getCurrencyCode());
		currency.setCurrencyID(getCurrencyID());
		currency.setCurrencyName(getCurrencyName());
		currency.setCurrencySymbol(getCurrencySymbol());
		currency.setLastModifyDate(getLastModifyDate());
		currency.setModifyPersonNum(getModifyPersonNum());
		currency.setStatusIndicator(getStatusIndicator());
		return currency;
	}
}
