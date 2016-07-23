package nemo9955;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	static String parola = "";
	static String utilizator = "";
	private static Connection conn = null;

	private static DBUtil instanta = null;

	private DBUtil() {
		buildConnection();
	}

	public static Connection getConnection() {
		if (instanta == null) {
			instanta = new DBUtil();
		}
		return conn;
	}

	public static void dispaySQLExceptions(SQLException ex) {
		while (ex != null) {
			System.out.println("SQL State:" + ex.getSQLState());
			System.out.println("Error Code:" + ex.getErrorCode());
			System.out.println("Message:" + ex.getMessage());
			Throwable t = ex.getCause();
			while (t != null) {
				System.out.println("Cause:" + t);
				t = t.getCause();
			}
			ex = ex.getNextException();
		}
	}

	public static void buildConnection() {
		String url = "jdbc:derby://localhost:1527/facturi;create=true";
		try {
			// Class.forName(driver);
			conn = DriverManager.getConnection(url);
			// } catch (ClassNotFoundException e) {
			// e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (!conn.isClosed())
					System.out.println("JDBC 4.0 Successfully connected to " + "Derby server using TCP/IP...");
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}


}
