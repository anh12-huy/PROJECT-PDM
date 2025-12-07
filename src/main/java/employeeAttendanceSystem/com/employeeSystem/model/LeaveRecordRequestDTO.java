package employeeAttendanceSystem.com.employeeSystem.model;

 

import java.io.Serializable;
import java.time.LocalDate;

 
public class LeaveRecordRequestDTO implements Serializable {
 
    private int leaveTypeId; 
    private int employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
  
    private String reason;
 
    public LeaveRecordRequestDTO() {}
    
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getLeaveTypeId() {
        return leaveTypeId;
    }

    public void setLeaveTypeId(int leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
