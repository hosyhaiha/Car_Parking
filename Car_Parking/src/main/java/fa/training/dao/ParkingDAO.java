package fa.training.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fa.training.entity.Parking;
import fa.training.utils.DBUtils;

public class ParkingDAO {

	public ArrayList<String> showParkPlace() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		ArrayList<String> list = new ArrayList<String>();
		try {
			conn = db.getConnection();
			String query = "select * from parkinglotplace";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
		return list;
	}

	public ArrayList<Parking> showListPark() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		ArrayList<Parking> list = new ArrayList<Parking>();
		try {
			conn = db.getConnection();
			String query = "select *from parkinglot";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Parking(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getString(6)));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
		return list;
	}

	public ArrayList<Parking> showListParkBlank() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		ArrayList<Parking> list = new ArrayList<Parking>();
		try {
			conn = db.getConnection();
			String query = "select *from parkinglot\n" + "where parkStatus = 'Blank'";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Parking(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getString(6)));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
		return list;
	}

	public Parking getParkByID(int id) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		Parking p = null;
		try {
			conn = db.getConnection();
			String query = "select *from parkinglot\n" + "where parkID = ?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				p = new Parking(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getString(6));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
		return p;
	}

	public void addParking(Parking p) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();

		try {
			conn = db.getConnection();
			String query = "INSERT INTO [dbo].[parkinglot]\r\n" + "           ([parkArea]\r\n"
					+ "           ,[parkName]\r\n" + "           ,[parkPlace]\r\n" + "           ,[parkPrice]\r\n"
					+ "           ,[parkStatus])\r\n" + "     VALUES\r\n" + "           (?\r\n" + "           ,?\r\n"
					+ "           ,?\r\n" + "           ,?\r\n" + "           ,?)";
			ps = conn.prepareStatement(query);
			ps.setInt(1, p.getParkArea());
			ps.setString(2, p.getParkName());
			ps.setString(3, p.getParkPlace());
			ps.setInt(4, p.getParkPrice());
			ps.setString(5, p.getParkStatus());
			ps.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
	}

	public void updateParking(int id, String name, String place, int area, int price, String status) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		try {
			conn = db.getConnection();
			String query = "UPDATE [dbo].[parkinglot]\r\n" + "   SET [parkArea] = ?\r\n" + "      ,[parkName] = ?\r\n"
					+ "      ,[parkPlace] = ?\r\n" + "      ,[parkPrice] = ?\r\n" + "      ,[parkStatus] = ?\r\n"
					+ " WHERE parkID = ?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, area);
			ps.setString(2, name);
			ps.setString(3, place);
			ps.setInt(4, price);
			ps.setString(5, status);
			ps.setInt(6, id);
			ps.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
	}

	public boolean deleteParking(int id) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		try {
			conn = db.getConnection();
			String query = "DELETE FROM [dbo].[parkinglot]\r\n" + "      WHERE parkID = ?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
	}

	public ArrayList<Parking> searchPark(String filter, String value) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		ArrayList<Parking> list = new ArrayList<>();
		try {
			String query = "select * from parkinglot where " + filter + " like concat(?,'%')";
			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, value);
			rs = ps.executeQuery();
			while (rs.next()) {
				Parking park = new Parking(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getString(6));
				list.add(park);
			}
			return list;
		} catch (Exception e) {
			throw e;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
	}

	public int getCountPark() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		int count = 0;
		try {
			String query = "SELECT COUNT(parkID) from [dbo].[parkinglot]";
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

	public ArrayList<Parking> getListPark(int pageIndex, int pageSize) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		ArrayList<Parking> list = new ArrayList<>();
		try {
			String query = "SELECT * from parkinglot\n"
					+ "order by parkID offset ? rows fetch first ? rows only";
			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, (pageIndex - 1) * pageSize);
			ps.setInt(2, pageSize);
			rs = ps.executeQuery();
			while (rs.next()) {
				Parking park = new Parking(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getString(6));
				list.add(park);
			}
			return list;
		} catch (Exception e) {
			throw e;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
	}
	
	public ArrayList<Parking> getListParkSearch(String filter, String value, int pageIndex, int pageSize)
			throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		ArrayList<Parking> list = new ArrayList<>();
		try {
			String query = "select * from parkinglot where " + filter + " like concat(?,'%')\n"
					+ "order by parkID offset ? rows fetch first ? rows only";
			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, value);
			ps.setInt(2, (pageIndex - 1) * pageSize);
			ps.setInt(3, pageSize);
			rs = ps.executeQuery();
			while (rs.next()) {
				Parking park = new Parking(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getString(6));
				list.add(park);
			}
			return list;
		} catch (Exception e) {
			throw e;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
	}
	

	public int getCountParkSearch(String filter, String value) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		int count = 0;
		try {
			String query = "select count (parkID) from parkinglot where "+ filter +" like concat(?,'%')";
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
	
}
