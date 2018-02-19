package com.myCache.dataObjects;

import java.util.Date;

import com.myCache.IDeepCopyable;
/**
 * 
 * @author Robin
 *
 */
public class Country implements java.io.Serializable,IDeepCopyable{
	
	private String countryCode;
	private String countryName;
	private int status;
	private int modifyPersonNum;
	private Date lastModifyDate;
	private int countryID;
	
	public static enum COLUMNS {COUNTRY_ID,COUNTRY_CD,COUNTRY_NAME,STATUS_IND,LAST_MODIFY_DT,MODIFY_PERSON_NUM};
	
	/** default constructor */
	public Country(){}
	
	public Country(int countryID,String countryCode,String countryName){
		this.countryID=countryID;
		this.countryCode=countryCode;
		this.countryName=countryName;
	}
	
	public Country(int countryID,String countryCode,String countryName,int status,int modifyPersonNum,Date lastModifiedDate){
		this.countryID=countryID;
		this.countryCode=countryCode;
		this.countryName=countryName;
		this.status=status;
		this.modifyPersonNum=modifyPersonNum;
		this.lastModifyDate=lastModifiedDate;
	}
	
	
	public int getCountryID() {
		return countryID;
	}

	public void setCountryID(int countryID) {
		this.countryID = countryID;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getModifyPersonNum() {
		return modifyPersonNum;
	}

	public void setModifyPersonNum(int modifyPersonNum) {
		this.modifyPersonNum = modifyPersonNum;
	}

	public Date getLastModifyDate() {
		return lastModifyDate;
	}

	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	
	
	@Override
	public Country deepCopy() {
		Country copy = new Country();
		copy.setCountryID(getCountryID());
		copy.setCountryCode(getCountryCode());
		copy.setCountryName(getCountryName());
		copy.setStatus(getStatus());
		copy.setModifyPersonNum(getModifyPersonNum());
		copy.setLastModifyDate(getLastModifyDate());
		return copy;
	}

	@Override
	public String toString() {
		return "Country [countryCode=" + countryCode + ", countryName=" + countryName + ", status=" + status
				+ ", modifyPersonNum=" + modifyPersonNum + ", lastModifyDate=" + lastModifyDate + ", countryID="
				+ countryID + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((countryCode == null) ? 0 : countryCode.hashCode());
		result = prime * result + countryID;
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
		Country other = (Country) obj;
		if (countryCode == null) {
			if (other.countryCode != null)
				return false;
		} else if (!countryCode.equals(other.countryCode))
			return false;
		if (countryID != other.countryID)
			return false;
		return true;
	}
	
	

}
