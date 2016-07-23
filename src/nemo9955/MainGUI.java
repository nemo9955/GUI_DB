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

		add(makeButton("Connect to the DB", Act.creaza));
		add(makeButton("Arata facturi", Act.afisea));
		add(makeButton("Inserare Factura", Act.insere));

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
		case Act.creaza:
			FacturaDB.createTable();
			break;
		case Act.afisea:
			
			break;
		case Act.insere:
			FacturaDB.insert(Factura.getRandomNewFactura());
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
