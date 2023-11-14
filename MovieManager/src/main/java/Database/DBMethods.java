package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Files.ImagesUtils;
import kdesp73.databridge.connections.DatabaseConnection;
import kdesp73.databridge.helpers.QueryBuilder;
import main.Movie;

public class DBMethods {

	private static String[] DBFields = {
		"Title",
		"Completion_Year",
		"Rated",
		"Release_Date",
		"Runtime",
		"Director",
		"Writers",
		"Actors",
		"Plot",
		"Language",
		"Country",
		"Awards",
		"Poster_URL",
		"IMDb_Rating",
		"IMDB_ID"
	};

	public static String[] getDBFields() {
		return DBFields;
	}

	// Adds genres to DB and returns their Category_ID's
	private static int[] matchCategoryID(Movie m) throws SQLException {
		DatabaseConnection db = Database.connection();

		String[] genre = DBUtils.genreToArray(m);

		int[] ids = new int[genre.length];

		ResultSet rs;

		for (int i = 0; i < genre.length; i++) {
			// Check if genre exists. If not add it and continue
			rs = db.executeQuery("SELECT Category FROM Categories WHERE Category = '" + genre[i] + "'");
			rs.next();
			if (!rs.next()) {
				System.out.println("Genre doesn't exist");
				System.out.println("Adding genre...");

				db.executeUpdate(new QueryBuilder().insertInto("Categories").columns("Category").values(genre[i]).build());

				System.out.println("Genre added.");
			}

			String query = "SELECT Category_ID FROM Categories WHERE Category=\'" + genre[i] + "\'";
			// System.out.println(query);

			rs = db.executeQuery(query);

			while (rs.next()) {
				ids[i] = rs.getInt(1);
			}
		}

		db.close();
		return ids;
	}

	private static void matchCategories(Movie m) throws SQLException { // Adds Category id's and imdb id in the in
		// beteween table
		DatabaseConnection db = Database.connection();
		int[] ids = matchCategoryID(m);

		for (int i = 0; i < ids.length; i++) {
			db.executeUpdate("INSERT INTO Category_Matching(Category_ID, IMDb_ID) VALUES(" + ids[i] + ", \'"
					+ m.getImdbID() + "\')");
		}
		db.close();
	}

	private static String matchCategories(int categoryId) throws SQLException { // Adds Category id's and imdb id in the
		// in beteween table

		DatabaseConnection db = Database.connection();
		String query = "SELECT Category FROM Categories WHERE Category_ID = " + categoryId;
		ResultSet rs = db.executeQuery(query);

		rs.next();

		db.close();
		return rs.getString(1);
	}

	public static void insertMovie(Movie m) {
		DatabaseConnection db = Database.connection();
		String query = "INSERT INTO Movies(" + DBUtils.columnsToList(DBFields) + ") VALUES(" + DBUtils.objectToList(m)
				+ ")";

		db.executeUpdate(query);
		db.close();
	}

	public static ArrayList<String[]> getMovies() throws SQLException {
		DatabaseConnection db = Database.connection();
		ArrayList<String[]> list = new ArrayList<>();

		String query = "SELECT " + DBUtils.columnsToList(DBFields) + " FROM Movies";
		ResultSet rs = db.executeQuery(query);

		while (rs.next()) {
			String[] str = new String[DBFields.length];

			for (int i = 0; i < DBFields.length; i++) {
				str[i] = rs.getString(i + 1);
			}

			list.add(str);
		}
		db.close();
		return list;
	}

	public static ArrayList<String> getCategories(String id) throws SQLException {
		DatabaseConnection db = Database.connection();
		ArrayList<String> genres = new ArrayList<>();
		ArrayList<Integer> categoryIDs = new ArrayList<>();

		String query = "SELECT Category_ID FROM Category_Matching WHERE IMDb_ID = \'" + id + "\'";
		ResultSet rs = db.executeQuery(query);

		while (rs.next()) {
			categoryIDs.add(rs.getInt(1));
		}

		for (int i = 0; i < categoryIDs.size(); i++) {
			genres.add(matchCategories(categoryIDs.get(i)));
		}

		db.close();
		return genres;
	}

	public static void getFilename(Statement s, Movie m) throws SQLException {
		String query = "SELECT Filename FROM Filenames WHERE Filename = " + m.getImdbID();

		ResultSet rs = s.executeQuery(query);

		rs.next();
		m.setFilename(rs.getString(1));
	}

	public static void formatDatabase() throws SQLException {
		DatabaseConnection db = Database.connection();
		db.executeUpdate("DELETE FROM Movies");
		db.executeUpdate("DELETE FROM Filepaths");
		db.executeUpdate("DELETE FROM Scraped");
		db.executeUpdate("DELETE FROM Category_Matching");

		ResultSet rs = db.executeQuery(new QueryBuilder().select("Image_Directory").from("Images").build());

		while (rs.next()) {
			ImagesUtils.delete(rs.getString("Image_Directory"));
		}

		db.executeUpdate("DELETE FROM Images");
		db.close();
	}

	private class DBUtils {

		public static String arrayToList(int[] arr) {
			String s = "";

			for (int i = 0; i < arr.length; i++) {
				if (i == arr.length - 1) {
					s = s.concat("" + arr[i]);
				} else {
					s = s.concat(arr[i] + ", ");
				}
			}

			return s;
		}

		public static String columnsToList(String[] arr) {
			String s = "";

			for (int i = 0; i < arr.length; i++) {
				if (i == arr.length - 1) {
					s = s.concat(arr[i]);
				} else {
					s = s.concat(arr[i] + ", ");
				}
			}

			return s;
		}

		public static String stringToList(String[] arr) {
			String s = "";

			for (int i = 0; i < arr.length; i++) {
				if (i == arr.length - 1) {
					s = s.concat("\'" + arr[i] + "\'");
				} else {
					s = s.concat("\'" + arr[i] + "\', ");
				}
			}

			return s;
		}

		public static String arrayToList(ArrayList<String> arr) {
			String s = "";

			for (int i = 0; i < arr.size(); i++) {
				if (i == arr.size() - 1) {
					s = s.concat(arr.get(i));
				} else {
					s = s.concat(arr.get(i) + ", ");
				}
			}

			return s;
		}

		public static String objectToList(Movie m) {
			String[] info = m.getInfo();
			String s = "";

			for (int i = 0; i < info.length; i++) {
				if (info[i] == null) {
					if (i == info.length - 1) {
						s = s.concat("\'" + info[i] + "\'");
					} else {
						s = s.concat("\'" + info[i] + "\', ");
					}
				} else {
					if (i == info.length - 1) {
						s = s.concat("\'" + info[i].replaceAll("\'", "\\\'") + "\'");
					} else {
						s = s.concat("\'" + info[i].replaceAll("\'", "\\\'") + "\', ");
					}
				}
			}
			return s;
		}

		public static String[] genreToArray(Movie m) {
			String[] s;
			// s = m.getGenre();
			try {
				s = m.getGenre().split(", ");
			} catch (NullPointerException e) {
				System.out.println("ERROR");
				s = new String[]{"null"};
			}

			return s;
		}

	}

}
