package employeeAttendanceSystem.com.employeeSystem.repository.impl;

import java.util.*;

import java.sql.*;

import employeeAttendanceSystem.com.employeeSystem.repository.EmployeeRepository;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.AttendanceEntity;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.AttendanceStatus;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.LeaveStatus;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.EmployeeEntity;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.LeaveRecordEntity;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.SexType;
import org.springframework.stereotype.Repository;


@Repository
public class EmployeeRepositoryimpl implements EmployeeRepository {
	static final String DB_URL="jdbc:mysql://localhost:3306/employee_attendance?useSSL=false&serverTimezone=UTC";
	static final String USER="root";
	static final String PASSWORD="root";
	@Override
	public List<EmployeeEntity> getAllEmployee() {
		String sql="select * from Employee";
		List<EmployeeEntity> result=new ArrayList<>();
		try(Connection conn=DriverManager.getConnection(DB_URL,USER,PASSWORD);
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);){
			while(rs.next()) {
				EmployeeEntity employee=new EmployeeEntity();
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
				employee.setCreated_at(rs.getTimestamp("Created_at"));
				result.add(employee);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Optional<EmployeeEntity> getEmployeeInformation(int id) {
	    String sql = "SELECT * FROM Employee WHERE EmployeeID = ?";

	    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, id);

	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {

	                EmployeeEntity employee = new EmployeeEntity();

	                employee.setEmployeeID(rs.getInt("EmployeeID"));
	                employee.setName(rs.getString("name"));
	                employee.setSsn(rs.getString("Ssn"));
	                employee.setAddress(rs.getString("Address"));
	                employee.setEmail(rs.getString("Email"));
	                employee.setSalary(rs.getDouble("Salary"));

	                String sexStr = rs.getString("Sex");
	                employee.setSex(sexStr != null ? SexType.valueOf(sexStr.toUpperCase()) : null);

	                employee.setBirthday(rs.getDate("Birthday"));
	                employee.setDepartmentID(rs.getInt("DepartmentID"));
	                employee.setUserID(rs.getInt("UserID"));
	                employee.setCreated_at(rs.getTimestamp("Created_at"));

	                return Optional.of(employee);
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return Optional.empty();
	}


	@Override
public boolean createEmployeeRecord(EmployeeEntity employee) {
    String sql = "INSERT INTO employee " +
            "(Name, Ssn, Address, Email, Salary, Sex, Birthday, DepartmentID, UserID, created_at) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
         PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        pstmt.setString(1, employee.getName());
        pstmt.setString(2, employee.getSsn());
        pstmt.setString(3, employee.getAddress());
        pstmt.setString(4, employee.getEmail());
        pstmt.setDouble(5, employee.getSalary());

        pstmt.setString(6, employee.getSex() != null ? employee.getSex().name() : null);

        if (employee.getBirthday() != null) {
            pstmt.setDate(7, new java.sql.Date(employee.getBirthday().getTime()));
        } else {
            pstmt.setDate(7, null);
        }

        pstmt.setInt(8, employee.getDepartmentID());

        // Dùng setObject để có thể truyền null nếu chưa có UserLogin
        if (employee.getUserID() != 0) {
            pstmt.setInt(9, employee.getUserID());
        } else {
            pstmt.setNull(9, java.sql.Types.INTEGER);
        }

        Timestamp createdAt = employee.getCreated_at() != null
                ? employee.getCreated_at()
                : new Timestamp(System.currentTimeMillis());
        pstmt.setTimestamp(10, createdAt);

        int rows = pstmt.executeUpdate();
        if (rows > 0) {
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    employee.setEmployeeID(rs.getInt(1));
                }
            }
        }
        return rows > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


	@Override
	public boolean updateEmployeeRecord(int id,EmployeeEntity employee) {
		String sql = "UPDATE employee SET Name=?, Ssn=?, Address=?, Email=?, Salary=?, Sex=?, Birthday=?, DepartmentID=?, UserID=?, created_at=? " +
                "WHERE EmployeeID=?";

	   try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
	        PreparedStatement pstmt = conn.prepareStatement(sql)) {
	
	       pstmt.setString(1, employee.getName());
	       pstmt.setString(2, employee.getSsn());
	       pstmt.setString(3, employee.getAddress());
	       pstmt.setString(4, employee.getEmail());
	       pstmt.setDouble(5, employee.getSalary());
	
	       pstmt.setString(6, employee.getSex() != null ? employee.getSex().name() : null);
	
	       pstmt.setDate(7, employee.getBirthday());
	
	       pstmt.setInt(8, employee.getDepartmentID());
	       if(employee.getUserID() != 0) {
				pstmt.setInt(9, employee.getUserID());
			} else {
				pstmt.setNull(9, java.sql.Types.INTEGER);
			}
				
	       pstmt.setTimestamp(10, new java.sql.Timestamp(System.currentTimeMillis()));
	

	       pstmt.setInt(11, id);
	
	       return pstmt.executeUpdate() > 0;
	
	   } catch (SQLException e) {
	       e.printStackTrace();
	       return false;
	   }
	}

