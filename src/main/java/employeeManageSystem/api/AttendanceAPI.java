package employeeManageSystem.api;
import org.springframework.web.bind.annotation.*;

import employeeManageSystem.model.AttendanceDTO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AttendanceAPI {
	@GetMapping(value="/api/attendances")
	public List<AttendanceDTO> getAllAttendance(){
		List<AttendanceDTO> result=new ArrayList<>();
		return result;
	}
	
	@GetMapping(value="/api/attendances/{id}")
	public AttendanceDTO getAttendance(@PathVariable String attendanceId) {
		return new AttendanceDTO();
	}
	
	@GetMapping(value="/api/attendances/employee/{employeeId}")
	public List<AttendanceDTO> getAttendanceOfEmployee(@PathVariable String employeeId){
		List<AttendanceDTO> result=new ArrayList<>();
		return result;
	}
	
	@GetMapping(value="/api/attendances/date/{yyyy-MM-dd}")
	public List<AttendanceDTO> getAttendanceOfDate(@PathVariable Date date){
		List<AttendanceDTO> result=new ArrayList<>();
		return result;
	}
	
	@PostMapping(value="/api/attendances")
	public void createAttendance(@RequestBody AttendanceDTO dto) {
		
	}
	
	@PostMapping(value="/api/attendances/auto-absent")
	public void autoAbsent() {
		
	}
	
	@PutMapping(value="/api/attendances/{id}")
	public void updateAttendance(@PathVariable String attendanceId) {
		
	}
	
	@DeleteMapping(value="/api/attendances/{id}")
public void deleteAttendance(@PathVariable String attendanceId) {
		
	}
}
