package employeeAttendanceSystem.com.employeeSystem.model;

 

import java.io.Serializable;

public class DepartmentAssignmentDTO implements Serializable {
 
    private Integer managerId; 
    
    public DepartmentAssignmentDTO() {}
 
    public Integer getManagerId() {
        return managerId;
    }
 
    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }
}


