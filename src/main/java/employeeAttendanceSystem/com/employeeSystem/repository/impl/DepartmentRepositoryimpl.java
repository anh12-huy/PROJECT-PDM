package employeeAttendanceSystem.com.employeeSystem.repository.impl;

import employeeAttendanceSystem.com.employeeSystem.repository.DepartmentRepository;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.DepartmentEntity;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.EmployeeEntity;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.SexType;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DepartmentRepositoryimpl implements DepartmentRepository {
		static final String DB_URL="jdbc:mysql://localhost:3306/employee_attendance?useSSL=false&serverTimezone=UTC";
		static final String USER="root";
		static final String PASSWORD="root";
		@Override
	    public boolean create(DepartmentEntity dept) {
	       String sql = "INSERT INTO Department (Department_name, Location, ManagerEmployeeID, created_at, updated_at) "
           + "VALUES (?, ?, ?, ?, ?)";

	        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	            ps.setString(1, dept.getDepartmentName());
				ps.setString(2, dept.getLocation());

				if (dept.getManagerEmployeeId() != null) {
					ps.setInt(3, dept.getManagerEmployeeId());
				} else {
					ps.setNull(3, java.sql.Types.INTEGER);
				}

				ps.setTimestamp(4, dept.getCreatedAt());
				ps.setTimestamp(5, dept.getUpdatedAt());

	            return ps.executeUpdate() > 0;

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return false;
	    }


		@Override
		public Optional<DepartmentEntity> findById(int id) {
		    String sql = "SELECT * FROM Department WHERE DepartmentID = ?";

		    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		         PreparedStatement ps = conn.prepareStatement(sql)) {

		        ps.setInt(1, id);

		        try (ResultSet rs = ps.executeQuery()) {
		            if (rs.next()) {
		                DepartmentEntity dept = new DepartmentEntity();

		                dept.setDepartmentId(rs.getInt("DepartmentID"));
		                dept.setDepartmentName(rs.getString("Department_name"));
		                dept.setLocation(rs.getString("Location"));
		                dept.setManagerEmployeeId(rs.getInt("ManagerEmployeeID"));

		                Timestamp created = rs.getTimestamp("created_at");
		                Timestamp updated = rs.getTimestamp("updated_at");

		                dept.setCreatedAt(created != null ? created : null);
		                dept.setUpdatedAt(updated != null ? updated : null);

		                return Optional.of(dept); 
		            }
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return Optional.empty(); 
		}



	    @Override
	    public List<DepartmentEntity> findAll() {
	        List<DepartmentEntity> list = new ArrayList<>();
	        String sql = "SELECT * FROM Department";

	        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
	             PreparedStatement ps = conn.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {

	            while (rs.next()) {
	                DepartmentEntity dept = new DepartmentEntity();
	                dept.setDepartmentId(rs.getInt("DepartmentID"));
	                dept.setDepartmentName(rs.getString("Department_name"));
	                dept.setLocation(rs.getString("Location"));
	                dept.setManagerEmployeeId(rs.getInt("ManagerEmployeeID"));

	                Timestamp created = rs.getTimestamp("created_at");
	                Timestamp updated = rs.getTimestamp("updated_at");

	                dept.setCreatedAt(created != null ? created : null);
	                dept.setUpdatedAt(updated != null ? updated : null);

	                list.add(dept);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return list;
	    }


	    @Override
	    public boolean update(int id, DepartmentEntity dept) {
	        String sql = """
	                UPDATE Department 
	                SET Department_name = ?, 
	                    Location = ?, 
	                    ManagerEmployeeID = ?, 
	                    updated_at = ?
	                WHERE DepartmentID = ?
	                """;

	        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	            ps.setString(1, dept.getDepartmentName());
	            ps.setString(2, dept.getLocation());
	            if(dept.getManagerEmployeeId() != 0){
					ps.setInt(3, dept.getManagerEmployeeId());
				} else {
					ps.setNull(3, java.sql.Types.INTEGER);
				}

	            ps.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));

	            ps.setInt(5, id);

	            return ps.executeUpdate() > 0;

	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }


	    @Override
	    public boolean delete(int id) {
	        String sql = "DELETE FROM Department WHERE DepartmentID = ?";

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
	    public List<EmployeeEntity> findEmployeesByDepartment(int departmentId) {
	        String sql = """
	                SELECT e.* 
	                FROM Employee e 
	                WHERE e.DepartmentID = ?
	                """;

	        List<EmployeeEntity> list = new ArrayList<>();

	        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	            ps.setInt(1, departmentId);
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	                EmployeeEntity employee = new EmployeeEntity();
	                employee.setEmployeeID(rs.getInt("EmployeeID"));
	                employee.setName(rs.getString("Name"));
	                employee.setSsn(rs.getString("Ssn"));
	                employee.setAddress(rs.getString("Address"));
	                employee.setEmail(rs.getString("Email"));
	                employee.setSalary(rs.getDouble("Salary"));
	                String sexStr = rs.getString("Sex");
	                employee.setSex(sexStr != null ? SexType.valueOf(sexStr.toUpperCase()) : null);
	                employee.setBirthday(rs.getDate("Birthday"));
	                employee.setDepartmentID(rs.getInt("DepartmentID"));
	                employee.setUserID(rs.getInt("UserID"));

	                Timestamp created = rs.getTimestamp("Created_at");
	                employee.setCreated_at(created);

	                list.add(employee);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return list;
	    }


	    @Override
	    public boolean assignManager(int departmentId, int employeeId) {
	        String sql = "UPDATE Department SET ManagerEmployeeID = ? WHERE DepartmentID = ?";

	        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	            if(employeeId != 0){
					ps.setInt(1, employeeId);
				} else {
					ps.setNull(1, java.sql.Types.INTEGER);
				}
	            ps.setInt(2, departmentId);

	            return ps.executeUpdate() > 0;

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return false;
	    }
	}
