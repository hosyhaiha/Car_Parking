package fa.training.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fa.training.entity.Car;
import fa.training.utils.DBUtils;

public class CarDAO {
	public ArrayList<String> showCarCPN() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		ArrayList<String> list = new ArrayList<String>();
		try {
			conn = db.getConnection();
			String query = "select * from carcompany";
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

	public void addCar(Car c) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		try {
			conn = db.getConnection();
			String query = "INSERT INTO [dbo].[car]\r\n" + "           ([licensePlate]\r\n"
					+ "           ,[carColor]\r\n" + "           ,[carType]\r\n" + "           ,[company]\r\n"
					+ "           ,[parkID])\r\n" + "     VALUES\r\n" + "           (?\r\n" + "           ,?\r\n"
					+ "           ,?\r\n" + "           ,?\r\n" + "           ,?)";
			ps = conn.prepareStatement(query);
			ps.setString(1, c.getLicensePlate());
			ps.setString(2, c.getCarColor());
			ps.setString(3, c.getCarType());
			ps.setString(4, c.getCompany());
			ps.setInt(5, c.getParkID());
			ps.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
	}

	public ArrayList<Car> showListCar() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		ArrayList<Car> list = new ArrayList<Car>();
		try {
			conn = db.getConnection();
			String query = "select  car.licensePlate, car.carColor, car.carType, car.company,car.parkID, parkinglot.parkName\r\n"
					+ "from car, parkinglot\r\n" + "where car.parkID= parkinglot.parkID";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Car(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getString(6)));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
		return list;
	}

	public void updateCar(String licensePlate, String carColor, String carType, String company, int parkID)
			throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		try {
			conn = db.getConnection();
			String query = "UPDATE [dbo].[car]\r\n" + "   SET [carColor] = ?\r\n"
					+ "      ,[carType] = ?\r\n" + "      ,[company] = ?\r\n" + "      ,[parkID] = ?\r\n"
					+ " WHERE [licensePlate] = ?";
			ps = conn.prepareStatement(query);
			ps.setString(1, carColor);
			ps.setString(2, carType);
			ps.setString(3, company);
			ps.setInt(4, parkID);
			ps.setString(5, licensePlate);
			ps.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
	}

	public void deleteCar(String licensePlate) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		try {
			conn = db.getConnection();
			String query = "DELETE FROM [dbo].[car]\r\n" + "      WHERE licensePlate = ?";
			ps = conn.prepareStatement(query);
			ps.setString(1, licensePlate);
			ps.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
	}

	public ArrayList<Car> searchCar(String filter, String value) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		ArrayList<Car> list = new ArrayList<>();
		try {
			String query = "select  car.licensePlate, car.carColor, car.carType, car.company,car.parkID, parkinglot.parkName\r\n"
					+ "from car, parkinglot\r\n" + "where car.parkID= parkinglot.parkID and "+filter+" like concat(?,'%')";
			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, value);
			rs = ps.executeQuery();
			while (rs.next()) {
				Car car = new Car(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getString(6));
				list.add(car);
			}
			return list;
		} catch (Exception e) {
			throw e;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
	}

	public int getCountCarSearch(String filter, String value) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		int count = 0;
		try {
			String query = "select count (licensePlate) from car where "+ filter +" like concat(?,'%')";
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
	
	public ArrayList<Car> getListCarSearch(String filter, String value, int pageIndex, int pageSize)
			throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		ArrayList<Car> list = new ArrayList<>();
		try {
			String query = "select  car.licensePlate, car.carColor, car.carType, car.company,car.parkID, parkinglot.parkName\n"
					+ "from car, parkinglot \n"
					+ "where car.parkID= parkinglot.parkID and "+filter+" like concat(?,'%')\n"
					+ "order by licensePlate offset ? rows fetch first ? rows only";
			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, value);
			ps.setInt(2, (pageIndex - 1) * pageSize);
			ps.setInt(3, pageSize);
			rs = ps.executeQuery();
			while (rs.next()) {
				Car car = new Car(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getString(6));
				list.add(car);
			}
			return list;
		} catch (Exception e) {
			throw e;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
	}
	
	
	
	public boolean checkDupCar(String licensePlate) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		ArrayList<String> list = new ArrayList<>();
		try {
			String query = "SELECT [licensePlate]\r\n"
					+ "  FROM [dbo].[car]\r\n"
					+ "  where [licensePlate] = ?";
			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, licensePlate);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
			if(list.isEmpty()) {
				return true;
			}else return false;
		} catch (Exception e) {
			throw e;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
	}
	
	public int getCountCar() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		int count = 0;
		try {
			String query = "SELECT COUNT(licensePlate) from [dbo].[car]";
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
	
	public ArrayList<Car> getListCar(int pageIndex, int pageSize) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DBUtils db = new DBUtils();
		ArrayList<Car> list = new ArrayList<>();
		try {
			String query = "select  car.licensePlate, car.carColor, car.carType, car.company,car.parkID, parkinglot.parkName\n"
					+ "from car, parkinglot \n"
					+ "where car.parkID= parkinglot.parkID\n"
					+ "order by licensePlate offset ? rows fetch first ? rows only\n";
			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, (pageIndex - 1) * pageSize);
			ps.setInt(2, pageSize);
			rs = ps.executeQuery();
			while (rs.next()) {
				Car car = new Car(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getString(6));
				list.add(car);
			}
			return list;
		} catch (Exception e) {
			throw e;
		} finally {
			db.closeConnection(rs, ps, conn);
		}
	}

}
