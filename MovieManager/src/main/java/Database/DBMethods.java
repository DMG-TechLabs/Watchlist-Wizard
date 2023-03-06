package Database;

import java.sql.*;
import java.util.ArrayList;
import main.Movie;

public class DBMethods {

        public static Statement s = Database.db().getStatement();
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

        public static int INSERT(Statement s, Movie m) throws SQLException { //Inserts movie to Database 
                //Check if movie already exists
                if (Database.db().valueExists("Movies", "IMDb_ID", m.getImdbID())) {
                        System.out.println("Movie already exists");
                        return -1;
                }
                String query = "INSERT INTO Movies(" + DBUtils.columnsToList(DBFields) + ") VALUES(" + DBUtils.objectToList(m) + ")";
                //String query = "INSERT INTO Movies(" + DBUtils.arrayToList(DBFields) + ") VALUES(" + "\'"+m.getTitle()+ "\', \'"+m.getYear()+ "\', \'"+m.getRated()+ "\', \'"+m.getReleased()+ "\', \'"+m.getRuntime()+ "\', \'"+m.getDirector()+ "\', \'"+m.getWriter()+ "\', \'"+m.getActors()+ "\', \'"+m.getPlot()+ "\', \'"+m.getLanguage()+ "\', \'"+m.getCountry()+ "\', \'"+m.getAwards()+ "\', \'"+m.getPoster()+ "\', \'"+m.getType()+ "\', \'"+m.getImdbRating()+ "\', \'"+m.getImdbID() + "\')";

                System.out.println(query);
                s.executeUpdate(query);
                System.out.println("Movie Added");
                return 0;
        }

        //Adds genres to DB and returns their Category_ID's
        private static int[] matchCategoryID(Movie m) throws SQLException {
                String[] genre = DBUtils.genreToArray(m);

                int[] ids = new int[genre.length];

                ResultSet rs;

                for (int i = 0; i < genre.length; i++) {
                        //Check if genre exists. If not add it and continue
                        if (!Database.db().valueExists("Categories", "Category", genre[i])) {
                                System.out.println("Genre doesn't exist");
                                System.out.println("Adding genre...");

                                Database.db().INSERT("Categories", "Category", genre[i]);

                                System.out.println("Genre added.");

                        }

                        String query = "SELECT Category_ID FROM Categories WHERE Category=\'" + genre[i] + "\'";
                        //System.out.println(query);

                        rs = s.executeQuery(query);

                        while (rs.next()) {
                                ids[i] = rs.getInt(1);
                        }
                }

                return ids;
        }

        private static void matchCategories(Movie m) throws SQLException { //Adds Category id's and imdb id in the in beteween table
                int[] ids = matchCategoryID(m);

                for (int i = 0; i < ids.length; i++) {
                        s.executeUpdate("INSERT INTO Category_Matching(Category_ID, IMDb_ID) VALUES(" + ids[i] + ", \'" + m.getImdbID() + "\')");
                }
        }

        private static String matchCategories(int categoryId) throws SQLException { //Adds Category id's and imdb id in the in beteween table
                String query = "SELECT Category FROM Categories WHERE Category_ID = " + categoryId;
                ResultSet rs = s.executeQuery(query);

                rs.next();

                return rs.getString(1);
        }

        public static int insertMovie(Movie m) throws SQLException {
                int status = INSERT(s, m); //Inserts movie to "Movies" table
                if (status == 0) {
                        matchCategories(m); //Inserts Categories
                        return status;
                }
                return status;
        }

        public static ArrayList<String[]> getMovies() throws SQLException {
                ArrayList<String[]> list = new ArrayList<>();

                String query = "SELECT " + DBUtils.columnsToList(DBFields) + " FROM Movies";
                ResultSet rs = Database.db().getStatement().executeQuery(query);

                while (rs.next()) {
                        String[] str = new String[DBFields.length];

                        for (int i = 0; i < DBFields.length; i++) {
                                str[i] = rs.getString(i + 1);
                        }

                        list.add(str);
                }

                return list;
        }

        public static ArrayList<String> getCategories(Statement s, String id) throws SQLException {
                ArrayList<String> genres = new ArrayList<>();
                ArrayList<Integer> categoryIDs = new ArrayList<>();

                String query = "SELECT Category_ID FROM Category_Matching WHERE IMDb_ID = \'" + id + "\'";
                ResultSet rs = s.executeQuery(query);

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
                Database.db().DELETE("Movies");
                Database.db().DELETE("Filepaths");
                Database.db().DELETE("Scraped");
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
                        //s = m.getGenre();
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
