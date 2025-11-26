package employeeAttendanceSystem.com.employeeSystem.repository.impl;

import java.sql.*;
import java.util.Optional;

import employeeAttendanceSystem.com.employeeSystem.repository.UserLoginRepository;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.RoleType;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.UserLoginEntity;
import org.springframework.stereotype.Repository;

@Repository
public class UserLoginRepositoryimpl implements UserLoginRepository{

    private static final String DB_URL = "jdbc:mysql://localhost:3306/employee_attendance?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    @Override
    public Optional<UserLoginEntity> getUserById(int id) {

        String sql = "SELECT * FROM UserLogin WHERE UserID = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapUser(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserLoginEntity> getUserByUsername(String username) {

        String sql = "SELECT * FROM UserLogin WHERE Username = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapUser(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

 
    @Override
    public boolean createUser(UserLoginEntity entity) {

        String sql = """
                INSERT INTO UserLogin(Username, PasswordHash, Role, Last_login, created_at)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, entity.getUsername());
            ps.setString(2, entity.getPasswordHash());
            ps.setString(3, entity.getRole().name());
            ps.setTimestamp(4, entity.getLastLogin());
            ps.setTimestamp(5, entity.getCreatedAt()); 
            
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public boolean updateUser(int id, UserLoginEntity entity) {

        String sql = """
                UPDATE UserLogin 
                SET Username = ?, PasswordHash = ?, Role = ?, Last_login = ?
                WHERE UserID = ?
                """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, entity.getUsername());
            ps.setString(2, entity.getPasswordHash());
            ps.setString(3, entity.getRole().name());
            ps.setTimestamp(4, entity.getLastLogin());
            ps.setInt(5, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public boolean deleteUser(int id) {
        String sql = "DELETE FROM UserLogin WHERE UserID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private UserLoginEntity mapUser(ResultSet rs) throws SQLException {
        UserLoginEntity user = new UserLoginEntity();

        user.setUserID(rs.getInt("UserID"));
        user.setUsername(rs.getString("Username"));
        user.setPasswordHash(rs.getString("PasswordHash")); 
        String roleStr = rs.getString("Role");
        user.setRole(roleStr != null ? RoleType.valueOf(roleStr.toUpperCase()) : null);
        user.setLastLogin(rs.getTimestamp("Last_login")); 
        user.setCreatedAt(rs.getTimestamp("created_at")); 

        return user;
    }
}