	@Override
	public boolean deleteEmployeeRecord(int emplId) {
		 String sql = "DELETE FROM Employee WHERE EmployeeID=?";
		    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		         PreparedStatement pstmt = conn.prepareStatement(sql)) {

		        pstmt.setInt(1, emplId);
		        int rowsAffected = pstmt.executeUpdate();
		        return rowsAffected > 0; 

		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
	}

	@Override
	public List<AttendanceEntity> getAllAttendanceRecord(int emplId) {
		List<AttendanceEntity> attendanceList = new ArrayList<>();
	    String sql = "SELECT * FROM Attendance WHERE EmployeeID = ?";
	    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setInt(1, emplId);

	        try (ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                AttendanceEntity attendance = new AttendanceEntity();
	                attendance.setAttendanceID(rs.getInt("AttendanceID"));
	                attendance.setEmployeeID(rs.getInt("EmployeeID"));
	                attendance.setWorkDate(rs.getDate("WorkDate"));
	                attendance.setShiftID(rs.getInt("ShiftID"));
	                
	                Timestamp checkInTs = rs.getTimestamp("CheckIn");
	                attendance.setCheckIn(checkInTs != null ? checkInTs : null);
	                
	                Timestamp checkOutTs = rs.getTimestamp("CheckOut");
	                attendance.setCheckOut(checkOutTs != null ? checkOutTs : null);
	                
	                String statusStr = rs.getString("Status");
	                attendance.setStatus(
	                    statusStr != null ? AttendanceStatus.valueOf(statusStr.toUpperCase()) : null
	                );

	                Timestamp createdAtTs = rs.getTimestamp("created_at");
	                attendance.setCreated_at(createdAtTs != null ? createdAtTs : null);

	                int holidayId = rs.getInt("HolidayID");
	                attendance.setHolidayID(!rs.wasNull() ? holidayId : 0); 
	                attendanceList.add(attendance);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return attendanceList;
	}

	@Override
	public List<LeaveRecordEntity> getAllLeaveEntity(int emplId) {
		List<LeaveRecordEntity> list = new ArrayList<>();

	    String sql = "SELECT * FROM LeaveRecord WHERE EmployeeID = ?";

	    try (Connection conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, emplId);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            LeaveRecordEntity leave = new LeaveRecordEntity();

	            leave.setLeaveID(rs.getInt("LeaveID"));
	            leave.setEmployeeID(rs.getInt("EmployeeID"));
	            leave.setLeaveTypeID(rs.getInt("LeaveTypeID"));
	            leave.setStart_date(rs.getDate("StartDate"));
	            leave.setEnd_date(rs.getDate("EndDate"));
	            leave.setReason(rs.getString("Reason"));

	            String statusStr = rs.getString("Status");
	            if (statusStr != null) {
	                leave.setStatus(LeaveStatus.valueOf(statusStr.toUpperCase()));
	            }

	            Timestamp ts = rs.getTimestamp("created_at");
	            leave.setCreated_at(ts != null ? ts : null);

	            list.add(leave);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}

	@Override
	public void assignDepartment(int emplId, int deptId) {
		String sql = "UPDATE Employee SET DepartmentID = ? WHERE EmployeeID = ?";

		 try (Connection conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, deptId);
	        ps.setInt(2, emplId);
	        ps.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void assignShift(int emplId, int shiftId) {
		String sql = "INSERT INTO attendance (EmployeeID, ShiftID, WorkDate) VALUES (?, ?, CURDATE())";

		 try (Connection conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, shiftId);
	        ps.setInt(2, emplId);
	        ps.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
}
