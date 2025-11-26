package employeeAttendanceSystem.com.employeeSystem.service;

import employeeAttendanceSystem.com.employeeSystem.model.EmployeeResponseDTO;
import employeeAttendanceSystem.com.employeeSystem.model.EmployeeRequestDTO;
import employeeAttendanceSystem.com.employeeSystem.model.LeaveRecordResponseDTO;
import employeeAttendanceSystem.com.employeeSystem.model.AttendanceResponseDTO;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
 
    List<EmployeeResponseDTO> getAllEmployees();
 
    Optional<EmployeeResponseDTO> getEmployeeDetails(int employeeId);
 
    EmployeeResponseDTO createEmployee(EmployeeRequestDTO request);
 
    EmployeeResponseDTO updateEmployee(int employeeId, EmployeeRequestDTO request);
 
    boolean deleteEmployee(int employeeId);
 
    List<AttendanceResponseDTO> getAttendanceHistory(int employeeId);
 
    List<LeaveRecordResponseDTO> getLeaveHistory(int employeeId);
 
    boolean assignDepartment(int employeeId, int departmentId);
 
    boolean assignShift(int employeeId, int shiftId);
}