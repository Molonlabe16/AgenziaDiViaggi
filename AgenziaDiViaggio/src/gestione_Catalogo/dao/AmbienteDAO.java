package gestione_Catalogo.dao;

import gestione_Catalogo.entity.Ambiente;
import gestione_Catalogo.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public class AmbienteDAO extends DAO {

	private static AmbienteDAO istanza = null;

	private static final String getListaAmbientiQuery = "SELECT * FROM `ambienti` WHERE 1";

	private static final String createQuery = 
			"CREATE TABLE IF NOT EXISTS AMBIENTE(" +
					"ID INTEGER PRIMARY KEY, " +
					"IDESTERNOELEMENTO VARCHAR(30) " +
					")";

	private static final String insertQuery = 
			"INSERT INTO AMBIENTE " +
			"VALUES(?, ?)";
	private static final String updateQuery = 
			"UPDATE AMBIENTE SET " +
			"IDESTERNOELEMENTO=? " +
			"WHERE ID=?";
	private static final String deleteQuery = 
			"DELETE FROM " +
			"AMBIENTE WHERE ID=?";
	private static final String findQuery = 
			"SELECT * FROM AMBIENTE " +
			"WHERE ID=?";
	
	/*private static final String findExistsQuery = 
			"SELECT EXISTS( " +
			"SELECT * FROM Ambienti " +
			"WHERE value = ?)";
	*/

	private static final String dropQuery = "DROP TABLE AMBIENTE IF EXISTS";

	
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	
	private AmbienteDAO() {
		try {
			Class.forName(driverName);

			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(createQuery);

			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResource();
		}
	}

	public static AmbienteDAO getIstanza() {
		if (istanza == null)
			istanza = new AmbienteDAO();
		return istanza;
	}

	@Override
	public void insert(Object obj) throws DAOException {
		// TODO Auto-generated method stub
		ResultSet rs;
		Ambiente ambiente;
		try {
			
			ambiente = (Ambiente) obj;
			//Situazione 1. Tabella vuota. Inserisco.
			
			conn = getConnection(usr, pass);
			ps = conn.prepareStatement(getListaAmbientiQuery);
			rs = ps.executeQuery();
			if(!rs.next()){
				ps = conn.prepareStatement(insertQuery);

				ps.setInt(1, ambiente.getID());
				ps.setString(2, ambiente.getIDEsternoElemento().toString());

				ps.executeUpdate();
			}
			
			//Situazione 2. Elemento non presente.
			ps = conn.prepareStatement(findQuery);

			ps.setInt(1, ambiente.getID());
			
			rs = ps.executeQuery();
			
			if(!rs.next()){
				ps = conn.prepareStatement(insertQuery);
	
				ps.setInt(1, ambiente.getID());
				ps.setString(2, ambiente.getIDEsternoElemento().toString());
	
				ps.executeUpdate();
			}
			//Situazione 3.Elemento Presente. Non inserisco.

		} catch (ClassCastException e) {
			throw new DAOException("Errore in insert ClassCastException.");
		} catch (SQLException e) {
			throw new DAOException("Errore in insert SQLException.");
		}

	}

	@Override
	public Ambiente read(Integer id) throws DAOException {
		Ambiente ambiente = new Ambiente();
		try {
			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(findQuery);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			rs.next();
			ambiente.setId(rs.getInt(1));
			ambiente.setValore(rs.getString(2));

			return ambiente;

		} catch (ClassCastException e) {
			throw new DAOException("Errore in read.");
		} catch (SQLException e) {
			throw new DAOException("Errore in read.");
		}
	}

	@Override
	public void update(Object obj) throws DAOException {
		// TODO Auto-generated method stub
		Ambiente ambiente;
		try {
			ambiente = (Ambiente) obj;

			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(updateQuery);

			ps.setInt(1, ambiente.getID());
			ps.setString(2, ambiente.getIDEsternoElemento().toString());
			ps.setInt(3, ambiente.getID());

			ps.executeUpdate();

		} catch (ClassCastException e) {
			throw new DAOException("Errore in update.");
		} catch (SQLException e) {
			throw new DAOException("Errore in update.");
		}

	}

	@Override
	public void delete(Object obj) throws DAOException {
		// TODO Auto-generated method stub
		Ambiente ambiente;
		try {
			ambiente = (Ambiente) obj;

			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(deleteQuery);

			ps.setInt(1, ambiente.getID());

			ps.executeUpdate();

		} catch (ClassCastException e) {
			throw new DAOException("Errore in delete.");
		} catch (SQLException e) {
			throw new DAOException("Errore in delete.");
		}
	}

	/*private boolean isInDatabase(Object obj) throws DAOException {
		// TODO Auto-generated method stub
		Ambiente ambiente;
		try {
			ambiente = (Ambiente) obj;

			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(findExistsQuery);

			ps.setString(1, ambiente.getValore());

			rs = ps.executeQuery();
			
			rs.next();
			if((rs.getString(1)).equals("1"))
				return true;
			return false;

		} catch (ClassCastException e) {
			throw new DAOException("Errore in delete.");
		} catch (SQLException e) {
			throw new DAOException("Errore in delete.");
		}
	}*/

	public void printListaAmbienti() throws DAOException {
		try {
			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(getListaAmbientiQuery);

			rs = ps.executeQuery();
			System.out.println("Lista ambienti.");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2));
			}
		} catch (ClassCastException e) {
			throw new DAOException("Errore in printListaAmbienti.");
		} catch (SQLException e) {
			throw new DAOException("Errore in printListaAmbienti.");
		}
	}
	
	public Integer getIdByValue(String valore) throws DAOException {
		String query = "SELECT * FROM AMBIENTE WHERE IDESTERNOELEMENTO = ?";
		try {
			conn = getConnection(usr, pass);
			
			//Situazione 1. Tabella Vuota. Id da ritornare 1.
			ps = conn.prepareStatement(getListaAmbientiQuery);
			rs = ps.executeQuery();
			if(!rs.next()){
				return 1;
			}
			//Situazione 2. Elemento presente
			
			ps = conn.prepareStatement(query);

			ps.setString(1, valore);

			rs = ps.executeQuery();
			if(rs.next()){
				return rs.getInt(1); 
			}
			
			// Situazione 3. Elemento non presente.
			ps = conn.prepareStatement(getListaAmbientiQuery);
			
			rs = ps.executeQuery();
			rs.last();
			return rs.getInt(1) + 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException("Errore in getIDByValue in SQL.");
		}
	}
	
	/**
	 * Se l'id passato non esiste lancia un'eccezione.
	 * @param id
	 * @return
	 */
	public String getValueById(Integer id) throws DAOException{
		String query = "SELECT * FROM AMBIENTE WHERE ID=?";
		ResultSet rs = null;
		try {
			//Situazione 2. Elemento presente
			
			ps = conn.prepareStatement(query);

			ps.setInt(1, id);

			rs = ps.executeQuery();
			if(rs.next()){
				return rs.getString(2); 
			}
			
			throw new DAOException("Errore in getValue.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException("Errore in getValue.");
		}
		
	}

	public void dropTable() throws DAOException {
		try {
			conn = getConnection(usr, pass);

			ps = conn.prepareStatement(dropQuery);

			ps.executeUpdate();
		} catch (ClassCastException e) {
			throw new DAOException("Errore in dropTable.");
		} catch (SQLException e) {
			throw new DAOException("Errore in dropTable.");
		}
	}

}
