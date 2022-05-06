package fa.training.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import fa.training.entity.Trip;
import fa.training.utils.DBUtils;

public class TripDAO {
	DBUtils db = new DBUtils();

	public void addTrip(Trip trip) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "insert into trip(bookedTicketNumber,carType,departureDate,departureTime,destination,driver,maximumOnlineTicketNumber) \r\n"
					+ "Values (?,?,?,?,?,?,?)";
		    con = db.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, trip.getBookedTicketNumber());
			ps.setString(2, trip.getCarType());
			ps.setDate(3, trip.getDepartureDate());
			ps.setTime(4, trip.getDepartureTime());
			ps.setString(5, trip.getDestination());
			ps.setString(6, trip.getDriver());
			ps.setInt(7, trip.getMaximumOnlineTicketNumber());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			db.closeConnection(rs, ps, con);
		}
	}

// get List trip
	public List<Trip> getListTrip() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Trip> list = new ArrayList<Trip>();
		try {
			String sql = "select* from trip";
			con = db.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				int tripID = rs.getInt("tripID");
				int bookedTicketNumber = rs.getInt("bookedTicketNumber");
				String carType = rs.getString("carType");
				Date departureDate = rs.getDate("departureDate");
				Time departureTime = rs.getTime("departureTime");
				String destination = rs.getString("destination");
				String driver = rs.getString("driver");
				int maximumOnlineTicketNumber = rs.getInt("maximumOnlineTicketNumber");
				list.add(new Trip(tripID, bookedTicketNumber, carType, departureDate, departureTime, destination,
						driver, maximumOnlineTicketNumber));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			db.closeConnection(rs, ps, con);
		}
		return list;
	}

// update trip
	public void updateTrip(Trip trip) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "update Trip set bookedTicketNumber = ?, carType = ?, "
					+ "departureDate = ?, departureTime = ?, destination = ?, driver = ?, maximumOnlineTicketNumber = ? where tripID = ?";
			con = db.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, trip.getBookedTicketNumber());
			ps.setString(2, trip.getCarType());
			ps.setDate(3, trip.getDepartureDate());
			ps.setTime(4, trip.getDepartureTime());
			ps.setString(5, trip.getDestination());
			ps.setString(6, trip.getDriver());
			ps.setInt(7, trip.getMaximumOnlineTicketNumber());
			ps.setInt(8, trip.getTripID());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			db.closeConnection(rs, ps, con);
		}
	}

// get Trip by ID
	public Trip getTripByID(int tripID) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Trip trip = new Trip();
		try {
			String sql = "select tripID,bookedTicketNumber,carType,departureDate,departureTime,destination,driver,maximumOnlineTicketNumber from trip where tripID = ?";
			con = db.getConnection(); 
			ps = con.prepareStatement(sql);
			ps.setInt(1, tripID);
			rs = ps.executeQuery();
			while (rs.next()) {
				int bookedTicketNumber = rs.getInt("bookedTicketNumber");
				String carType = rs.getString("carType");
				Date departureDate = rs.getDate("departureDate");
				Time departureTime = rs.getTime("departureTime");
				String destination = rs.getString("destination");
				String driver = rs.getString("driver");
				int maximumOnlineTicketNumber = rs.getInt("maximumOnlineTicketNumber");
				trip = new Trip(tripID, bookedTicketNumber, carType, departureDate, departureTime, destination, driver,
						maximumOnlineTicketNumber);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			db.closeConnection(rs, ps, con);
		}
		return trip;
	}

