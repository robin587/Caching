package com.myCache.dataObjects;

import java.util.Date;

import com.myCache.IDeepCopyable;

/**
 * 
 * @author Robin
 *
 */
public class Holiday implements java.io.Serializable,IDeepCopyable{

	private int holidayID;
	private String holidayCode;
	private String holidayName;
	private Date holidayDate;
	private int status;
	private Date lastModifiedDate;
	private Number modifyPersonNum;
	
	public static enum COLUMNS {HOLIDAY_ID,HOLIDAY_CD,HOLIDAY_NAME,HOLIDAY_DT,STATUS_IND,LAST_MODIFY_DT,MODIFY_PERSON_NUM};
	
	public Holiday(){}
	
	public Holiday(int holidayID,String holidayCode,String holidayName){
		this.holidayID=holidayID;
		this.holidayCode=holidayCode;
		this.holidayName=holidayName;
	}
	
	public int getHolidayID() {
		return holidayID;
	}
	public void setHolidayID(int holidayID) {
		this.holidayID = holidayID;
	}
	public String getHolidayCode() {
		return holidayCode;
	}
	public void setHolidayCode(String holidayCode) {
		this.holidayCode = holidayCode;
	}
	public String getHolidayName() {
		return holidayName;
	}
	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}
	public Date getHolidayDate() {
		return holidayDate;
	}
	public void setHolidayDate(Date holidayDate) {
		this.holidayDate = holidayDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public Number getModifyPersonNum() {
		return modifyPersonNum;
	}
	public void setModifyPersonNum(Number modifyPersonNum) {
		this.modifyPersonNum = modifyPersonNum;
	}

	@Override
	public String toString() {
		return "Holiday [holidayID=" + holidayID + ", holidayCode=" + holidayCode + ", holidayName=" + holidayName
				+ ", holidayDate=" + holidayDate + ", status=" + status + ", lastModifiedDate=" + lastModifiedDate
				+ ", modifyPersonNum=" + modifyPersonNum + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((holidayCode == null) ? 0 : holidayCode.hashCode());
		result = prime * result + ((holidayDate == null) ? 0 : holidayDate.hashCode());
		result = prime * result + holidayID;
		result = prime * result + ((holidayName == null) ? 0 : holidayName.hashCode());
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
		Holiday other = (Holiday) obj;
		if (holidayCode == null) {
			if (other.holidayCode != null)
				return false;
		} else if (!holidayCode.equals(other.holidayCode))
			return false;
		if (holidayDate == null) {
			if (other.holidayDate != null)
				return false;
		} else if (!holidayDate.equals(other.holidayDate))
			return false;
		if (holidayID != other.holidayID)
			return false;
		if (holidayName == null) {
			if (other.holidayName != null)
				return false;
		} else if (!holidayName.equals(other.holidayName))
			return false;
		return true;
	}
	
	public Holiday deepCopy()
	{
		Holiday holiday = new Holiday();
		holiday.setHolidayCode(getHolidayCode());
		holiday.setHolidayDate(getHolidayDate());
		holiday.setHolidayID(getHolidayID());
		holiday.setHolidayName(getHolidayName());
		holiday.setLastModifiedDate(getLastModifiedDate());
		holiday.setModifyPersonNum(getModifyPersonNum());
		holiday.setStatus(getStatus());
		return holiday;
	}
	
}
