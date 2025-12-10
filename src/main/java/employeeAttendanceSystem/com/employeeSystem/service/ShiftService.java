package employeeAttendanceSystem.com.employeeSystem.service;

import employeeAttendanceSystem.com.employeeSystem.model.ShiftDTO;  
import employeeAttendanceSystem.com.employeeSystem.model.EmployeeResponseDTO;  
import employeeAttendanceSystem.com.employeeSystem.model.ShiftRequestDTO; 
import employeeAttendanceSystem.com.employeeSystem.repository.entity.ShiftEntity;  
import employeeAttendanceSystem.com.employeeSystem.repository.entity.EmployeeEntity;  
import java.util.List;
import java.util.Optional;

public interface ShiftService {
 
    List<ShiftDTO> getAllShifts();
   
    Optional<ShiftDTO> getShiftById(int shiftId);
    
    ShiftDTO createShift(ShiftRequestDTO request); 
 
    ShiftDTO updateShift(int shiftId, ShiftRequestDTO request); 
 
    boolean deleteShift(int shiftId);
    boolean assignEmployeeToShift(int shiftId, int employeeId);

    List<EmployeeResponseDTO> getEmployeesInShift(int shiftId);
 
    List<ShiftDTO> getEmployeeSchedule(int employeeId);
}