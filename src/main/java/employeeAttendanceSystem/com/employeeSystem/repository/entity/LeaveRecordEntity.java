package employeeAttendanceSystem.com.employeeSystem.repository.entity;

import java.sql.Date;
import java.sql.Timestamp;


public class LeaveRecordEntity {
	private int leaveID;
	private int employeeID;
	private int LeaveTypeID;
	private Date Start_date;
	private Date End_date;
	private String Reason;
	private LeaveStatus status;
	private Timestamp  Created_at;
	public int getLeaveID() {
		return leaveID;
	}
	public void setLeaveID(int leaveID) {
		this.leaveID = leaveID;
	}
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public int getLeaveTypeID() {
		return LeaveTypeID;
	}
	public void setLeaveTypeID(int leaveTypeID) {
		LeaveTypeID = leaveTypeID;
	}
	public Date getStart_date() {
		return Start_date;
	}
	public void setStart_date(Date start_date) {
		Start_date = start_date;
	}
	public Date getEnd_date() {
		return End_date;
	}
	public void setEnd_date(Date end_date) {
		End_date = end_date;
	}
	public String getReason() {
		return Reason;
	}
	public void setReason(String reason) {
		Reason = reason;
	}
	public LeaveStatus getStatus() {
		return status;
	}
	public void setStatus(LeaveStatus status) {
		this.status = status;
	}
	public Timestamp getCreated_at() {
		return Created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		Created_at = created_at;
	}
	
}

