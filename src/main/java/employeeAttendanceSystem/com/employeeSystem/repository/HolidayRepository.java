package employeeAttendanceSystem.com.employeeSystem.repository;

import java.sql.Date;
import java.util.*;

import employeeAttendanceSystem.com.employeeSystem.repository.entity.HolidayEntity;


public interface HolidayRepository {
	List<HolidayEntity> getAllHoliday();
	Optional<HolidayEntity> getHolidayById(int holidayId);
	boolean createHolidayRecord(HolidayEntity holiday);
	boolean updateHolidayRecord(int id,HolidayEntity entity);
	boolean deleteHolidayRecord(int holidayId);
	boolean checkHoliday(Date date);
}
