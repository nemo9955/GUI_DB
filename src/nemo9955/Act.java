package nemo9955;

public class Act {
	public static final String RUN_COMMAND = "ruleaza comanda";
	public static final String EXPORT_TO_CSV = "ruleaza comanda si exporta in CSV";
	public static final String CREEAZA = "conecteaza / creaza BD";
	public static final String ADAUDA_ALEATOR = "adauga element aleator";
	public static final String AFISEAZA_TOT = "printeaza intreaga BD";
	public static final String STERGE_TOT = "goleste baza de date";

	public static final String LIST_DATABASE = "listeaza bazele de date";
	public static final String LIST_TABLES = "listeaza tabelele";
	public static final String IMPORT_AND_ADD_CSV = "import CSV into table";

	public static enum ComandResult {
		TO_OUTPUT, TO_CSV
	}

}
