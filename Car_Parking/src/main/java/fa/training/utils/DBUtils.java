package fa.training.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {
    private final String username = "sa1";
    private final String password = "123123";
    private final String serverName = "localhost";
    private final String dbName = "MockProject_T4";
    private final String portNumber = "1433";
	 public Connection getConnection() throws Exception {
		 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		 String url = "jdbc:sqlserver://" + serverName + ":" + portNumber +";databaseName=" + dbName;
		 return DriverManager.getConnection(url, username, password);
	 }
	 public  void closeConnection(ResultSet rs, PreparedStatement ps, Connection con) throws SQLException {
	        if (rs != null && !rs.isClosed()) {
	            rs.close();
	        }
	        if (ps != null && !ps.isClosed()) {
	            ps.close();
	        }
	        if (con != null && !con.isClosed()) {
	            con.close();
	        }
	    }
}
