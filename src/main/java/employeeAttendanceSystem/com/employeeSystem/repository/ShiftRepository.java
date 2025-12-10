package employeeAttendanceSystem.com.employeeSystem.repository;

import java.util.*;

import employeeAttendanceSystem.com.employeeSystem.repository.entity.EmployeeEntity;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.ShiftEntity;

public interface ShiftRepository {
	   List<ShiftEntity> geAtAllShift();
	   Optional<ShiftEntity> getShiftById(int Shiftid);
	   boolean createShift(ShiftEntity entity);
	   boolean updateShift(int id,ShiftEntity entity);
	   boolean deleteShift(int id);
	   boolean assignEmployeToShift(int shiftId,int employeeId);
	   List<EmployeeEntity> getAllEmployeeInShift(int shiftId);
	   List<ShiftEntity> getAllShiftOfEmployee(int employeeId);
}
