package employeeAttendanceSystem.com.employeeSystem.model;

 

import java.io.Serializable;
import java.util.List;

public class ShiftAssignmentRequestDTO implements Serializable {
 
    private List<Integer> employeeIds; 
 
    public ShiftAssignmentRequestDTO() {}
 
    public List<Integer> getEmployeeIds() { return employeeIds; }
    
    public void setEmployeeIds(List<Integer> employeeIds) { this.employeeIds = employeeIds; }
}

