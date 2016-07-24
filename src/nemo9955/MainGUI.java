package nemo9955;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MainGUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	JPanel jpan = new JPanel(new GridBagLayout());

	private JTextArea inputArea;
	private JTextArea outputArea;

	private void initGUI() {
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		setTitle("DB Master 42");
		setSize(800, 600);
		setLocationRelativeTo(null);

		addKeyListener(windowListeners);

	}

	public MainGUI() {
		initGUI();
		addElements();

		add(jpan);

		// pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void addElements() {
		int but = 0;
		addGBElement(makeButton("RUN", Act.RUN_COMMAND), 0, but++);
		addGBElement(makeButton("RUN to CSV", Act.EXPORT_TO_CSV), 0, but++);
		addGBElement(makeButton("Create the Table", Act.CREEAZA), 0, but++);
		addGBElement(makeButton("Show facturi", Act.AFISEAZA_TOT), 0, but++);
		addGBElement(makeButton("Add to Factura", Act.ADAUDA_ALEATOR), 0, but++);
		addGBElement(makeButton("Delete FACTURA", Act.STERGE_TOT), 0, but++);
		addGBElement(makeButton("List DB", Act.LIST_DATABASE), 0, but++);
		addGBElement(makeButton("List Tables", Act.LIST_TABLES), 0, but++);
		addGBElement(makeButton("Import Data", Act.IMPORT_AND_ADD_CSV), 0, but++);

		inputArea = new JTextArea("SELECT * FROM FACTURA", 300, 40);
		inputArea.setFont(new Font("monospaced", Font.PLAIN, 12));
		JScrollPane scrollInPane = new JScrollPane(inputArea);

		con.fill = GridBagConstraints.BOTH;
		con.gridx = 1;
		con.gridy = 0;
		con.gridheight = 2;
		con.weighty = 1;
		con.weightx = 1;
		con.ipadx = 400;
		jpan.add(scrollInPane, con);

		outputArea = new JTextArea("DB Master 42!", 300, 40);
		outputArea.setFont(new Font("monospaced", Font.PLAIN, 12));
		JScrollPane scrollPane = new JScrollPane(outputArea);
		outputArea.setEditable(false);

		con.fill = GridBagConstraints.BOTH;
		con.gridx = 1;
		con.gridy = 2;
		con.weighty = 1;
		con.weightx = 1;
		con.gridheight = 6;
		con.gridwidth = 1;
		con.ipadx = 400;
		jpan.add(scrollPane, con);

	}

	GridBagConstraints con = new GridBagConstraints();

	private void addGBElement(Component compon, int x, int y, int w, int h) {
		con.fill = GridBagConstraints.HORIZONTAL;

		con.gridx = x;
		con.gridy = y;

		con.gridheight = h;
		con.gridwidth = w;

		jpan.add(compon, con);
	}

	private void addGBElement(Component compon, int x, int y) {
		addGBElement(compon, x, y, 1, 1);
	}

	private JButton makeButton(String name, String action) {
		JButton theButton = new JButton(name);
		theButton.addActionListener(this);
		theButton.setActionCommand(action);
		return theButton;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		switch (arg0.getActionCommand()) {
		case Act.CREEAZA:
			Main.fdb.createTable();
			break;
		case Act.AFISEAZA_TOT:
			Main.dbu.displayAll("factura");
			break;
		case Act.ADAUDA_ALEATOR:
			Main.fdb.insertFactura(Factura.getRandomNewFactura());
			break;
		case Act.STERGE_TOT:
			Main.fdb.dropTable();
			break;
		case Act.LIST_DATABASE:
			Main.dbu.listDatabase();
			break;
		case Act.LIST_TABLES:
			Main.dbu.listTables();
			break;
		case Act.RUN_COMMAND:
			Main.dbu.runCommand(inputArea.getText(), Act.ComandResult.TO_OUTPUT);
			System.out.println(inputArea.getText());
			inputArea.setText("");
			break;
		case Act.EXPORT_TO_CSV:
			Main.dbu.runCommand(inputArea.getText(), Act.ComandResult.TO_CSV);
			System.out.println(inputArea.getText());
			inputArea.setText("");
			break;
		case Act.IMPORT_AND_ADD_CSV:
			Main.dbu.imporFromCSV();
			break;

		default:
			break;
		}

	}

	public void logData(String s) {

		outputArea.setText(outputArea.getText() + "\n" + s);
	}

	private KeyListener windowListeners = new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println(e);
			// inchide fereastra la tasta ESC (pentru convenienta)
			if ((int) e.getKeyChar() == 27) {
				setVisible(false);
				dispose();
			}

		}
	};
}
