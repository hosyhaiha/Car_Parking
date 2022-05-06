package fa.training.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fa.training.entity.Department;
import fa.training.entity.Employee;
import fa.training.utils.DBUtils;

public class EmployeeDAO {
	public void addEmployee(Employee e) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		try {
			String query = "insert into Employee values(?,?,?,?,?,?,?,?,?)";
			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, e.getAccount());
			ps.setString(2, e.getDepartment());
			ps.setString(3, e.getEmployeeAddress());
			ps.setDate(4, e.getEmployeeBirthdate());
			ps.setString(5, e.getEmployeeEmail());
			ps.setString(6, e.getEmployeeName());
			ps.setString(7, e.getEmployeePhone());
			ps.setString(8, e.getPassword());
			ps.setString(9, e.getSex());
			ps.executeUpdate();

		} catch (Exception ex) {
			throw ex;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
	}

	public void deleteEmployee(String id) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		try {
			String query = "DELETE FROM Employee WHERE employeeID = ?";
			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (Exception ex) {
			throw ex;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
	}

	public void updateEmployee(String department, String employeeAddress, Date employeeBirthdate, String employeeEmail,
			String employeeName, String employeePhone, String sex, String id) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		try {
			String query = "UPDATE Employee SET department =?, employeeAddress =?, employeeBirthdate =?,\r\n"
					+ "						employeeEmail =?, employeeName =?, employeePhone =?,sex=? where employeeid =?";
			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, department);
			ps.setString(2, employeeAddress);
			ps.setDate(3, employeeBirthdate);
			ps.setString(4, employeeEmail);
			ps.setString(5, employeeName);
			ps.setString(6, employeePhone);
			ps.setString(7, sex);
			ps.setString(8, id);
			ps.executeUpdate();
		} catch (Exception ex) {
			throw ex;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
	}

	public void editProfile(String employeeAddress, Date employeeBirthdate, String employeeEmail, String employeeName,
			String employeePhone, String password, String sex, String id) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		try {
			String query = "UPDATE Employee SET employeeAddress =?, employeeBirthdate =?,\r\n"
					+ "	employeeEmail =?, employeeName =?, employeePhone =? , password =?, sex=? where employeeid =?";
			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, employeeAddress);
			ps.setDate(2, employeeBirthdate);
			ps.setString(3, employeeEmail);
			ps.setString(4, employeeName);
			ps.setString(5, employeePhone);
			ps.setString(6, password);
			ps.setString(7, sex);
			ps.setString(8, id);
			ps.executeUpdate();
		} catch (Exception ex) {
			throw ex;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
	}

	public List<Department> getDepartment() throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		List<Department> list = new ArrayList<Department>();
		try {
			String query = "select department from permission";
			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Department dep = new Department(rs.getString(1));
				list.add(dep);
			}
		} catch (Exception ex) {
			throw ex;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
		return list;
	}

	public boolean checkEmailExisted(String email) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();

		try {
			String sql = "select * from Employee where employeeEmail = ?";
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			db.closeConnection(rs, ps, conn);
		}
		return false;
	}

	public boolean checkEmailUpdateExisted(String email, String id) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();

		try {
			String sql = "select * from Employee where employeeEmail = ? ,id = ?";
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			db.closeConnection(rs, ps, conn);
		}
		return false;
	}

	public boolean checkAccountExisted(String account) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();

		try {
			String sql = "select * from Employee where account = ?";
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, account);
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			db.closeConnection(rs, ps, conn);
		}
		return false;
	}

	public boolean checkAccountUpdateExisted(String account, String id) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();

		try {
			String sql = "select * from Employee where account = ? , id = ?";
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, account);
			ps.setString(2, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			db.closeConnection(rs, ps, conn);
		}
		return false;
	}

	public Employee getEmployeeByID(String id) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		Employee employee = new Employee();
		;
		try {
			String sql = "select * from Employee where employeeID = ?";
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				employee = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			db.closeConnection(rs, ps, conn);
		}
		return employee;
	}

	public Employee getEmployeeByAccount(String account) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		Employee employee = new Employee();
		;
		try {
			String sql = "select * from Employee where account = ?";
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, account);
			rs = ps.executeQuery();
			if (rs.next()) {
				employee = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			db.closeConnection(rs, ps, conn);
		}
		return employee;
	}

	public List<Employee> getListEmployee(int pageIndex, int pageSize) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		List<Employee> list = new ArrayList<>();
		try {
			String query = "select * from employee " + "order by employeeid offset ? rows fetch first ? rows only";
			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, (pageIndex - 1) * pageSize);
			ps.setInt(2, pageSize);
			rs = ps.executeQuery();
			while (rs.next()) {
				Employee employee = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getDate(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
						rs.getString(10));
				list.add(employee);
			}
			return list;
		} catch (Exception e) {
			throw e;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
	}

	public List<Employee> getListEmployeeSearch(String filter, String value, int pageIndex, int pageSize)
			throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		List<Employee> list = new ArrayList<>();
		try {
			String query = "select * from employee where " + filter + " like concat(?,'%') "
					+ "order by employeeID  offset ? rows fetch first ? rows only";
			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, value);
			ps.setInt(2, (pageIndex - 1) * pageSize);
			ps.setInt(3, pageSize);
			rs = ps.executeQuery();
			while (rs.next()) {
				Employee employee = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getDate(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
						rs.getString(10));
				list.add(employee);
			}
			return list;
		} catch (Exception e) {
			throw e;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
	}

	public List<Employee> getEmployee(String filter, String value) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		List<Employee> list = new ArrayList<>();
		try {
			String query = "select * from employee where " + filter + " like concat(?,'%')";
			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, value);
			rs = ps.executeQuery();
			while (rs.next()) {
				Employee employee = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getDate(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
						rs.getString(10));
				list.add(employee);
			}
			return list;
		} catch (Exception e) {
			throw e;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
	}

	public int getCountEmployee() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		int count = 0;
		try {
			String query = "SELECT COUNT(employeeid) from Employee";
			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
		return count;
	}

	public int getCountEmployeeSearch(String filter, String value) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		int count = 0;
		try {
			String query = "select count (employeeID) from employee where " + filter + " like concat(?,'%')";
			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, value);
			rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
		return count;
	}

	public boolean checkLogin(String account, String password) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();

		try {
			String sql = "select * from employee where account = ? and password = ?";
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, account);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			db.closeConnection(rs, ps, conn);
		}
		return false;
	}
}
