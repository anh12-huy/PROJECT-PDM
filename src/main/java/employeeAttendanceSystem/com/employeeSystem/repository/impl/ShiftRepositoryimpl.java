package employeeAttendanceSystem.com.employeeSystem.repository.impl;

import java.sql.*;
import java.util.*;

import employeeAttendanceSystem.com.employeeSystem.repository.ShiftRepository;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.EmployeeEntity;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.SexType;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.ShiftEntity;
import org.springframework.stereotype.Repository;

@Repository
public class ShiftRepositoryimpl implements ShiftRepository{
	private static final String DB_URL = "jdbc:mysql://localhost:3306/employee_attendance?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    @Override
    public List<ShiftEntity> geAtAllShift() {
        List<ShiftEntity> list = new ArrayList<>();
        String sql = "SELECT * FROM Shift";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ShiftEntity shift = mapShift(rs);
                list.add(shift);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Optional<ShiftEntity> getShiftById(int shiftId) {
        String sql = "SELECT * FROM Shift WHERE ShiftID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, shiftId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ShiftEntity shift = mapShift(rs);
                    return Optional.of(shift);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }


    @Override
    public boolean createShift(ShiftEntity entity) {
        String sql = "INSERT INTO Shift (Shift_name, Start_time, End_time) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, entity.getShiftName());
            ps.setTime(2, entity.getStartTime());
            ps.setTime(3, entity.getEndTime());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateShift(int id, ShiftEntity entity) {
        String sql = "UPDATE Shift SET Shift_name = ?, Start_time = ?, End_time = ? WHERE ShiftID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, entity.getShiftName());
            ps.setTime(2, entity.getStartTime());
            ps.setTime(3, entity.getEndTime());
            ps.setInt(4, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteShift(int id) {
        String sql = "DELETE FROM Shift WHERE ShiftID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean assignEmployeToShift(int shiftId, int employeeId) {
        String sql = "INSERT INTO Shift (ShiftID, EmployeeID) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, shiftId);
            ps.setInt(2, employeeId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<EmployeeEntity> getAllEmployeeInShift(int shiftId) {
        List<EmployeeEntity> list = new ArrayList<>();
        String sql = "SELECT e.* FROM Employee e JOIN attendance a on a.EmployeeID=e.EmployeeID join Shift s ON a.ShiftID = s.ShiftID WHERE s.ShiftID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, shiftId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                EmployeeEntity emp = mapEmployee(rs);
                list.add(emp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<ShiftEntity> getAllShiftOfEmployee(int employeeId) {
        List<ShiftEntity> list = new ArrayList<>();
                String sql = "SELECT s.* FROM Shift s JOIN attendance a on a.ShiftID = s.ShiftID join Employee e ON a.EmployeeID=e.EmployeeID WHERE e.EmployeeID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ShiftEntity shift = mapShift(rs);
                list.add(shift);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
 
    private ShiftEntity mapShift(ResultSet rs) throws SQLException {
        ShiftEntity shift = new ShiftEntity();
        shift.setShiftID(rs.getInt("ShiftID"));
        shift.setShiftName(rs.getString("Shift_name"));
        shift.setStartTime(rs.getTime("Start_time"));
        shift.setEndTime(rs.getTime("End_time"));
        return shift;
    }

    private EmployeeEntity mapEmployee(ResultSet rs) throws SQLException {
        EmployeeEntity emp = new EmployeeEntity();
        emp.setEmployeeID(rs.getInt("EmployeeID"));
        emp.setName(rs.getString("Name"));
        emp.setSsn(rs.getString("Ssn"));
        emp.setAddress(rs.getString("Address"));
        emp.setEmail(rs.getString("Email"));
        emp.setSalary(rs.getDouble("Salary"));

        // null-safe enum mapping
        String sexStr = rs.getString("Sex");
        emp.setSex(sexStr != null ? SexType.valueOf(sexStr.trim().toUpperCase()) : null);

        emp.setBirthday(rs.getDate("Birthday"));
        emp.setDepartmentID(rs.getInt("DepartmentID"));
        emp.setUserID(rs.getInt("UserID"));
        emp.setCreated_at(rs.getTimestamp("created_at"));

        return emp;
    }
}
