package employeeAttendanceSystem.com.employeeSystem.controller;

import employeeAttendanceSystem.com.employeeSystem.service.DepartmentService;
import employeeAttendanceSystem.com.employeeSystem.model.DepartmentAssignmentDTO;
import employeeAttendanceSystem.com.employeeSystem.model.DepartmentRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(required = false) String location) {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DepartmentRequestDTO department) {
        return ResponseEntity.ok(departmentService.createDepartment(department));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody DepartmentRequestDTO department) {
        return ResponseEntity.ok(departmentService.updateDepartment(id, department));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{deptId}/assign-manager/{employeeId}")
    public ResponseEntity<?> assignManager(@PathVariable int deptId, @PathVariable int employeeId) {
        DepartmentAssignmentDTO assignment = new DepartmentAssignmentDTO();
        assignment.setManagerId(employeeId); // gán ID vào
        boolean success = departmentService.assignManager(deptId, assignment);
        return ResponseEntity.ok(success ? "Manager assigned" : "Failed to assign manager");
    }

    @GetMapping("/{deptId}/employees")
    public ResponseEntity<?> getEmployees(@PathVariable int deptId) {
        return ResponseEntity.ok(departmentService.getEmployeesByDepartment(deptId));
    }
}
