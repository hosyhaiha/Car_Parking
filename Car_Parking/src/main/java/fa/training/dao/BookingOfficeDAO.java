package fa.training.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fa.training.entity.Office;
import fa.training.entity.OfficePlace;
import fa.training.utils.DBUtils;

public class BookingOfficeDAO {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	DBUtils db = new DBUtils();
	
	public List<Office> getAllOffice(int pageIndex, int pageSize) throws SQLException,Exception {
		List<Office> list = new ArrayList<>();
		try {
			String query = "select bookingoffice.officeID,\r\n"
					+ "bookingoffice.endContractDeadline,\r\n"
					+ "bookingoffice.officeName,\r\n"
					+ "bookingoffice.officePhone,\r\n"
					+ "bookingoffice.officePlace,\r\n"
					+ "bookingoffice.officePrice,\r\n"
					+ "bookingoffice.startContractDeadline,\r\n"
					+ "bookingoffice.tripID,\r\n"
					+ "trip.destination as 'tripDes' from bookingoffice,trip where bookingoffice.tripID = trip.tripID"
					+ " order by officeID offset ? rows fetch first ? rows only";
			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, (pageIndex - 1) * pageSize);
			ps.setInt(2, pageSize);
			rs = ps.executeQuery();
			while (rs.next()) {
				Office p = new Office(rs.getInt(1), rs.getDate(2), 
						rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getDate(7), rs.getInt(8),rs.getString(9));
				list.add(p);
			}
			return list;
		} catch (Exception e) {
			throw e;
		}finally {
			db.closeConnection(rs, ps, conn);
		}
	}
	
	public Office getOfficeByID(int id) throws Exception {
		Office office = new Office();
		try {
			String sql = "select bookingoffice.officeID,\r\n"
					+ "bookingoffice.endContractDeadline,\r\n"
					+ "bookingoffice.officeName,\r\n"
					+ "bookingoffice.officePhone,\r\n"
					+ "bookingoffice.officePlace,\r\n"
					+ "bookingoffice.officePrice,\r\n"
					+ "bookingoffice.startContractDeadline,\r\n"
					+ "bookingoffice.tripID,\r\n"
					+ "trip.destination as 'tripDes' from bookingoffice,trip where bookingoffice.tripID = trip.tripID\r\n"
					+ "and officeID = ?";
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				office = new Office(rs.getInt(1), rs.getDate(2), 
						rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getDate(7), rs.getInt(8),rs.getString(9));
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			db.closeConnection(rs, ps, conn);
		}
		return office;
	}
	
	public List<OfficePlace> getAllPlace() throws SQLException {
		List<OfficePlace> list = new ArrayList<>();
		try {
			String query = "select * from dbo.bookofficeplace";
			DBUtils db = new DBUtils();
			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				OfficePlace p = new OfficePlace(rs.getString(1));
				list.add(p);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			db.closeConnection(rs, ps, conn);
		}
		return null;
	}
	
	public void addNew(Date endContractDeadline, String officeName, String officePhone,String officeplace,int officePrice,Date startContractDeadline,int tripID) throws SQLException {
		try {
			String query = "INSERT INTO [dbo].[bookingoffice]([endContractDeadline],[officeName],[officePhone],[officePlace],[officePrice],[startContractDeadline],[tripID])"
					+ " VALUES(?,?,?,?,?,?,?)";
			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			ps.setDate(1, endContractDeadline);
			ps.setString(2, officeName);
			ps.setString(3, officePhone);
			ps.setString(4, officeplace);
			ps.setInt(5, officePrice);
			ps.setDate(6, startContractDeadline);
			ps.setInt(7, tripID);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			db.closeConnection(rs, ps, conn);
		}
	}
	
	public List<Office> getOffice(String value,String filter) throws Exception{
		List<Office> list = new ArrayList<>();
		try {
			String query = "select bookingoffice.officeID,\r\n"
					+ "bookingoffice.endContractDeadline,\r\n"
					+ "bookingoffice.officeName,\r\n"
					+ "bookingoffice.officePhone,\r\n"
					+ "bookingoffice.officePlace,\r\n"
					+ "bookingoffice.officePrice,\r\n"
					+ "bookingoffice.startContractDeadline,\r\n"
					+ "bookingoffice.tripID,\r\n"
					+ "trip.destination as 'tripDes' from bookingoffice,trip where bookingoffice.tripID = trip.tripID and "
							+ filter + " LIKE CONCAT(?,'%')";
			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, value);
			rs = ps.executeQuery();
			while (rs.next()) {
				Office p = new Office(rs.getInt(1), rs.getDate(2), 
						rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getDate(7), rs.getInt(8),rs.getString(9));
				list.add(p);
			}
			return list;
		} catch (Exception e) {
			throw e;
		}finally {
			db.closeConnection(rs, ps, conn);
		}	
	}
	
	public int getCountOffice() throws Exception {
		int count = 0;
		try {
			String query = "select COUNT(officeID) from bookingoffice";
			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		}catch (Exception e) {
			throw e;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
		return count;
	}
	
	public int getCountOfficeSearch(String filter, String value) throws Exception {
		int count = 0;
		try {
			String query = "select COUNT(officeID) from bookingoffice where " + filter + "\r\n"
					+ "LIKE CONCAT(?,'%')";
			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, value);
			rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		}catch (Exception e) {
			throw e;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
		return count;
	}
	
	public List<Office> getListOfficeSearch(String filter, String value,int pageIndex, int pageSize) throws SQLException,Exception {
		List<Office> list = new ArrayList<>();
		try {
			String query = "select bookingoffice.officeID,\r\n"
					+ "bookingoffice.endContractDeadline,\r\n"
					+ "bookingoffice.officeName,\r\n"
					+ "bookingoffice.officePhone,\r\n"
					+ "bookingoffice.officePlace,\r\n"
					+ "bookingoffice.officePrice,\r\n"
					+ "bookingoffice.startContractDeadline,\r\n"
					+ "bookingoffice.tripID,\r\n"
					+ "trip.destination as 'tripDes' from bookingoffice,trip where bookingoffice.tripID = trip.tripID \r\n"
					+ "and "+ filter +" LIKE CONCAT(?,'%')\r\n"
					+ "order by officeID offset ? rows fetch first ? rows only";
			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, value);
			ps.setInt(2, (pageIndex - 1) * pageSize);
			ps.setInt(3, pageSize);
			rs = ps.executeQuery();
			while (rs.next()) {
				Office p = new Office(rs.getInt(1), rs.getDate(2), 
						rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getDate(7), rs.getInt(8),rs.getString(9));
				list.add(p);
			}
			return list;
		} catch (Exception e) {
			throw e;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
	}
}
