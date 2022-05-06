package fa.training.entity;

import java.sql.Date;

public class Employee {
	private int employeeID;
	private String account;
	private String department;
	private String employeeAddress;
	private Date employeeBirthdate;
	private String employeeEmail;
	private String employeeName;
	private String employeePhone;
	private String password;
	private String sex;

	public Employee() {
		// TODO Auto-generated constructor stub
	}
	

	public Employee(int employeeID, String account, String department, String employeeAddress, Date employeeBirthdate,
			String employeeEmail, String employeeName, String employeePhone, String password, String sex) {
		super();
		this.employeeID = employeeID;
		this.account = account;
		this.department = department;
		this.employeeAddress = employeeAddress;
		this.employeeBirthdate = employeeBirthdate;
		this.employeeEmail = employeeEmail;
		this.employeeName = employeeName;
		this.employeePhone = employeePhone;
		this.password = password;
		this.sex = sex;
	}


	public Employee(String account, String department, String employeeAddress, Date employeeBirthdate,
			String employeeEmail, String employeeName, String employeePhone, String password, String sex) {
		super();
		this.account = account;
		this.department = department;
		this.employeeAddress = employeeAddress;
		this.employeeBirthdate = employeeBirthdate;
		this.employeeEmail = employeeEmail;
		this.employeeName = employeeName;
		this.employeePhone = employeePhone;
		this.password = password;
		this.sex = sex;
	}


	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getEmployeeAddress() {
		return employeeAddress;
	}
	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}
	public Date getEmployeeBirthdate() {
		return employeeBirthdate;
	}
	public void setEmployeeBirthdate(Date employeeBirthdate) {
		this.employeeBirthdate = employeeBirthdate;
	}
	public String getEmployeeEmail() {
		return employeeEmail;
	}
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeePhone() {
		return employeePhone;
	}
	public void setEmployeePhone(String employeePhone) {
		this.employeePhone = employeePhone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
}
