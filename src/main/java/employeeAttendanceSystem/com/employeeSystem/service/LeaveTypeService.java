

package employeeAttendanceSystem.com.employeeSystem.service; 

import employeeAttendanceSystem.com.employeeSystem.repository.entity.LeaveTypeEntity ;
import employeeAttendanceSystem.com.employeeSystem.model.LeaveTypeResponseDTO;  
import employeeAttendanceSystem.com.employeeSystem.model.LeaveTypeRequest; 
import java.util.List;
import java.util.Optional;

public interface LeaveTypeService {

    List<LeaveTypeResponseDTO> getAllLeaveTypes(); 

 
    Optional<LeaveTypeResponseDTO> getLeaveTypeById(int leaveTypeId); 


    LeaveTypeResponseDTO createLeaveType(LeaveTypeRequest request); 

 
    LeaveTypeResponseDTO updateLeaveType(int leaveTypeId, LeaveTypeRequest request); 

    
    boolean deleteLeaveType(int leaveTypeId);
}