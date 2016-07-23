package nemo9955;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainGUI extends JFrame implements KeyListener, ActionListener {
	private static final long serialVersionUID = 1L;

	private void initGUI() {
		setLayout(new FlowLayout());
		setTitle("Simple example");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		addKeyListener(this);

		add(makeButton("Connect to the DB", Act.CONECTEAZA));
		add(makeButton("Arata facturi", Act.AFISEAZA_TOT));
		add(makeButton("Inserare Factura", Act.ADAUDA_ALEATOR));
		add(makeButton("Goleste DB", Act.STERGE_TOT));

	}

	private JButton makeButton(String name, String action) {
		JButton theButton = new JButton(name);
		theButton.addActionListener(this);
		theButton.setActionCommand(action);
		return theButton;
	}

	public MainGUI() {
		initGUI();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// inchide fereastra la tasta ESC (pentru convenienta)
		if ((int) arg0.getKeyChar() == 27) {
			setVisible(false);
			dispose();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		switch (arg0.getActionCommand()) {
		case Act.CONECTEAZA:
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

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}
}
