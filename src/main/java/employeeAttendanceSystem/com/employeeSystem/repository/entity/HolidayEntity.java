package employeeAttendanceSystem.com.employeeSystem.repository.entity;

import java.sql.Date;

public class HolidayEntity {
	private int HolidayID;
	private String HolidayName;
	private Date HolidayDate;
	private String Description;
	
	public int getHolidayID() {
		return HolidayID;
	}
	public void setHolidayID(int holidayID) {
		HolidayID = holidayID;
	}
	public String getHolidayName() {
		return HolidayName;
	}
	public void setHolidayName(String holidayName) {
		HolidayName = holidayName;
	}
	public Date getHolidayDate() {
		return HolidayDate;
	}
	public void setHolidayDate(Date holidayDate) {
		HolidayDate = holidayDate;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
}
