package employeeManageSystem.api;
import org.springframework.web.bind.annotation.*;

import employeeManageSystem.model.EmployeeDTO;
import employeeManageSystem.model.ShiftDTO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ShiftAPI {
	@GetMapping(value="/api/shifts")
	public List<ShiftDTO> getAllShift() {
		List<ShiftDTO> result=new ArrayList<>();
		return result;
	}
	
	@GetMapping(value="/api/shifts/{id}")
	public ShiftDTO getShift(@PathVariable String shiftId) {
		return new ShiftDTO();
	}
	
	@GetMapping(value="/api/shifts/{shiftId}/employees")
	public List<EmployeeDTO> getEmployeeOfShift(@PathVariable String shiftId) {
		List<EmployeeDTO> result=new ArrayList<>();
		return result;
	}
	
	@GetMapping(value="/api/shifts/schedule")
	public List<ShiftDTO> getShiftFromDate(@RequestParam(value="Date") Date date) {
		List<ShiftDTO> result=new ArrayList<>();
		return result;
	}
	
	@PostMapping(value="/api/shifts")
	public void createShift(@RequestBody ShiftDTO dto) {
		
	}
	
	@PostMapping(value="/api/shifts/{shiftId}/assign-employee/{employeeId}")
	public void assignEmployeeToShift(@PathVariable String shiftId,
									  @PathVariable String employeeId) {
		
	}
	
	@PutMapping(value="/api/shifts/{id}")
	public void updateShift(@RequestBody ShiftDTO dto,
							@PathVariable String shiftId) {
		
	}
	
	@DeleteMapping(value="/api/shifts/{id}")
	public void deleteShift(@RequestBody ShiftDTO dto,
							@PathVariable String shiftId) {
		
	}
	
	
}