// delete trip

	public boolean deleteTrip(int tripID) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean rowDeleted = false;
		try {
			String sql = "delete from trip where tripID = ?";
			con = db.getConnection(); 
			ps = con.prepareStatement(sql);
			ps.setInt(1, tripID);
			rowDeleted = ps.executeUpdate() > 0;
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			db.closeConnection(rs, ps, con);
		}
		return rowDeleted;
	}
	
	public int getCountTrip() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		int count = 0;
		try {
			String query = "SELECT COUNT(tripID) from trip";
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
	
	public List<Trip> getListTripPage(int pageIndex, int pageSize) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		List<Trip> list = new ArrayList<>();
		try {
			String query = "select * from trip " + "order by tripID offset ? rows fetch first ? rows only";
			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, (pageIndex - 1) * pageSize);
			ps.setInt(2, pageSize);
			rs = ps.executeQuery();
			while (rs.next()) {
				
				Trip trip = new Trip(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDate(4),rs.getTime(5),rs.getString(6),rs.getString(7),rs.getInt(8));
				list.add(trip);
			}
			return list;
		} catch (Exception e) {
			throw e;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
	}

	public List<Trip> getTripSearch(String value,Date dateFrom, Date dateTo) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		List<Trip> list = new ArrayList<>();
		try {
			String sql = "select * from trip where destination like concat(?,'%') and departureDate between ? and ?";
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, value);
			ps.setDate(2, dateFrom);
			ps.setDate(3, dateTo);
			rs = ps.executeQuery();
			while (rs.next()) {
				Trip trip = new Trip(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDate(4),rs.getTime(5),rs.getString(6),rs.getString(7),rs.getInt(8));
				list.add(trip);
			}
			return list;
		} catch (Exception e) {
			throw e;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
	}
	
	public int getCountTicketByTripID(int tripID) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		int count = 0;
		try {
			String query = "SELECT COUNT(ticketID) from ticket where tripID = ?";
			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, tripID);
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
	
	public int getCountOfficeByTripID(int tripID) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		int count = 0;
		try {
			String query = "SELECT COUNT(officeID) from bookingoffice where tripID = ?";
			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, tripID);
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
	
	public int getCountTripSearch(String value,Date dateFrom, Date dateTo) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		int count = 0;
		try {
			String sql = "select count(tripID) from trip where destination like concat(?,'%') and departureDate between ? and ?";
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, value);
			ps.setDate(2, dateFrom);
			ps.setDate(3, dateTo);
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
	
	public List<Trip> getListTripSearch(String value,Date dateFrom, Date dateTo, int pageIndex, int pageSize)
			throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		List<Trip> list = new ArrayList<>();
		try {
			String query = "select * from trip where destination like concat(?,'%') and departureDate between ? and ? "
					+ "order by tripID  offset ? rows fetch first ? rows only";
			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, value);
			ps.setDate(2, dateFrom);
			ps.setDate(3, dateTo);
			ps.setInt(4, (pageIndex - 1) * pageSize);
			ps.setInt(5, pageSize);
			rs = ps.executeQuery();
			while (rs.next()) {
				Trip trip = new Trip(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDate(4),rs.getTime(5),rs.getString(6),rs.getString(7),rs.getInt(8));
				list.add(trip);
			}
			return list;
		} catch (Exception e) {
			throw e;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
	}
	
	public String getMinDate() throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		String minDate = "";
		try {
			String sql = "select min(departureDate) from trip";
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				minDate = rs.getString(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
		return minDate;
	}
	public String getMaxDate() throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		String maxDate = "";
		try {
			String sql = "select max(departureDate) from trip";
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				maxDate = rs.getString(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
		return maxDate;
	}
	
	
	public int getCountTripSearchNoDate(String value) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		int count = 0;
		try {
			String sql = "select count(tripID) from trip where destination like concat(?,'%')";
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
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
	
	public List<Trip> getListTripSearchNoDate(String value, int pageIndex, int pageSize)
			throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		List<Trip> list = new ArrayList<>();
		try {
			String query = "select * from trip where destination like concat(?,'%') "
					+ "order by tripID  offset ? rows fetch first ? rows only";
			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, value);
			ps.setInt(2, (pageIndex - 1) * pageSize);
			ps.setInt(3, pageSize);
			rs = ps.executeQuery();
			while (rs.next()) {
				Trip trip = new Trip(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDate(4),rs.getTime(5),rs.getString(6),rs.getString(7),rs.getInt(8));
				list.add(trip);
			}
			return list;
		} catch (Exception e) {
			throw e;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
	}
}





