package gestioneutenti.dao;


/**
 *config_db_utenti contiene alcune costanti per la configurazione della persistenza.
 */
public class config_db_utenti {

	public static final int PERSISTENZA_DATABASE=0;
	public static final int PERSISTENZA_FILESYSTEM=1;
	public static final int TIPO_PERSISTENZA=PERSISTENZA_DATABASE;
	
		
	
	public static final String USER_CONNESSIONE = "Any";
	public static final String PASSWORD_CONNESSIONE = "";
	
	public static final String NOME_DB_UTENTI="test.sql";
	public static final String NOME_TABELLA_UTENTI="utenti";
	
	
	
	
	

	public static final String NOME_DRIVER= "com.mysql.jdbc.Driver";
	public  static final String NOME_URL = "jdbc:mysql://localhost/test?";
	public static final String PERCORSO_COMPLETO_DB = NOME_URL + NOME_DB_UTENTI;

	public static final String COLONNA_USERNAME="username";
	public static final String COLONNA_PASSWORD="password";
	public static final String COLONNA_NOME="nome";
	public static final String COLONNA_COGNOME="cognome";
	public static final String COLONNA_CITTA="citta";
	public static final String COLONNA_NASCITA="nascita";
	public static final String COLONNA_SESSO="sesso";
	public static final String COLONNA_MAIL="mail";
	public static final String COLONNA_RUOLO="ruolo";
	public static final String COLONNA_RCODE="resetCode";
	public static final int INDICE_USERNAME=1;
	public static final int INDICE_PASSWORD=2;
	public static final int INDICE_NOME=3;
	public static final int INDICE_COGNOME=4;
	public static final int INDICE_CITTA=5;
	public static final int INDICE_NASCITA=6;
	public static final int INDICE_SESSO=7;
	public static final int INDICE_MAIL=8;
	public static final int INDICE_RUOLO=9;
	public static final int INDICE_RCODE=10;

	public static final java.text.SimpleDateFormat formattoDateTime = new java.text.SimpleDateFormat("yyyy-MM-dd");


	static public final String QUERY_CREA_TABELLA="CREATE TABLE IF NOT EXISTS "
			+config_db_utenti.NOME_TABELLA_UTENTI+"( "+
			config_db_utenti.COLONNA_USERNAME+" VARCHAR(20) PRIMARY KEY, "+
			config_db_utenti.COLONNA_PASSWORD+" VARCHAR(20), "+
			config_db_utenti.COLONNA_NOME+" VARCHAR(20), "+
			config_db_utenti.COLONNA_COGNOME+" VARCHAR(20), "+
			config_db_utenti.COLONNA_CITTA+" VARCHAR(20), "+
			config_db_utenti.COLONNA_NASCITA+" DATETIME, "+
			config_db_utenti.COLONNA_SESSO+" VARCHAR(20), "+
			config_db_utenti.COLONNA_MAIL+" VARCHAR(50), "+
			config_db_utenti.COLONNA_RUOLO+" INT(2), "+
			config_db_utenti.COLONNA_RCODE+" VARCHAR(50))";
	
	
	static public final String STATEMENT_SALVA="INSERT INTO "
			+NOME_TABELLA_UTENTI+
			" ("+COLONNA_USERNAME+", "
			+COLONNA_PASSWORD+", "
			+COLONNA_NOME+", "
			+COLONNA_COGNOME+", "
			+COLONNA_CITTA+", "
			+COLONNA_NASCITA+", "
			+COLONNA_SESSO+", "
			+COLONNA_MAIL+", "
			+COLONNA_RUOLO+", "
			+COLONNA_RCODE
			+") VALUES(?,?,?,?,?,?,?,?,?,?)";

	static public final String QUERY_SELECT_USERNAME = "SELECT * FROM " 
			+NOME_TABELLA_UTENTI+" WHERE "
			+COLONNA_USERNAME+" = ?";
	
	static public final String QUERY_SELECT_ALL = "SELECT * FROM " 
			+NOME_TABELLA_UTENTI;
	public static final String QUERY_SELECT_NOME = "SELECT * FROM " 
			+NOME_TABELLA_UTENTI+" WHERE "
			+COLONNA_NOME+" = ?";
	public static final String QUERY_SELECT_COGNOME = "SELECT * FROM " 
			+NOME_TABELLA_UTENTI+" WHERE "
			+COLONNA_COGNOME+" = ?";
	public static final String QUERY_SELECT_CITTA = "SELECT * FROM " 
			+NOME_TABELLA_UTENTI+" WHERE "
			+COLONNA_CITTA+" = ?";
	public static final String QUERY_SELECT_SESSO = "SELECT * FROM " 
			+NOME_TABELLA_UTENTI+" WHERE "
			+COLONNA_SESSO+" = ?";
	public static final String QUERY_SELECT_MAIL = "SELECT * FROM " 
			+NOME_TABELLA_UTENTI+" WHERE "
			+COLONNA_MAIL+" = ?";
	public static final String QUERY_SELECT_RUOLO = "SELECT * FROM " 
			+COLONNA_RUOLO+" WHERE "
			+COLONNA_COGNOME+" = ?";
	public static final String QUERY_SELECT_AGEUP = "SELECT * FROM "
			+NOME_TABELLA_UTENTI+" WHERE "
			+COLONNA_NASCITA+" > ? ";
	public static final String QUERY_SELECT_AGEDOWN = "SELECT * FROM " 
			+NOME_TABELLA_UTENTI+" WHERE "
			+COLONNA_NASCITA+" < ? ";
	
	
	
	static public final String STATEMENT_DELETE = "DELETE FROM " 
			+NOME_TABELLA_UTENTI+" WHERE "
			+COLONNA_USERNAME+" = ?";
	
	static public final String STATEMENT_UPDATE = "UPDATE "
			+NOME_TABELLA_UTENTI+" SET "
			+COLONNA_PASSWORD+"= ?, "
			+COLONNA_NOME+"= ?, "
			+COLONNA_COGNOME+"= ?, "
			+COLONNA_CITTA+"= ?, "
			+COLONNA_NASCITA+"= ?, "
			+COLONNA_SESSO+"= ?, "
			+COLONNA_MAIL+"= ?, "
			+COLONNA_RUOLO+"= ?, " 
			+COLONNA_RCODE+"= ? "
			+"WHERE "
			+COLONNA_USERNAME+" = ?";
	
	public static final String QUERY_SELECT_USR_PASS =  "SELECT * FROM " 
			+NOME_TABELLA_UTENTI+" WHERE "
			+COLONNA_USERNAME+" = ?, AND"
			+COLONNA_PASSWORD+" = ?";
	
	
} 