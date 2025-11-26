package employeeAttendanceSystem.com.employeeSystem.repository.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class AttendanceEntity {

    private Integer AttendanceID;
    private Integer EmployeeID;
    private Date WorkDate;
    private Integer ShiftID;        // ✔ Integer để nhận null
    private Timestamp CheckIn;
    private Timestamp CheckOut;
    private AttendanceStatus Status;
    private Timestamp created_at;
    private Integer HolidayID;      // ✔ Integer

    public Integer getAttendanceID() {
        return AttendanceID;
    }

    public void setAttendanceID(Integer attendanceID) {
        AttendanceID = attendanceID;
    }

    public Integer getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        EmployeeID = employeeID;
    }

    public Date getWorkDate() {
        return WorkDate;
    }

    public void setWorkDate(Date workDate) {
        WorkDate = workDate;
    }

    public Integer getShiftID() {
        return ShiftID;
    }

    public void setShiftID(Integer shiftID) {
        ShiftID = shiftID;
    }

    public Timestamp getCheckIn() {
        return CheckIn;
    }

    public void setCheckIn(Timestamp checkIn) {
        CheckIn = checkIn;
    }

    public Timestamp getCheckOut() {
        return CheckOut;
    }

    public void setCheckOut(Timestamp checkOut) {
        CheckOut = checkOut;
    }

    public AttendanceStatus getStatus() {
        return Status;
    }

    public void setStatus(AttendanceStatus status) {
        Status = status;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Integer getHolidayID() {
        return HolidayID;
    }

    public void setHolidayID(Integer holidayID) {
        HolidayID = holidayID;
    }
}
