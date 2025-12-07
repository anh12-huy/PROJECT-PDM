package employeeAttendanceSystem.com.employeeSystem.model;

import java.sql.Date;

 
import java.io.Serializable;
public class LeaveTypeResponseDTO implements Serializable {  
    private String leaveTypeName; 
    private Integer maxDaysAllowed; 
   
    public LeaveTypeResponseDTO() {
    }
    public String getLeaveTypeName() {
        return leaveTypeName;
    }
    public void setLeaveTypeName(String leaveTypeName) {
        this.leaveTypeName = leaveTypeName;
    }

    public Integer getMaxDaysAllowed() {
        return maxDaysAllowed;
    }

    public void setMaxDaysAllowed(Integer maxDaysAllowed) {
        this.maxDaysAllowed = maxDaysAllowed;
    }
}

