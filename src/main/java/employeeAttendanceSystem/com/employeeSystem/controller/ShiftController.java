package employeeAttendanceSystem.com.employeeSystem.controller;

import employeeAttendanceSystem.com.employeeSystem.service.ShiftService;
import employeeAttendanceSystem.com.employeeSystem.model.ShiftRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/shifts")
@RequiredArgsConstructor
public class ShiftController {
    private final ShiftService shiftService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(shiftService.getAllShifts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return ResponseEntity.ok(shiftService.getShiftById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<?> create(@RequestBody ShiftRequestDTO shift) {
        return ResponseEntity.ok(shiftService.createShift(shift));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody ShiftRequestDTO shift) {
        return ResponseEntity.ok(shiftService.updateShift(id, shift));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<?> delete(@PathVariable int id) {
        boolean success = shiftService.deleteShift(id);
        return success ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
    }

    @PostMapping("/{shiftId}/assign-employee/{employeeId}")
    public ResponseEntity<?> assignEmployee(@PathVariable int shiftId, @PathVariable int employeeId) {
        boolean success = shiftService.assignEmployeeToShift(shiftId, employeeId);
        return ResponseEntity.ok(success ? "Employee assigned to shift" : "Failed to assign employee");
    }

    @GetMapping("/{shiftId}/employees")
    public ResponseEntity<?> getEmployeesInShift(@PathVariable int shiftId) {
        return ResponseEntity.ok(shiftService.getEmployeesInShift(shiftId));
    }

    @GetMapping("/{employeeId}/schedule")
    public ResponseEntity<?> getScheduleByDate(@PathVariable int employeeId) {
        return ResponseEntity.ok(shiftService.getEmployeeSchedule(employeeId));
    }
}
