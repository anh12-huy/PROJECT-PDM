package employeeAttendanceSystem.com.employeeSystem.controller;

import employeeAttendanceSystem.com.employeeSystem.service.HolidayService;
import employeeAttendanceSystem.com.employeeSystem.model.HolidayRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/holidays")
@RequiredArgsConstructor
public class HolidayController {
    private final HolidayService holidayService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(holidayService.getAllHolidays());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return ResponseEntity.ok(holidayService.getHolidayById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<?> create(@RequestBody HolidayRequestDTO holiday) {
        return ResponseEntity.ok(holidayService.createHoliday(holiday));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody HolidayRequestDTO holiday) {
        return ResponseEntity.ok(holidayService.updateHoliday(id, holiday));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<?> delete(@PathVariable int id) {
        boolean success = holidayService.deleteHoliday(id);
        return success ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
    }

    @GetMapping("/check")
    public ResponseEntity<?> checkHoliday(@RequestParam("date") String date) {
        try {
            java.sql.Date sqlDate = java.sql.Date.valueOf(date);
            boolean isHoliday = holidayService.isHoliday(sqlDate);
            return ResponseEntity.ok(isHoliday ? "Is holiday" : "Not a holiday");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid date format");
        }
    }

    @PostMapping("/sync-attendance")
    public ResponseEntity<?> syncAttendance(@RequestParam int holidayId) {
        boolean success = holidayService.syncAttendanceStatusForHoliday(holidayId);
        return ResponseEntity.ok(success ? "Attendance synced" : "Failed to sync attendance");
    }
}
