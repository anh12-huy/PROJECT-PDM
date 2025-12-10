package employeeAttendanceSystem.com.employeeSystem.model;
 

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import employeeAttendanceSystem.com.employeeSystem.repository.entity.LeaveStatus;
 
import employeeAttendanceSystem.com.employeeSystem.repository.entity.LeaveStatus;

 
public class LeaveRecordResponseDTO  {

     
    private int leaveId;         
    private int employeeId;     
    private LocalDateTime createdAt;
    
    private int leaveTypeId;    
    private String leaveTypeName; 
    private Integer maxDaysAllowed;  
   
    private LocalDate startDate;
    private LocalDate endDate;
    private int totalDays;      
     
    private String reason;
    private LeaveStatus status; 
   
    public LeaveRecordResponseDTO() {}
 
    public int getLeaveId() { return leaveId; }
    public void setLeaveId(int leaveId) { this.leaveId = leaveId; }
 
    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }
 
    public int getLeaveTypeId() { return leaveTypeId; }
    public void setLeaveTypeId(int leaveTypeId) { this.leaveTypeId = leaveTypeId; }

    public String getLeaveTypeName() { return leaveTypeName; }
    public void setLeaveTypeName(String leaveTypeName) { this.leaveTypeName = leaveTypeName; }

    public Integer getMaxDaysAllowed() { return maxDaysAllowed; }
    public void setMaxDaysAllowed(Integer maxDaysAllowed) { this.maxDaysAllowed = maxDaysAllowed; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

 
    public int getTotalDays() { return totalDays; }
    public void setTotalDays(int totalDays) { this.totalDays = totalDays; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public LeaveStatus getStatus() { return status; }
    public void setStatus(LeaveStatus status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
