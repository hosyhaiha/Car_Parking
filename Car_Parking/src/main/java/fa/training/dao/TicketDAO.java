package fa.training.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import fa.training.entity.Ticket;
import fa.training.utils.DBUtils;

public class TicketDAO {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	DBUtils db = new DBUtils();

	public List<Ticket> getAllTicket(int pageIndex, int pageSize) throws SQLException,Exception {
		List<Ticket> list = new ArrayList<>();
		try {
			String query = "select ticket.ticketID,ticket.bookingTime,ticket.customerName,ticket.licensePlate,ticket.tripID,trip.destination "
					+ "as 'tripDes' from ticket,trip where ticket.tripID = trip.tripID "
					+ " order by ticketID offset ? rows fetch first ? rows only";
			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, (pageIndex - 1) * pageSize);
			ps.setInt(2, pageSize);
			rs = ps.executeQuery();
			while (rs.next()) {
				Ticket p = new Ticket(rs.getInt(1), rs.getTime(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getString(6));
				list.add(p);
			}
			return list;
		} catch (Exception e) {
			throw e;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
	}

	public Ticket getTicketByID(int id) throws Exception {
		Ticket ticket = new Ticket();
		try {
			String sql = "select ticket.ticketID,ticket.bookingTime,ticket.customerName,ticket.licensePlate,ticket.tripID,trip.destination "
					+ "as 'tripDes' from ticket,trip where ticket.tripID = trip.tripID and ticketID = ?";
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				ticket = new Ticket(rs.getInt(1), rs.getTime(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getString(6));
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			db.closeConnection(rs, ps, conn);
		}
		return ticket;
	}

	public void addNew(Time bookingTime, String customerName, String licensePlate, int tripID) throws SQLException {
		try {
			String query = "INSERT INTO [dbo].[ticket]([bookingTime],[customerName],[licensePlate],[tripID]) VALUES (?,?,?,?)";
			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			ps.setTime(1, bookingTime);
			ps.setString(2, customerName);
			ps.setString(3, licensePlate);
			ps.setInt(4, tripID);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, conn);
		}
	}

	public void deleteTicket(int id) throws SQLException {
		try {
			String query = "DELETE FROM [dbo].[ticket]\r\n" + "      WHERE ticketID = ?";
			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception ex) {
			Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			db.closeConnection(rs, ps, conn);
		}
	}

	public List<Ticket> getTicket(String value, String filter) throws Exception {
		List<Ticket> list = new ArrayList<>();
		try {
			String query = "select ticket.ticketID,ticket.bookingTime,ticket.customerName,ticket.licensePlate,ticket.tripID,trip.destination \r\n"
					+ "as 'tripDes' from ticket,trip where ticket.tripID = trip.tripID \r\n" + "and " + filter
					+ " LIKE CONCAT(?,'%')";
			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, value);
			rs = ps.executeQuery();
			while (rs.next()) {
				Ticket ticket = new Ticket(rs.getInt(1), rs.getTime(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getString(6));
				list.add(ticket);
			}
			return list;
		} catch (Exception e) {
			throw e;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
	}

	public int getCountTicket() throws Exception {
		int count = 0;
		try {
			String query = "select COUNT(ticketID) from ticket";
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
	
	public int getCountTicketSearch(String filter, String value) throws Exception {
		int count = 0;
		try {
			String query = "select COUNT(ticketID) from ticket where " + filter + "\r\n"
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
	
	public List<Ticket> getListTicketSearch(String filter, String value,int pageIndex, int pageSize) throws SQLException,Exception {
		List<Ticket> list = new ArrayList<>();
		try {
			String query = "select ticket.ticketID,ticket.bookingTime,ticket.customerName,ticket.licensePlate,ticket.tripID,trip.destination \r\n"
					+ "as 'tripDes' from ticket,trip where ticket.tripID = trip.tripID \r\n"
					+ "and "+ filter +"\r\n"
					+ "LIKE CONCAT(?,'%')\r\n"
					+ "order by ticketID offset ? rows fetch first ? rows only";
			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, value);
			ps.setInt(2, (pageIndex - 1) * pageSize);
			ps.setInt(3, pageSize);
			rs = ps.executeQuery();
			while (rs.next()) {
				Ticket p = new Ticket(rs.getInt(1), rs.getTime(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getString(6));
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
