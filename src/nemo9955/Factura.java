package nemo9955;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Factura {
	private int nrFactura;
	private String furnizor;
	private String client;
	private double suma;
	private Date data;

	static DateFormat df = new SimpleDateFormat("dd-mm-yyyy");

	public Factura(int nrFactura, String furnizor, String client, double suma, Date data) {
		super();
		this.nrFactura = nrFactura;
		this.furnizor = furnizor;
		this.client = client;
		this.suma = suma;
		this.data = data;
	}

	@Override
	public String toString() {
		return "Factura [nrFactura=" + nrFactura + ", Furnizor=" + furnizor + ", client=" + client + ", suma=" + suma
				+ ", data=" + data + "]";
	}

	public int getNrFactura() {
		return nrFactura;
	}

	public void setNrFactura(int nrFactura) {
		this.nrFactura = nrFactura;
	}

	public String getFurnizor() {
		return furnizor;
	}

	public void setFurnizor(String furnizor) {
		this.furnizor = furnizor;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public double getSuma() {
		return suma;
	}

	public void setSuma(double suma) {
		this.suma = suma;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	/**
	 * 
	 * int nrFactura;
	 * 
	 * String furnizor;
	 * 
	 * String client;
	 * 
	 * double suma;
	 * 
	 * Date data;
	 * 
	 * @param arr
	 * @return
	 */
	public static Factura getInstanceFromStringArray(String[] arr) {

		int start = 1;

		int nrFactura = Integer.parseInt(arr[start++]);
		String client = arr[start++];
		String furnizor = arr[start++];
		Date data = null;
		double suma = Double.parseDouble(arr[start++]);
		try {
			data = df.parse(arr[start++]);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Factura f = new Factura(nrFactura, furnizor, client, suma, data);
		return f;
	}

	public static Factura getRandomNewFactura() {
		Factura f = null;
		Random r = new Random();
		try {
			f = new Factura(r.nextInt(100) + 100, "ceva " + r.nextInt(10), "eu " + r.nextInt(10), r.nextInt(100) + 300,
					df.parse(String.format("%d-%d-%d", r.nextInt(100), r.nextInt(100), 1000 + r.nextInt(2000))));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return f;
	}
}