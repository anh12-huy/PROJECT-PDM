package employeeAttendanceSystem.com.employeeSystem.controller;

import employeeAttendanceSystem.com.employeeSystem.service.EmployeeService;
import employeeAttendanceSystem.com.employeeSystem.model.AttendanceResponseDTO;
import employeeAttendanceSystem.com.employeeSystem.model.LeaveRecordResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> getAllEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String sex,
            @RequestParam(required = false) String department) {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable int id) {
        return ResponseEntity.ok(employeeService.getEmployeeDetails(id));
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody employeeAttendanceSystem.com.employeeSystem.model.EmployeeRequestDTO emp) {
        return ResponseEntity.ok(employeeService.createEmployee(emp));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable int id, @RequestBody employeeAttendanceSystem.com.employeeSystem.model.EmployeeRequestDTO emp) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, emp));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/attendance")
    public ResponseEntity<List<AttendanceResponseDTO>> getEmployeeAttendance(@PathVariable int id) {
        return ResponseEntity.ok(employeeService.getAttendanceHistory(id));
    }

    @GetMapping("/{id}/leaves")
    public ResponseEntity<List<LeaveRecordResponseDTO>> getEmployeeLeaves(@PathVariable int id) {
        return ResponseEntity.ok(employeeService.getLeaveHistory(id));
    }

    @PostMapping("/{id}/assign-department/{deptId}")
    public ResponseEntity<Void> assignDepartment(@PathVariable int id, @PathVariable int deptId) {
        employeeService.assignDepartment(id, deptId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/assign-shift/{shiftId}")
    public ResponseEntity<Void> assignShift(@PathVariable int id, @PathVariable int shiftId) {
        employeeService.assignShift(id, shiftId);
        return ResponseEntity.ok().build();
    }
}