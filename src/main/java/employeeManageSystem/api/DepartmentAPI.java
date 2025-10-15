package employeeManageSystem.api;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import employeeManageSystem.dto.DepartmentDTO;
import employeeManageSystem.dto.EmployeeDTO;

@RestController
public class DepartmentAPI {
	@GetMapping(value="/api/departments")
	public List<DepartmentDTO> getAllDepartment(){
		List<DepartmentDTO> result=new ArrayList<>();
		return result;
	}
	
	@GetMapping(value="/api/departments/{id}")
	public DepartmentDTO getDepartment(@PathVariable int deptId) {
		return new DepartmentDTO();
	}
	
	@GetMapping(value="/api/departments/{deptId}/employees")
	public List<EmployeeDTO> getAllEmployeeOfDepartment(@PathVariable int deptId){
		List<EmployeeDTO> result=new ArrayList<>();
		return result;
	}
	
	@PostMapping(value="/api/departments")
	public void createDepartment(@RequestBody DepartmentDTO dto) {
	}
	
	@PostMapping(value="/api/departments/{deptId}/assign-manager/{employeeId}")
	public void assignManager(@PathVariable int deptId,
							  @PathVariable String employeeId) {
		
	}
	
	
	@PutMapping(value="/api/departments/{id}")
	public void updateDepartment(@RequestBody DepartmentDTO dto) {
		
	}
	
	
	@DeleteMapping(value="/api/departments/{id}")
	public void deleteDepartment(@PathVariable int deptId) {
		
	}
}
