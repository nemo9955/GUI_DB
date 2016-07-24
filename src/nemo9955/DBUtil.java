package nemo9955;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import nemo9955.Act.ComandResult;

public class DBUtil {

	final String URL = "jdbc:derby://localhost:1527/facturi;create=true";
	final String DRIVER = "org.apache.derby.jdbc.ClientDriver";
	final String PASS = "";
	final String USER = "";

	Connection conn = null;
	Statement stmt = null;

	public DBUtil() {
		buildConnection();
	}

	public void imporFromCSV() {
		BufferedReader dataBR;
		try {
			dataBR = new BufferedReader(new FileReader(new File("import.csv")));
			String line = "";
			line = dataBR.readLine();
			String cols[] = line.split(",");
			int noCols = cols.length;

			while ((line = dataBR.readLine()) != null) {

				cols = line.split(",");
				for (int i = 0; i < noCols; i++) {

					cols[i] = cols[i].trim().substring(1, cols[i].length() - 1).trim();
				}

				Main.fdb.insertFactura(Factura.getInstanceFromStringArray(cols));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void runCommand(String comand, ComandResult outputTo) {
		try {
			PreparedStatement statement = conn.prepareStatement(comand);
			ResultSet resultSet = statement.executeQuery();
			switch (outputTo) {
			case TO_CSV:
				convertToCsv(resultSet, "export.csv");
				break;
			case TO_OUTPUT:
				Main.gui.logData(Main.dbu.printResult(resultSet));
				break;
			default:
				break;
			}

		} catch (

		Exception e) {
			Main.gui.logData("ERROR: " + e.getLocalizedMessage());
		}

	}

	public static void convertToCsv(ResultSet rs, String output) throws SQLException, FileNotFoundException {
		PrintWriter csvWriter = new PrintWriter(new File(output));
		ResultSetMetaData meta = rs.getMetaData();
		int numberOfColumns = meta.getColumnCount();
		String dataHeaders = "\"" + meta.getColumnName(1) + "\"";
		for (int i = 2; i < numberOfColumns + 1; i++) {
			dataHeaders += ",\"" + meta.getColumnName(i) + "\"";
		}
		csvWriter.println(dataHeaders);
		while (rs.next()) {
			String row = "\"" + rs.getString(1) + "\"";
			for (int i = 2; i < numberOfColumns + 1; i++) {
				row += ",\"" + rs.getString(i) + "\"";
			}
			csvWriter.println(row);
		}
		csvWriter.close();
	}

	public void listDatabase() {
		// ResultSet rs;
		try {

			DatabaseMetaData meta = conn.getMetaData();
			ResultSet res = meta.getSchemas();
			Main.gui.logData(printResult(res));
			res.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void displayAll(String table) {
		try {
			PreparedStatement statement = conn.prepareStatement("SELECT * from " + table);
			ResultSet resultSet = statement.executeQuery();
			Main.gui.logData(Main.dbu.printResult(resultSet));

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	public void listTables() {
		DatabaseMetaData md;
		try {
			md = conn.getMetaData();
			ResultSet rs = md.getTables(null, null, "%", null);

			Main.gui.logData(printResult(rs));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String printResult(ResultSet rs) {
		String s = "\n";
		ResultSetMetaData rsmd;
		try {
			rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount() + 1;

			int[] sps = new int[cols];

			for (int i = 1; i < cols; i++) {
				String columnLabel = rsmd.getColumnLabel(i);

				sps[i] = ((int) Math.ceil(rsmd.getColumnDisplaySize(i) / 3) + columnLabel.length());
				s += String.format("%-" + sps[i] + "s", columnLabel);
			}

			s += "\n";

			while (rs.next()) {
				for (int i = 1; i < cols; i++) {
					String colValue = rs.getString(i);
					s += String.format("%-" + (sps[i]) + "s", colValue);
				}
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
			conn = DriverManager.getConnection(URL/* , USER, PASS */);
		} catch (SQLException e) {
			DBUtil.dispaySQLExceptions(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (!conn.isClosed())
					Main.gui.logData("JDBC Successfully connected to Derby server.");
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
