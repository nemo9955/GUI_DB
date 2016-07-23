package nemo9955;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class FacturaDB {

	public static void createTable() {
		Connection conn = DBUtils.getCon();
		boolean exists = false;
		try {
			DatabaseMetaData dbmd = conn.getMetaData();
			String st[] = { "TABLE" };
			ResultSet rs = dbmd.getTables(null, null, null, st);
			if (rs.next()) {
				if (rs.getString(3).equals("FACTURA"))
					exists = true;
			}
			System.out.println("Table " + rs.getString(3) + " exists");

		} catch (SQLException e) {
			DBUtils.SQLEx(e);
		}

		if (!exists) {
			try {
				Statement s = conn.createStatement();
				s.execute("CREATE TABLE FACTURA" //
						+ "(ID INTEGER NOT NULLGENERATED ALWAYS AS"//
						+ "IDENTITY(START WITH 0,INCREMENT BY 1)," //
						+ "nrFactura INTEGER, client VARCHAR(20),"//
						+ "furnizor VARCHAR(20), suma DOUBLE, data DATE," //
						+ "CONSTRAINT primary key 2 PRIMARY KEY(ID)");//
				conn.commit();
			} catch (SQLException e) {
				DBUtils.SQLEx(e);
			}
		}
	}

	public static void insert(Factura f) {
		Connection conn = DBUtils.getCon();
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO " //
					+ "factura(nrFactura,client,furnizor,suma,data) "//
					+ "VALUES(?,?,?,?,?)"); //
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

}
