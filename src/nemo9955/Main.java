package nemo9955;

public class Main {

	public static FacturaDB fdb;

	public static MainGUI gui;

	public static DBUtil dbu;

	public static void main(String[] args) {
		fdb = new FacturaDB();
		gui = new MainGUI();
		dbu = new DBUtil();

	}

}
