package employeeAttendanceSystem.com.employeeSystem.repository;

import java.util.List;
import java.util.Optional;

import employeeAttendanceSystem.com.employeeSystem.repository.entity.LeaveRecordEntity;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.LeaveStatus;


public interface LeaveRecordRepository {
	List<LeaveRecordEntity> getAllLeaveRecord();
	Optional<LeaveRecordEntity> getLeaveRecordById(int id);
	boolean createLeaveRecord(LeaveRecordEntity entity);
	boolean updateLeaveRecord(int id,LeaveRecordEntity entity);
	boolean deleteLeaveRecord(int id);
    boolean approveLeaveRecord(int id);
    boolean denyLeaveRecord(int id);
    Optional<LeaveRecordEntity> getLeaveRecordOfEmployee(int emplId);
    List<LeaveRecordEntity> getAllPendingLeaveRecord();
}