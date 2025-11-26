package employeeAttendanceSystem.com.employeeSystem.repository.entity;

import java.sql.Date;
import java.sql.Timestamp;


public class EmployeeEntity {
	private int EmployeeID;
	private String Name;
	private String Ssn;
	private String Address;
	private String Email;
	private double Salary;
	private SexType Sex;
	private Date Birthday;
	private int UserID;
	private int DepartmentID;
	private Timestamp Created_at;
	
	public int getEmployeeID() {
		return EmployeeID;
	}
	public void setEmployeeID(int employeeID) {
		EmployeeID = employeeID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getSsn() {
		return Ssn;
	}
	public void setSsn(String ssn) {
		Ssn = ssn;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public double getSalary() {
		return Salary;
	}
	public void setSalary(double salary) {
		Salary = salary;
	}
	public SexType getSex() {
		return Sex;
	}
	public void setSex(SexType sex) {
		Sex = sex;
	}
	public Date getBirthday() {
		return Birthday;
	}
	public void setBirthday(Date birthday) {
		Birthday = birthday;
	}
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public int getDepartmentID() {
		return DepartmentID;
	}
	public void setDepartmentID(int departmentID) {
		DepartmentID = departmentID;
	}
	public Timestamp getCreated_at() {
		return Created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		Created_at = created_at;
	}
	
	
}
