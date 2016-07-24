package nemo9955;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class FacturaDB {
	
	
	public void createTable() {
		Connection conn = Main.dbu.getConnection();

		boolean tableExists = false;
		try {
			DatabaseMetaData dbmd = conn.getMetaData();

			ResultSet rs = dbmd.getTables(null, null, null, new String[] { "TABLE" });
			if (rs.next()) {
				if (rs.getString(3).equals("FACTURA")) {
					tableExists = true;
				}
				System.out.println("Table " + rs.getString(3) + " exists");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (!tableExists) {
			try {
				Statement s = conn.createStatement();
				s.execute("CREATE TABLE factura "//
						+ "(ID  INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY "//
						+ "(START WITH 0, INCREMENT BY 1), "//
						+ "nrFactura INTEGER, "//
						+ "client VARCHAR(20), "//
						+ "furnizor VARCHAR(20), "//
						+ "suma DOUBLE, "//
						+ "data DATE, "//
						+ "CONSTRAINT primary_key_2 PRIMARY KEY (ID))");//

				conn.commit();

			} catch (SQLException ex) {

				DBUtil.dispaySQLExceptions(ex);

			}
		}
	}

	public void insertFactura(Factura f) {
		Connection conn = Main.dbu.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO " //
					+ "factura(nrFactura,client,furnizor,suma,data)VALUES(?,?,?,?,?)");
			ps.setInt(1, f.getNrFactura());
			ps.setString(2, f.getClient());
			ps.setString(3, f.getFurnizor());
			ps.setDouble(4, f.getSuma());
			ps.setTimestamp(5, new Timestamp(f.getData().getTime()));
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void dropTable() {
		try {
			Connection connect = Main.dbu.getConnection();
			PreparedStatement statement = connect.prepareStatement("DROP TABLE factura");

			statement.executeUpdate();
		} catch (

		Exception e) {
			 e.printStackTrace();
		} 
	}

	// public static void main(String args[]) {
	// FacturaDB test = new FacturaDB();
	// test.createTable();
	// test.insertFactura(Factura.getRandomNewFactura());
	// test.displayAll();
	// test.displayInTimePeriod();
	// }

}