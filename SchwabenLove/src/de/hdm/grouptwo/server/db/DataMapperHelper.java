package de.hdm.grouptwo.server.db;

/**
 * Helper class for classes that implement <code>DataMapper</code>.
 * 
 * @author JeromeBadt
 */
public class DataMapperHelper {

	/**
	 * Private constructor to prevent initialization.
	 */
	private DataMapperHelper() {
	}

	/**
	 * Checks an integer for null.
	 * 
	 * @param check
	 *            the integer to check
	 * @return string to be used in an SQL query
	 */
	public static String checkNull(int check) {
		return check == 0 ? "NULL" : Integer.toString(check);
	}

	/**
	 * Checks a string for null.
	 * 
	 * @param check
	 *            the string to check
	 * @return string to be used in an SQL query
	 */
	public static String checkNull(String check) {
		return check == null ? "NULL" : "'" + check + "'";
	}
}
