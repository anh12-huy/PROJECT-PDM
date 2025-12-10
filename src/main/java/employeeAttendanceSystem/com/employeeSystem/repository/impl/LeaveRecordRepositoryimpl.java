package employeeAttendanceSystem.com.employeeSystem.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import employeeAttendanceSystem.com.employeeSystem.repository.LeaveRecordRepository;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.LeaveRecordEntity;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.LeaveStatus;
import org.springframework.stereotype.Repository;
@Repository
public class LeaveRecordRepositoryimpl implements LeaveRecordRepository {
	static final String DB_URL="jdbc:mysql://localhost:3306/employee_attendance?useSSL=false&serverTimezone=UTC";
	static final String USER="root";
	static final String PASSWORD="root";
	private LeaveRecordEntity mapEntity(ResultSet rs) throws SQLException {
        LeaveRecordEntity entity = new LeaveRecordEntity();
        entity.setLeaveID(rs.getInt("LeaveID"));
        entity.setEmployeeID(rs.getInt("EmployeeID"));
        entity.setLeaveTypeID(rs.getInt("LeaveTypeID"));
        entity.setStart_date(rs.getDate("Start_date"));
        entity.setEnd_date(rs.getDate("End_date"));
        entity.setReason(rs.getString("Reason"));
        String statusStr = rs.getString("Status");
        entity.setStatus(statusStr != null ? LeaveStatus.valueOf(statusStr.toUpperCase()) : null);
        entity.setCreated_at(rs.getTimestamp("created_at"));
        return entity;
    }

    @Override
    public List<LeaveRecordEntity> getAllLeaveRecord() {
        String sql = "SELECT * FROM LeaveRecord";
        List<LeaveRecordEntity> list = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapEntity(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Optional<LeaveRecordEntity> getLeaveRecordById(int id) {
        String sql = "SELECT * FROM LeaveRecord WHERE LeaveID = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    LeaveRecordEntity entity = mapEntity(rs);
                    return Optional.of(entity);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return Optional.empty();
    }


    @Override
    public boolean createLeaveRecord(LeaveRecordEntity entity) {
        String sql = "INSERT INTO LeaveRecord (EmployeeID, LeaveTypeID, Start_date, End_date, Reason, Status, created_at) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, entity.getEmployeeID());
            stmt.setInt(2, entity.getLeaveTypeID());
            if (entity.getStart_date() != null) {
                stmt.setDate(3, new java.sql.Date(entity.getStart_date().getTime()));
            } else {
                stmt.setNull(3, java.sql.Types.DATE);
            }
            stmt.setDate(4, new java.sql.Date(entity.getEnd_date().getTime()));
            stmt.setString(5, entity.getReason());
            stmt.setString(6, entity.getStatus().name());
            stmt.setTimestamp(7, entity.getCreated_at());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateLeaveRecord(int id, LeaveRecordEntity entity) {
        String sql = "UPDATE LeaveRecord SET EmployeeID=?, LeaveTypeID=?, Start_date=?, End_date=?, Reason=?, Status=? "
                   + "WHERE LeaveID=?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, entity.getEmployeeID());
            stmt.setInt(2, entity.getLeaveTypeID());
            if (entity.getStart_date() != null) {
                stmt.setDate(3, new java.sql.Date(entity.getStart_date().getTime()));
            } else {
                stmt.setNull(3, java.sql.Types.DATE);
            }
            stmt.setDate(4, new java.sql.Date(entity.getEnd_date().getTime()));
            stmt.setString(5, entity.getReason());
            stmt.setString(6, entity.getStatus().name());
            stmt.setInt(7, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteLeaveRecord(int id) {
        String sql = "DELETE FROM LeaveRecord WHERE LeaveID=?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean approveLeaveRecord(int id) {
        String sql = "UPDATE LeaveRecord SET Status='APPROVED' WHERE LeaveID=?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean denyLeaveRecord(int id) {
        String sql = "UPDATE LeaveRecord SET Status='REJECTED' WHERE LeaveID=?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<LeaveRecordEntity> getLeaveRecordsOfEmployee(int emplId) {
        String sql = "SELECT * FROM LeaveRecord WHERE EmployeeID = ? ORDER BY created_at DESC";
        List<LeaveRecordEntity> list = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, emplId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapEntity(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    @Override
    public List<LeaveRecordEntity> getAllPendingLeaveRecord() {
        String sql = "SELECT * FROM LeaveRecord WHERE Status='PENDING'";
        List<LeaveRecordEntity> list = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapEntity(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

}
