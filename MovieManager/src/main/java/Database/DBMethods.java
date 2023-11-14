package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.dbcp.DbcpException;

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

	public static int selectMovieId(String imdb_id) {
		DatabaseConnection db = Database.connection();

		ResultSet rs = db.executeQuery(new QueryBuilder().select("movie_id").from("Movies").where("imdb_id = \'" + imdb_id + "\'").build());

		int id = -1;
		try {
			rs.next();
			id = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		db.close();
		return id;
	}

	public static void insertMovie(Movie m) {
		String insertQuery = "INSERT INTO Movies (title, year, runtime, plot, image_url, image_path, imdb_rating, imdb_id, filepath) "
				+ "VALUES("
				+ "\'" + m.getTitle() + "\'"
				+ "\'" + m.getYear() + "\'"
				+ "\'" + m.getRuntime() + "\'"
				+ "\'" + m.getPlot() + "\'"
				+ "\'" + m.getPoster() + "\'"
				+ "\'" + m.getImagePath() + "\'"
				+ "\'" + m.getImdbRating() + "\'"
				+ "\'" + m.getImdbID() + "\'"
				+ "\'" + m.getDirectory() + "\'"
				+ ")";

		DatabaseConnection db = Database.connection();

		db.executeUpdate(insertQuery);

		db.close();
	}

	public static ArrayList<Movie> getMovies() throws SQLException {
		DatabaseConnection db = Database.connection();
		ArrayList<Movie> list = new ArrayList<>();

		ResultSet moviesRS = db.executeQuery(new QueryBuilder().select().from("Movies").build());

		while (moviesRS.next()) {
			Movie m = new Movie();

			String imdb_id = moviesRS.getString("imdb_id");
			if (imdb_id != null || "".equals(imdb_id)) {
				m.setImdbID(imdb_id);
				m.setImdbRating(moviesRS.getString("imdb_rating"));
				m.setPlot(moviesRS.getString("plot"));
				m.setYear(moviesRS.getString("year"));
				m.setRuntime(moviesRS.getString("runtime"));
				m.setPoster(moviesRS.getString("image_url"));
				m.setImagePath(moviesRS.getString("image_path"));

				m.setGenre(DBUtils.arrayToList(getCategories(imdb_id)));
				m.setActors(DBUtils.arrayToList(getActors(imdb_id)));
				m.setWriter(DBUtils.arrayToList(getWriters(imdb_id)));
				m.setDirector(DBUtils.arrayToList(getDirectors(imdb_id)));
				m.setDirector(DBUtils.arrayToList(getDirectors(imdb_id)));

				m.setRated(getRated(imdb_id));
				m.setLanguage(getLanguage(imdb_id));
				m.setCountry(getCountry(imdb_id));
			}

			m.setTitle(moviesRS.getString("title"));
			m.setTitle(moviesRS.getString("filepath"));

			list.add(m);
		}

		db.close();
		return list;
	}

	public static ArrayList<String> getCategories(String imdb_id) throws SQLException {
		DatabaseConnection db = Database.connection();
		QueryBuilder builder = new QueryBuilder();

		ArrayList<String> genres = new ArrayList<>();
		ArrayList<String> categoryIds = new ArrayList<>();

		String movie_id = getMovieID("imdb_id = '" + imdb_id + "'");

		ResultSet rs = db.executeQuery(builder.select("category_id").from("Categories_Matching").where("movie_id = '" + movie_id + "'").build());

		while (rs.next()) {
			categoryIds.add(rs.getString("category_id"));
		}

		for (String i : categoryIds) {
			rs = db.executeQuery(builder.select("category").from("Categories").where("id = '" + i + "'").build());
			rs.next();
			genres.add(rs.getString("category"));
		}
		db.close();
		return genres;
	}

	public static ArrayList<String> getActors(String imdb_id) throws SQLException {
		DatabaseConnection db = Database.connection();
		QueryBuilder builder = new QueryBuilder();

		ArrayList<String> actors = new ArrayList<>();
		ArrayList<String> actorsIds = new ArrayList<>();

		String movie_id = getMovieID("imdb_id = '" + imdb_id + "'");

		ResultSet rs = db.executeQuery(builder.select("actor_id").from("Actors_Matching").where("movie_id = '" + movie_id + "'").build());

		while (rs.next()) {
			actorsIds.add(rs.getString("actor_id"));
		}

		for (String i : actorsIds) {
			rs = db.executeQuery(builder.select("actor").from("Actors").where("id = '" + i + "'").build());
			rs.next();
			actors.add(rs.getString("actor"));
		}

		db.close();
		return actors;
	}

	public static ArrayList<String> getWriters(String imdb_id) throws SQLException {
		DatabaseConnection db = Database.connection();
		QueryBuilder builder = new QueryBuilder();

		ArrayList<String> writers = new ArrayList<>();
		ArrayList<String> writersIds = new ArrayList<>();

		String movie_id = getMovieID("imdb_id = '" + imdb_id + "'");

		ResultSet rs = db.executeQuery(builder.select("writer_id").from("Writers_Matching").where("movie_id = '" + movie_id + "'").build());

		while (rs.next()) {
			writersIds.add(rs.getString("writer_id"));
		}

		for (String i : writersIds) {
			rs = db.executeQuery(builder.select("writer").from("Writers").where("id = '" + i + "'").build());
			rs.next();
			writers.add(rs.getString("writer"));
		}

		db.close();
		return writers;
	}

	public static String getCountry(String imdb_id) throws SQLException {
		DatabaseConnection db = Database.connection();
		QueryBuilder builder = new QueryBuilder();

		String country;
		String countryId;

		String movie_id = getMovieID("imdb_id = '" + imdb_id + "'");

		ResultSet rs = db.executeQuery(builder.select("country_id").from("Countries_Matching").where("movie_id = '" + movie_id + "'").build());
		rs.next();
		countryId = rs.getString("country_id");

		rs = db.executeQuery(builder.select("country").from("Countries").where("id = '" + countryId + "'").build());
		rs.next();
		country = rs.getString("country");

		db.close();
		return country;
	}

	public static ArrayList<String> getDirectors(String imdb_id) throws SQLException {
		DatabaseConnection db = Database.connection();
		QueryBuilder builder = new QueryBuilder();

		ArrayList<String> directors = new ArrayList<>();
		ArrayList<String> directorsIds = new ArrayList<>();

		String movie_id = getMovieID("imdb_id = '" + imdb_id + "'");

		ResultSet rs = db.executeQuery(builder.select("director_id").from("Directors_Matching").where("movie_id = '" + movie_id + "'").build());

		while (rs.next()) {
			directorsIds.add(rs.getString("director_id"));
		}

		for (String i : directorsIds) {
			rs = db.executeQuery(builder.select("director").from("Directors").where("id = '" + i + "'").build());
			rs.next();
			directors.add(rs.getString("director"));
		}

		db.close();
		return directors;
	}

	public static String getLanguage(String imdb_id) throws SQLException {
		DatabaseConnection db = Database.connection();
		QueryBuilder builder = new QueryBuilder();

		String language;
		String languageId;

		String movie_id = getMovieID("imdb_id = '" + imdb_id + "'");

		ResultSet rs = db.executeQuery(builder.select("language_id").from("Languages_Matching").where("movie_id = '" + movie_id + "'").build());
		rs.next();
		languageId = rs.getString("language_id");

		rs = db.executeQuery(builder.select("language").from("Languages").where("id = '" + languageId + "'").build());
		rs.next();
		language = rs.getString("language");

		db.close();
		return language;
	}

	public static String getRated(String imdb_id) throws SQLException {
		DatabaseConnection db = Database.connection();
		QueryBuilder builder = new QueryBuilder();

		String rated;
		String rated_id;

		String movie_id = getMovieID("imdb_id = '" + imdb_id + "'");

		ResultSet rs = db.executeQuery(builder.select("rated_id").from("Rated_Matching").where("movie_id = '" + movie_id + "'").build());
		rs.next();
		rated_id = rs.getString("rated_id");

		rs = db.executeQuery(builder.select("rating").from("Ratings").where("id = '" + rated_id + "'").build());
		rs.next();
		rated = rs.getString("rating");

		db.close();
		return rated;
	}

	public static String getMovieID(String where) throws SQLException {
		DatabaseConnection db = Database.connection();
		QueryBuilder builder = new QueryBuilder();

		ResultSet rs = db.executeQuery(builder.select("movie_id").from("Movies").where(where).build());
		rs.next();
		db.close();

		return rs.getString("movie_id");
	}

	public static void formatDatabase() throws SQLException {
		DatabaseConnection db = Database.connection();
		db.executeUpdate("DELETE FROM Directories");
		db.executeUpdate("DELETE FROM Scraped");
		db.executeUpdate("DELETE FROM Category_Matching");
		db.executeUpdate("DELETE FROM Actors_Matching");
		db.executeUpdate("DELETE FROM Countries_Matching");
		db.executeUpdate("DELETE FROM Directors_Matching");
		db.executeUpdate("DELETE FROM Language_Matching");
		db.executeUpdate("DELETE FROM Rated_Matching");
		db.executeUpdate("DELETE FROM Writers_Matching");

		ResultSet rs = db.executeQuery(new QueryBuilder().select("image_path").from("Movies").build());

		rs.next();
		ImagesUtils.delete(rs.getString("image_path"));

		db.executeUpdate("DELETE FROM Movies");

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

//		public static String objectToList(Movie m) {
//			String s = "";
//
//			for (int i = 0; i < info.length; i++) {
//				if (info[i] == null) {
//					if (i == info.length - 1) {
//						s = s.concat("\'" + info[i] + "\'");
//					} else {
//						s = s.concat("\'" + info[i] + "\', ");
//					}
//				} else {
//					if (i == info.length - 1) {
//						s = s.concat("\'" + info[i].replaceAll("\'", "\\\'") + "\'");
//					} else {
//						s = s.concat("\'" + info[i].replaceAll("\'", "\\\'") + "\', ");
//					}
//				}
//			}
//			return s;
//		}
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
