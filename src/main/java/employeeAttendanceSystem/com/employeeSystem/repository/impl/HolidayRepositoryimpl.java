package employeeAttendanceSystem.com.employeeSystem.repository.impl;

import employeeAttendanceSystem.com.employeeSystem.repository.HolidayRepository;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.HolidayEntity;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class HolidayRepositoryimpl implements HolidayRepository {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/employee_attendance?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    @Override
    public List<HolidayEntity> getAllHoliday() {
        String sql = "SELECT * FROM Holiday";
        List<HolidayEntity> result = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                HolidayEntity holiday = new HolidayEntity();
                holiday.setHolidayID(rs.getInt("HolidayID"));
                holiday.setHolidayName(rs.getString("HolidayName"));
                holiday.setHolidayDate(rs.getDate("HolidayDate"));
                holiday.setDescription(rs.getString("Description"));
                result.add(holiday);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Optional<HolidayEntity> getHolidayById(int holidayId) {
        String sql = "SELECT * FROM Holiday WHERE HolidayID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, holidayId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    HolidayEntity holiday = new HolidayEntity();
                    holiday.setHolidayID(rs.getInt("HolidayID"));
                    holiday.setHolidayName(rs.getString("HolidayName"));
                    holiday.setHolidayDate(rs.getDate("HolidayDate"));
                    holiday.setDescription(rs.getString("Description"));
                    return Optional.of(holiday);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public boolean createHolidayRecord(HolidayEntity holiday) {
        String sql = "INSERT INTO Holiday (HolidayName, HolidayDate, Description) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (holiday.getHolidayName() != null) {
                ps.setString(1, holiday.getHolidayName());
            } else {
                ps.setNull(1, Types.VARCHAR);
            }

            ps.setDate(2, holiday.getHolidayDate());

            if (holiday.getDescription() != null) {
                ps.setString(3, holiday.getDescription());
            } else {
                ps.setNull(3, Types.VARCHAR);
            }

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateHolidayRecord(int id, HolidayEntity entity) {
        String sql = "UPDATE Holiday SET HolidayName = ?, HolidayDate = ?, Description = ? WHERE HolidayID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (entity.getHolidayName() != null) {
                ps.setString(1, entity.getHolidayName());
            } else {
                ps.setNull(1, Types.VARCHAR);
            }

            ps.setDate(2, entity.getHolidayDate());

            if (entity.getDescription() != null) {
                ps.setString(3, entity.getDescription());
            } else {
                ps.setNull(3, Types.VARCHAR);
            }

            ps.setInt(4, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteHolidayRecord(int holidayId) {
        String sql = "DELETE FROM Holiday WHERE HolidayID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, holidayId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean checkHoliday(Date date) {
        String sql = "SELECT COUNT(*) FROM Holiday WHERE HolidayDate = ?";
        boolean exist = false;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, date);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    exist = rs.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exist;
    }
}
