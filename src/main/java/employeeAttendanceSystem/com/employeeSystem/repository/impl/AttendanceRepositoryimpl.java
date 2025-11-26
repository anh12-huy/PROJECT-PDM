package employeeAttendanceSystem.com.employeeSystem.repository.impl;

import employeeAttendanceSystem.com.employeeSystem.repository.AttendanceRepository;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.AttendanceEntity;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.AttendanceStatus;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AttendanceRepositoryimpl implements AttendanceRepository {

    private static final String DB_URL =
            "jdbc:mysql://localhost:3306/employee_attendance?useSSL=false&serverTimezone=UTC";

    private static final String USER = "root";
    private static final String PASSWORD = "root";
    @Override
    public Optional<AttendanceEntity> findByEmployeeAndDate(int employeeId, Date date) {
        String sql = "SELECT * FROM Attendance WHERE EmployeeID = ? AND WorkDate = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, employeeId);
            ps.setDate(2, date);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Optional.of(mapRow(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    @Override
    public boolean save(AttendanceEntity attendance) {
        String sql = """
                INSERT INTO Attendance (EmployeeID, WorkDate, ShiftID, CheckIn, CheckOut, Status)
                VALUES (?, ?, ?, ?, ?, ?)
                ON DUPLICATE KEY UPDATE
                   ShiftID = VALUES(ShiftID),
                   CheckIn = VALUES(CheckIn),
                   CheckOut = VALUES(CheckOut),
                   Status = VALUES(Status)
                """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, attendance.getEmployeeID());
            ps.setDate(2, attendance.getWorkDate());

            if (attendance.getShiftID() != 0) {
                ps.setInt(3, attendance.getShiftID());
            } else {
                ps.setNull(3, Types.INTEGER);
            }

            ps.setTimestamp(4, attendance.getCheckIn());
            ps.setTimestamp(5, attendance.getCheckOut());

            ps.setString(6, attendance.getStatus() != null ? attendance.getStatus().name() : null);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean checkIn(int employeeId, int shiftId) {
        String sql = """
            INSERT INTO Attendance (EmployeeID, WorkDate, ShiftID, CheckIn, Status)
            VALUES (?, CURDATE(), ?, NOW(), 'Present')
            ON DUPLICATE KEY UPDATE
                CheckIn = IF(CheckIn IS NULL, NOW(), CheckIn),
                ShiftID = VALUES(ShiftID),
                Status = 'Present'
            """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, employeeId);
            ps.setInt(2, shiftId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean checkOut(int employeeId) {
        String sql = """
                UPDATE Attendance
                SET CheckOut = NOW()
                WHERE EmployeeID = ? AND WorkDate = CURDATE() AND CheckOut IS NULL
                """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, employeeId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Optional<AttendanceEntity> findOpenCheckInByEmployeeId(int employeeId) {
        String sql = """
                SELECT * FROM Attendance
                WHERE EmployeeID = ? AND WorkDate = CURDATE()
                """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, employeeId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                AttendanceEntity at = new AttendanceEntity();
                at.setAttendanceID(rs.getInt("AttendanceID"));
                at.setEmployeeID(rs.getInt("EmployeeID"));
                at.setShiftID(rs.getInt("ShiftID"));
                at.setCheckIn(rs.getTimestamp("CheckIn"));
                at.setCheckOut(rs.getTimestamp("CheckOut"));
                String status = rs.getString("Status");
                at.setStatus(status != null ? AttendanceStatus.valueOf(status.toUpperCase()) : null);
                return Optional.of(at);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<AttendanceEntity> findAllByEmployeeId(int employeeId) {
        String sql = "SELECT * FROM Attendance WHERE EmployeeID = ? ORDER BY WorkDate DESC";
        List<AttendanceEntity> list = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, employeeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<AttendanceEntity> getByDate(Date date) {
        String sql = "SELECT * FROM Attendance WHERE WorkDate = ? ORDER BY EmployeeID";
        List<AttendanceEntity> list = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, date);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Optional<AttendanceStatus> getAttendanceStatus(int emplId) {
        String sql = "SELECT Status FROM Attendance WHERE EmployeeID = ? AND WorkDate = CURDATE()";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, emplId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String statusStr = rs.getString("Status");
                    if (statusStr != null) {
                        return Optional.of(AttendanceStatus.valueOf(statusStr.toUpperCase()));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public boolean autoMarkAbsent(Date date) {
        String sql = """
                INSERT INTO Attendance (EmployeeID, WorkDate, Status)
                SELECT e.EmployeeID, ?, 'Absent'
                FROM Employee e
                WHERE NOT EXISTS (
                   SELECT 1 FROM Attendance a
                   WHERE a.EmployeeID = e.EmployeeID AND a.WorkDate = ?
                )
                """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, date);
            ps.setDate(2, date);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Hàm mapRow null-safe
    private AttendanceEntity mapRow(ResultSet rs) throws SQLException {
        AttendanceEntity a = new AttendanceEntity();

        a.setAttendanceID(rs.getInt("AttendanceID"));
        a.setEmployeeID(rs.getInt("EmployeeID"));

        a.setWorkDate(rs.getDate("WorkDate"));

        int shiftId = rs.getInt("ShiftID");
        a.setShiftID(rs.wasNull() ? null : shiftId);

        Timestamp in = rs.getTimestamp("CheckIn");
        a.setCheckIn(in);

        Timestamp out = rs.getTimestamp("CheckOut");
        a.setCheckOut(out);

        String status = rs.getString("Status");
        a.setStatus(status != null ? AttendanceStatus.valueOf(status.toUpperCase()) : null);

        Timestamp created = rs.getTimestamp("created_at");
        a.setCreated_at(created);

        return a;
    }
}




 