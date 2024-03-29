package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.regex.Pattern;

import kdesp73.databridge.connections.DatabaseConnection;
import kdesp73.databridge.connections.MSAccessConnection;

public class Database {
	private static final String FILEPATH = System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/")
			+ "/data/MoviesDatabase.accdb";

	private static DatabaseConnection instance;

	private Database() {
        // Private constructor to prevent external instantiation
    }

    public static DatabaseConnection connection() {
		synchronized (DatabaseConnection.class) {
			try {
				// Initialize the database connection here
				String url = "jdbc:ucanaccess://" + FILEPATH;
				String username = "";
				String password = "";
				instance = new MSAccessConnection();
				instance.connect(url, username, password);
			} catch (RuntimeException e) {
				e.printStackTrace();
				throw new RuntimeException("Failed to create the database connection.");
			}
		}
        return instance;
    }

}
