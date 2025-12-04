package employeeAttendanceSystem.com.employeeSystem.controller;

import employeeAttendanceSystem.com.employeeSystem.service.EmployeeService;
import employeeAttendanceSystem.com.employeeSystem.model.AttendanceResponseDTO;
import employeeAttendanceSystem.com.employeeSystem.model.LeaveRecordResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<?> getAllEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String sex,
            @RequestParam(required = false) String department) {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or (hasRole('EMPLOYEE') and @securityContextService.getCurrentUserId() == #id)")
    public ResponseEntity<?> getEmployee(@PathVariable int id) {
        return ResponseEntity.ok(employeeService.getEmployeeDetails(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<?> createEmployee(@RequestBody employeeAttendanceSystem.com.employeeSystem.model.EmployeeRequestDTO emp) {
        return ResponseEntity.ok(employeeService.createEmployee(emp));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<?> updateEmployee(@PathVariable int id, @RequestBody employeeAttendanceSystem.com.employeeSystem.model.EmployeeRequestDTO emp) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, emp));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/attendance")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or (hasRole('EMPLOYEE') and @securityContextService.getCurrentUserId() == #id)")
    public ResponseEntity<List<AttendanceResponseDTO>> getEmployeeAttendance(@PathVariable int id) {
        return ResponseEntity.ok(employeeService.getAttendanceHistory(id));
    }

    @GetMapping("/{id}/leaves")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or (hasRole('EMPLOYEE') and @securityContextService.getCurrentUserId() == #id)")
    public ResponseEntity<List<LeaveRecordResponseDTO>> getEmployeeLeaves(@PathVariable int id) {
        return ResponseEntity.ok(employeeService.getLeaveHistory(id));
    }

    @PostMapping("/{id}/assign-department/{deptId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<Void> assignDepartment(@PathVariable int id, @PathVariable int deptId) {
        employeeService.assignDepartment(id, deptId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/assign-shift/{shiftId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<Void> assignShift(@PathVariable int id, @PathVariable int shiftId) {
        employeeService.assignShift(id, shiftId);
        return ResponseEntity.ok().build();
    }
}
