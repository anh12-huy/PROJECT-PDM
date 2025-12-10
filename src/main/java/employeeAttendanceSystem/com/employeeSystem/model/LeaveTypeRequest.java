package employeeAttendanceSystem.com.employeeSystem.model;

public class LeaveTypeRequest {
	private int LeaveTypeID;
	private String LeaveType_name;
	private int MaxDaysAllowed;
	public int getLeaveTypeID() {
		return LeaveTypeID;
	}
	public void setLeaveTypeID(int leaveTypeID) {
		LeaveTypeID = leaveTypeID;
	}
	public String getLeaveType_name() {
		return LeaveType_name;
	}
	public void setLeaveType_name(String leaveType_name) {
		LeaveType_name = leaveType_name;
	}
	public int getMaxDaysAllowed() {
		return MaxDaysAllowed;
	}
	public void setMaxDaysAllowed(int maxDaysAllowed) {
		MaxDaysAllowed = maxDaysAllowed;
	}
}
