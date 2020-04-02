package window;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * An object that represents a mySQL database. It provides methods for all
 * needed queries for the rest of the application.
 * 
 * @author Arne
 *
 */
public class Database {

	private static Database instance = null;
	private String dbURL = "w0186fcf.kasserver.com";
	private String dbURI;
	private String dbUser;
	private String dbPass;
	private Connection con;

	/**
	 * Returns the existing database object. If none exists, a database is created.
	 * 
	 * @return the Database object
	 */
	public static Database db() {
		if (Database.instance == null) {
			instance = new Database();
		}
		return instance;
	}

	private Database() throws IllegalStateException {

		this.dbURI = "jdbc:mysql://" + dbURL + ":3306/d031cb54";
		this.dbUser = "d031cb54";
		this.dbPass = "w54aEfBbzDv9mMrb";

		System.out.println("Connecting database...");
		try {
			this.con = DriverManager.getConnection(dbURI, dbUser, dbPass);
			System.out.println("Database connected!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Cannot connect the database.");
			throw new IllegalStateException("Cannot connect the database.", e);
		}
	}

	@SuppressWarnings("javadoc")
	public String getDbURL() {
		return dbURL;
	}

	/**
	 * Checks the login credentials against the username and password stored in the
	 * database.
	 * 
	 * @param name the username to log in with
	 * @param pw   the password to check
	 * @return true if the the username and password are correct; if the credentials
	 *         are incorrect or an error occurred, the value returned is false
	 */
	public boolean validateCredentials(String name, String pw) {
		boolean valid = false;
		String query = "SELECT * FROM `Benutzer` WHERE name=? AND password=?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			// excecute SQL
			pst = con.prepareStatement(query);
			pst.setString(1, name);
			pst.setString(2, pw);
			rs = pst.executeQuery();
			// process result
			valid = validateLoginResult(rs);
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			// release resources in reverse-order of
			// their creation if they are no-longer needed
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) { // ignore SQLException
				}
				rs = null;
			}
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException sqlEx) { // ignore SQLException
				}
				pst = null;
			}
		}

		return valid;
	}

	private boolean validateLoginResult(ResultSet rs) throws SQLException {
		int size = 0;
		while (rs.next()) {
			size++;
		}
		if (size == 1) {
			return true;
		} else if (size > 1) {
			JOptionPane.showMessageDialog(null, "Duplicate username and password");
		}
		return false;
	}
	
	public TableModel readTable() {
		TableModel tm = null;
		String query = "SELECT * FROM `Benutzer`";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			// execute SQL
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();
			// process result 
			tm = buildTableModel(rs);
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			// release resources in reverse-order of
			// their creation if they are no-longer needed
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) { // ignore SQLException
				}
				rs = null;
			}
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException sqlEx) { // ignore SQLException
				}
				pst = null;
			}
		}

		return tm ;
	}
	
	private DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);

	}

}
