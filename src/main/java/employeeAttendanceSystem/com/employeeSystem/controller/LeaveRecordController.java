package employeeAttendanceSystem.com.employeeSystem.controller;

import employeeAttendanceSystem.com.employeeSystem.model.LeaveRecordRequestDTO;
import employeeAttendanceSystem.com.employeeSystem.service.LeaveRecordService;
import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/leaves")
@RequiredArgsConstructor
public class LeaveRecordController {

    private final LeaveRecordService leaveRecordService;

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {

        return ResponseEntity.ok(leaveRecordService.getAllLeaveRecords());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return ResponseEntity.ok(leaveRecordService.getLeaveRecordById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody LeaveRecordRequestDTO request) {
        return ResponseEntity.ok(leaveRecordService.createLeaveRecord(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id,
                                    @RequestBody LeaveRecordRequestDTO request) {

        return ResponseEntity.ok(leaveRecordService.updateLeaveRecord(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        leaveRecordService.deleteLeaveRecord(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<?> approve(@PathVariable int id) {
       boolean success = leaveRecordService.approveLeaveRecord(id);
        if (success) {
            return ResponseEntity.ok(Map.of("message", "Leave approved"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(Map.of("message", "Failed to approve leave"));
        }
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<?> reject(@PathVariable int id) {
        boolean success = leaveRecordService.denyLeaveRecord(id);
        return ResponseEntity.ok(success ? "Leave rejected" : "Failed to reject leave");
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<?> getByEmployee(@PathVariable int employeeId) {
        return ResponseEntity.ok(leaveRecordService.getLeaveRecordsByEmployee(employeeId));
    }

    @GetMapping("/pending")
    public ResponseEntity<?> getPending() {
        return ResponseEntity.ok(leaveRecordService.getAllPendingLeaveRecords());
    }
}
