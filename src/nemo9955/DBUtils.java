package nemo9955;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

	// private static String driver = "org.apache.derby.jdbc.EmbededDrive";

	private static Connection conn = null;

	private static DBUtils ins = null;

	private DBUtils() {
		buildCon();
	}

	private static void buildCon() {
		String url = "jdbc:derby://localhost:1527/facturi;create=true;";
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
			conn = DriverManager.getConnection(url);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (!conn.isClosed())
					System.out.println("Creat cu success");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	public static Connection getCon() {
		if (ins == null) {
			ins = new DBUtils();
		}
		return conn;
	}

	public static void SQLEx(SQLException e) {
		while (e != null) {
			System.out.println("SQL STatic " + e.getSQLState());
			System.out.println("sql code " + e.getErrorCode());
			System.out.println("mesage " + e.getMessage());
			Throwable t = e.getCause();
			while (t != null) {
				System.out.println("Cause : " + t);
				t = t.getCause();
			}
			e = e.getNextException();

		}
	}
}
