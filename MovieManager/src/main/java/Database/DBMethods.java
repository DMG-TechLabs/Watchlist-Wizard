package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Files.ImagesUtils;
import info.movito.themoviedbapi.model.Data;
import kdesp73.databridge.helpers.QueryBuilder;
import kdesp73.databridge.helpers.ResultProcessor;
import kdesp73.databridge.helpers.ResultRow;
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
		String[] genre = DBUtils.genreToArray(m);

		int[] ids = new int[genre.length];

		ResultProcessor resultProcessor = new ResultProcessor();
		ResultSet rs;

		for (int i = 0; i < genre.length; i++) {
			// Check if genre exists. If not add it and continue
			rs = Database.connection().executeQuery("SELECT Category FROM Categories WHERE Category = '" + genre[i] + "'");
			rs.next();
			if (!rs.next()) {
				System.out.println("Genre doesn't exist");
				System.out.println("Adding genre...");

				Database.connection().executeUpdate(new QueryBuilder().insertInto("Categories").columns("Category").values(genre[i]).build());

				System.out.println("Genre added.");
			}

			String query = "SELECT Category_ID FROM Categories WHERE Category=\'" + genre[i] + "\'";
			// System.out.println(query);

			rs = Database.connection().executeQuery(query);

			while (rs.next()) {
				ids[i] = rs.getInt(1);
			}
		}

		return ids;
	}

	private static void matchCategories(Movie m) throws SQLException { // Adds Category id's and imdb id in the in
																		// beteween table
		int[] ids = matchCategoryID(m);

		for (int i = 0; i < ids.length; i++) {
			Database.connection().executeUpdate("INSERT INTO Category_Matching(Category_ID, IMDb_ID) VALUES(" + ids[i] + ", \'"
					+ m.getImdbID() + "\')");
		}

	}

	private static String matchCategories(int categoryId) throws SQLException { // Adds Category id's and imdb id in the
																				// in beteween table
		String query = "SELECT Category FROM Categories WHERE Category_ID = " + categoryId;
		ResultSet rs = Database.connection().executeQuery(query);

		rs.next();

		return rs.getString(1);
	}

	public static void insertMovie(Movie m) {
		String query = "INSERT INTO Movies(" + DBUtils.columnsToList(DBFields) + ") VALUES(" + DBUtils.objectToList(m)
				+ ")";

		Database.connection().executeUpdate(query);
	}

	public static ArrayList<String[]> getMovies() throws SQLException {
		ArrayList<String[]> list = new ArrayList<>();

		String query = "SELECT " + DBUtils.columnsToList(DBFields) + " FROM Movies";
		ResultSet rs = Database.connection().executeQuery(query);

		while (rs.next()) {
			String[] str = new String[DBFields.length];

			for (int i = 0; i < DBFields.length; i++) {
				str[i] = rs.getString(i + 1);
			}

			list.add(str);
		}

		return list;
	}

	public static ArrayList<String> getCategories(String id) throws SQLException {
		ArrayList<String> genres = new ArrayList<>();
		ArrayList<Integer> categoryIDs = new ArrayList<>();

		String query = "SELECT Category_ID FROM Category_Matching WHERE IMDb_ID = \'" + id + "\'";
		ResultSet rs = Database.connection().executeQuery(query);

		while (rs.next()) {
			categoryIDs.add(rs.getInt(1));
		}

		for (int i = 0; i < categoryIDs.size(); i++) {
			genres.add(matchCategories(categoryIDs.get(i)));
		}

		return genres;
	}

	public static void getFilename(Statement s, Movie m) throws SQLException {
		String query = "SELECT Filename FROM Filenames WHERE Filename = " + m.getImdbID();

		ResultSet rs = s.executeQuery(query);

		rs.next();
		m.setFilename(rs.getString(1));
	}

	public static void formatDatabase() throws SQLException {
		Database.connection().execute("TRUNCATE TABLE Movies");
		Database.connection().execute("TRUNCATE TABLE Filepaths");
		Database.connection().execute("TRUNCATE TABLE Scraped");
		Database.connection().execute("TRUNCATE TABLE Category_Matching");

		ResultProcessor rp = new ResultProcessor();
		ResultSet rs = Database.connection().executeQuery(new QueryBuilder().select("Image_Directory").from("Images").build());

		List<ResultRow> table = rp.toList(rs);

		for(ResultRow row : table) {
			ImagesUtils.delete(row.get("Image_Directory"));
		}

		Database.connection().execute("TRUNCATE TABLE Images");
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
				s = new String[] { "null" };
			}

			return s;
		}

	}

}
