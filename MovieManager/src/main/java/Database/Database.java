package Database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import kdesp73.databridge.connections.*;
import kdesp73.databridge.connections.DatabaseConnection;

public class Database {
	private static final String FILEPATH = System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/")
			+ "/data/MoviesDatabase.accdb";

	public static DatabaseConnection connection() {
		String dbUrl = "jdbc:ucanaccess://" + FILEPATH;
		String dbUsername = ""; // if necessary
		String dbPassword = ""; // if necessary

		// Create an instance of MSAccessConnection
		MSAccessConnection msAccessConnection = new MSAccessConnection();
		try {
			msAccessConnection.connect(dbUrl, dbUsername, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return msAccessConnection;
	}
}
