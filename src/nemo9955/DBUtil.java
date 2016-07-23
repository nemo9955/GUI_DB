package nemo9955;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	final String URL = "jdbc:derby://localhost:1527/facturi;create=true";
	final String DRIVER = "org.apache.derby.jdbc.ClientDriver";
	final String PASS = "";
	final String USER = "";

	Connection conn = null;
	Statement stmt = null;

	private DBUtil instanta = null;

	public DBUtil() {
		buildConnection();
	}

	public String printResult(ResultSet rs) {
		String s = "";
		ResultSetMetaData rsmd;
		try {
			rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			int[] sps = new int[cols + 1];

			for (int i = 1; i < cols; i++) {
				sps[i] = ((int) Math.ceil(rsmd.getColumnDisplaySize(i) / 3) + rsmd.getColumnLabel(i).length());
				s += String.format("%-" + sps[i] + "s", rsmd.getColumnLabel(i));
			}
			s += "\n";

			while (rs.next()) {
				for (int i = 1; i < cols; i++)
					s += String.format("%-" + sps[i] + "s", rs.getString(i));
				s += "\n";
			}

		} catch (SQLException e) {
			DBUtil.dispaySQLExceptions(e);
		}

		return s;
	}

	public Connection getConnection() {
		return conn;
	}

	public void buildConnection() {
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL);
		} catch (SQLException e) {
			DBUtil.dispaySQLExceptions(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (!conn.isClosed())
					System.out.println("JDBC Successfully connected to Derby server.");
			} catch (SQLException e) {
				DBUtil.dispaySQLExceptions(e);
			}

		}
	}

	public static void dispaySQLExceptions(SQLException ex) {
		ex.printStackTrace();
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
}
