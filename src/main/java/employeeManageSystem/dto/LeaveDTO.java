package employeeManageSystem.dto;

import java.sql.Date;

public class LeaveDTO {
	 	private int leaveId;
	    private Date startDate;
	    private Date endDate;
	    private String reason;
	    private String status;

	    private int employeeId;
	    private String employeeName;

	    private int leaveTypeId;
	    private String leaveTypeName;
		public int getLeaveId() {
			return leaveId;
		}
		public void setLeaveId(int leaveId) {
			this.leaveId = leaveId;
		}
		public Date getStartDate() {
			return startDate;
		}
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}
		public Date getEndDate() {
			return endDate;
		}
		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}
		public String getReason() {
			return reason;
		}
		public void setReason(String reason) {
			this.reason = reason;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public int getEmployeeId() {
			return employeeId;
		}
		public void setEmployeeId(int employeeId) {
			this.employeeId = employeeId;
		}
		public String getEmployeeName() {
			return employeeName;
		}
		public void setEmployeeName(String employeeName) {
			this.employeeName = employeeName;
		}
		public int getLeaveTypeId() {
			return leaveTypeId;
		}
		public void setLeaveTypeId(int leaveTypeId) {
			this.leaveTypeId = leaveTypeId;
		}
		public String getLeaveTypeName() {
			return leaveTypeName;
		}
		public void setLeaveTypeName(String leaveTypeName) {
			this.leaveTypeName = leaveTypeName;
		}
}
