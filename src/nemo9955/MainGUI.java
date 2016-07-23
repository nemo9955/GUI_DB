package nemo9955;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MainGUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private void initGUI() {
		setLayout(new GridBagLayout());
		setTitle("DB Master 42");
		setSize(300, 200);
		setLocationRelativeTo(null);

		addKeyListener(windowListeners);

	}

	public MainGUI() {
		initGUI();
		addElements();
		
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void addElements() {

		addGBElement(makeButton("Create the DB", Act.CREEAZA), 0, 0, 3, 1);
		addGBElement(makeButton("Arata facturi", Act.AFISEAZA_TOT), 0, 1);
		addGBElement(makeButton("Inserare Factura", Act.ADAUDA_ALEATOR), 0, 2);
		addGBElement(makeButton("Goleste DB", Act.STERGE_TOT), 0, 3);

		JTextArea outputArea = new JTextArea("DB Master 42!", 500, 300);
		JScrollPane scrollPane = new JScrollPane(outputArea);
		outputArea.setEditable(false);
		con.fill = GridBagConstraints.BOTH;
		con.gridx = 1;
		con.gridy = 1;
		con.gridheight =4;
		con.gridwidth = 2;
		add(scrollPane, con);

	}

	GridBagConstraints con = new GridBagConstraints();

	private void addGBElement(Component compon, int x, int y, int w, int h) {
		con.fill = GridBagConstraints.BOTH;

		con.gridx = x;
		con.gridy = y;

		con.gridheight = h;
		con.gridwidth = w;

		add(compon, con);
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
			Main.fdb.displayAll();
			break;
		case Act.ADAUDA_ALEATOR:
			Main.fdb.insertFactura(Factura.getRandomNewFactura());
			break;
		case Act.STERGE_TOT:
			Main.fdb.dropTable();
			break;

		default:
			break;
		}

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
