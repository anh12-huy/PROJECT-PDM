package employeeAttendanceSystem.com.employeeSystem.service;

 

import employeeAttendanceSystem.com.employeeSystem.repository.entity.LeaveRecordEntity; 
import employeeAttendanceSystem.com.employeeSystem. model.LeaveRecordResponseDTO; 
import employeeAttendanceSystem.com.employeeSystem.model.LeaveRecordRequestDTO;

import java.util.List;
import java.util.Optional;

public interface LeaveRecordService {
 
    List<LeaveRecordResponseDTO> getAllLeaveRecords();
 
    Optional<LeaveRecordResponseDTO> getLeaveRecordById(int leaveId);
 
    LeaveRecordResponseDTO createLeaveRecord(LeaveRecordRequestDTO request);
 
    LeaveRecordResponseDTO updateLeaveRecord(int leaveId, LeaveRecordRequestDTO request);
 
    boolean deleteLeaveRecord(int leaveId);
 
    boolean approveLeaveRecord(int leaveId);
 
    boolean denyLeaveRecord(int leaveId);
 
    List<LeaveRecordResponseDTO> getLeaveRecordsByEmployee(int employeeId);
 
    List<LeaveRecordResponseDTO> getAllPendingLeaveRecords();
}

