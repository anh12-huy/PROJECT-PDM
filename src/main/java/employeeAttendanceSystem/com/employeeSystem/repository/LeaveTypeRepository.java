package employeeAttendanceSystem.com.employeeSystem.repository;

import java.util.*;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.LeaveTypeEntity;

public interface LeaveTypeRepository {
  
   List<LeaveTypeEntity> getAllLeaveType();
   Optional<LeaveTypeEntity> getLeaveTypeById(int id);
   boolean createLeaveType(LeaveTypeEntity entity);
   boolean updateLeaveType(int id,LeaveTypeEntity entity);
   boolean deleteLeaveType(int id);
}

