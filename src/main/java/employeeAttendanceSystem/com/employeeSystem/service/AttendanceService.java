package employeeAttendanceSystem.com.employeeSystem.service;

 

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import employeeAttendanceSystem.com.employeeSystem.model.AttendanceRequestDTO;
import employeeAttendanceSystem.com.employeeSystem.model.AttendanceResponseDTO;
 
public interface AttendanceService {
 
    boolean checkIn(int employeeId, int shiftId);
 
    boolean checkOut(int employeeId);
 
    boolean markManualAttendance(AttendanceRequestDTO attendanceDTO, int employeeId, int markerId);
    
    boolean autoMarkAbsentForDate(LocalDate date);

 
    List<AttendanceResponseDTO> getMyAttendanceHistory(int employeeId);
 
    List<AttendanceResponseDTO> getAttendanceByDate(LocalDate date);
    
    String getCurrentStatus(int employeeId);
}
