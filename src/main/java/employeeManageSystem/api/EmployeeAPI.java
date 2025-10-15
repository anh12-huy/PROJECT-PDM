package employeeManageSystem.api;


import org.springframework.web.bind.annotation.*;

import employeeManageSystem.dto.AttendanceDTO;
import employeeManageSystem.dto.EmployeeDTO;
import employeeManageSystem.dto.LeaveDTO;

import java.util.ArrayList;
import java.util.List;



@RestController
public class EmployeeAPI {
	@GetMapping(value="/api/employees/")
	public List<EmployeeDTO> getAllEmployee(@RequestParam(value="name",required=false) String name,
											@RequestParam(value="street",required=false) String street,
											@RequestParam(value="ward",required=false) String ward,
											@RequestParam(value="sex",required=false) String sex,
											@RequestParam(value="department",required=false) String department,
											@RequestParam(value="shift",required=false) String shift){
		List<EmployeeDTO> result= new ArrayList<>();
		
		
		return result;
	}
	
	@GetMapping(value="/api/employees/{id}")
	public EmployeeDTO getEmployeeInfo(@PathVariable int EmployeeId) {
		EmployeeDTO empl = new EmployeeDTO();
		return empl;
	}
	
	@GetMapping(value="/api/employees/{id}/attendance")
	public AttendanceDTO getEmployeeAttendace(@PathVariable int EmployeeId) {
		AttendanceDTO attendance = new AttendanceDTO();
		return attendance;
	}
	
	@GetMapping(value="/api/employees/{id}/leave")
	public LeaveDTO getEmployeeLeave(@PathVariable int EmployeeId) {
		LeaveDTO leave = new LeaveDTO();
		return leave;
	}
	
	
	@PostMapping(value="api/employees/")
	public void createEmployee(@RequestBody EmployeeDTO dto) {
	}
	
	@PostMapping(value="/api/employees/{id}/assign-department/{deptId}")
	public void assignDepartment(@PathVariable int EmployeeId,
								 @PathVariable int deptId) {
		
	}
	
	@PostMapping(value="/api/employees/{id}/assign-shift/{shiftId}")
	public void assignShift(@PathVariable int EmployeeId,
							@PathVariable int shiftId) {
		
	}
	
	
	@PutMapping(value="/api/employees/{id}")
	public void updateEmployeeInfo(@PathVariable int EmployeeId,
								   @RequestBody EmployeeDTO dto	) {
	}
	
	@DeleteMapping(value="/api/employees/{id}")
	public void deleteEmployee(@PathVariable int EmployeeId) {
		
	}
	
}
