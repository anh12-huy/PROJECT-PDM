package employeeAttendanceSystem.com.employeeSystem.repository.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import employeeAttendanceSystem.com.employeeSystem.repository.LeaveTypeRepository;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.LeaveTypeEntity;
import org.springframework.stereotype.Repository;

@Repository
public class LeaveTypeRepositoryimpl implements LeaveTypeRepository {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/employee_attendance?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    @Override
    public List<LeaveTypeEntity> getAllLeaveType() {
        List<LeaveTypeEntity> list = new ArrayList<>();
        String sql = "SELECT * FROM LeaveType";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Optional<LeaveTypeEntity> getLeaveTypeById(int id) {
        String sql = "SELECT * FROM LeaveType WHERE LeaveTypeID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public boolean createLeaveType(LeaveTypeEntity entity) {
        // Không set LeaveTypeID nếu DB là auto_increment
        String sql = "INSERT INTO LeaveType (LeaveType_name, MaxDaysAllowed) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, entity.getLeaveType_name());
            ps.setInt(2, entity.getMaxDaysAllowed());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        entity.setLeaveTypeID(generatedKeys.getInt(1)); // set ID trả về từ DB
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateLeaveType(int id, LeaveTypeEntity entity) {
        String sql = "UPDATE LeaveType SET LeaveType_name = ?, MaxDaysAllowed = ? WHERE LeaveTypeID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, entity.getLeaveType_name());
            ps.setInt(2, entity.getMaxDaysAllowed());
            ps.setInt(3, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteLeaveType(int id) {
        String sql = "DELETE FROM LeaveType WHERE LeaveTypeID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // --- private helper method ---
    private LeaveTypeEntity mapRow(ResultSet rs) throws SQLException {
        LeaveTypeEntity entity = new LeaveTypeEntity();
        entity.setLeaveTypeID(rs.getInt("LeaveTypeID"));
        entity.setLeaveType_name(rs.getString("LeaveType_name"));
        entity.setMaxDaysAllowed(rs.getInt("MaxDaysAllowed"));
        return entity;
    }
}
