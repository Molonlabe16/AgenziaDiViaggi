package gestioneutenti.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WebConnectionManager implements ConnectionManager {
	
	private static WebConnectionManager singletonConnectionManager = null;
	
	private DataSource dataSource;
    private Connection connection;

	private WebConnectionManager() {
		try {
            Context initContext  = new InitialContext();
            Context envContext  = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/VoyagerUtentiDB");             
        } catch (NamingException e) {
            e.printStackTrace();
        }
	}
	
	public static synchronized WebConnectionManager getInstance() {
		if (singletonConnectionManager == null) {
			singletonConnectionManager = new WebConnectionManager();
		}
		
		return singletonConnectionManager;
	}
	
	@Override
	public Connection getConnection() {
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	@Override
	public void createDB() {
		String SQL_CREA_DATABASE = "CREATE DATABASE `VoyagerUtenti`";
		String SQL_CREA_TABELLA_UTENTI = "CREATE TABLE `VoyagerUtenti`.`utenti` ( " +
				"username VARCHAR(20) PRIMARY KEY UNIQUE, " +
				"password VARCHAR(20), " + 
				"nome VARCHAR(20), " + 
				"cognome VARCHAR(20), " + 
				"citta VARCHAR(20), " + 
				"nascita VARCHAR(20), " + 
				"sesso VARCHAR(20), " + 
				"mail VARCHAR(20), " + 
				"ruolo VARCHAR(20), " + 
				"attr VARCHAR(20))";				
				
		Statement statement = null;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(SQL_CREA_DATABASE);
			statement.executeUpdate(SQL_CREA_TABELLA_UTENTI);
		} catch (SQLException exc) {
			exc.printStackTrace();
		} finally {
			this.close(connection, statement);
		}
	}
	
	@Override
	public synchronized void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException exc) {
				exc.printStackTrace();
			}
		}
	}
	
	@Override
	public synchronized void close(Connection connection, Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException exc) {
				exc.printStackTrace();
			}
		}
		
		close(connection);
	}
	
	@Override
	public synchronized void close(Connection connection, Statement statement, ResultSet result) {
		if (result != null) {
			try {
				result.close();
			} catch (SQLException exc) {
				exc.printStackTrace();
			}
		}
		
		close(connection, statement);
	}

}
