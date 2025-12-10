 

package employeeAttendanceSystem.com.employeeSystem.repository;

import employeeAttendanceSystem.com.employeeSystem.repository.entity.AttendanceEntity;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.AttendanceStatus;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository {
    Optional<AttendanceEntity> findByEmployeeAndDate(int employeeId, Date date);
    boolean save(AttendanceEntity attendance); 
  
    boolean checkIn(int employeeId, int shiftId);
 
    boolean checkOut(int employeeId);
     
    Optional<AttendanceEntity> findOpenCheckInByEmployeeId(int employeeId); 
   
    List<AttendanceEntity> findAllByEmployeeId(int employeeId); 
 
    List<AttendanceEntity> getByDate(Date date);  
   
    Optional<AttendanceStatus> getAttendanceStatus(int emplId);
  
    boolean autoMarkAbsent(Date date);  
}