package employeeAttendanceSystem.com.employeeSystem.model;

 

import java.io.Serializable;
 
public class EmployeeAssignmentDTO implements Serializable {
 
    private int departmentId; 
 
    private int shiftId; 
   
    public EmployeeAssignmentDTO() {}
 
    public int getDepartmentId() { return departmentId; }
    public void setDepartmentId(int departmentId) { this.departmentId = departmentId; }
 
    public int getShiftId() { return shiftId; }
    public void setShiftId(int shiftId) { this.shiftId = shiftId; }
}
