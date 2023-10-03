package main;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Database.DBMethods;
import Database.Database;
import Files.DirFiles;
import Files.FilesList;
import Files.ImagesUtils;
import GUI.GUIMethods;
import kdesp73.databridge.connections.DatabaseConnection;
import kdesp73.databridge.helpers.QueryBuilder;
import kdesp73.databridge.helpers.ResultRow;
import net.ucanaccess.jdbc.UcanaccessSQLException;

public class MovieCollection {

    private ArrayList<String> exts = new ArrayList<>();
    //        private String[] exts = {
    //                "3g2",
    //                "3gp",
    //                "aaf",
    //                "asf",
    //                "avchd",
    //                "avi",
    //                "drc",
    //                "flv",
    //                "m2v",
    //                "m3u8",
    //                "m4p",
    //                "m4v",
    //                "mkv",
    //                "mng",
    //                "mov",
    //                "mp2",
    //                "mp4",
    //                "mpe",
    //                "mpeg",
    //                "mpg",
    //                "mpv",
    //                "mxf",
    //                "nsv",
    //                "ogg",
    //                "ogv",
    //                "qt",
    //                "rm",
    //                "rmvb",
    //                "roq",
    //                "svi",
    //                "vob",
    //                "webm",
    //                "wmv",
    //                "yuv"
    //        };

    private ArrayList<String> dir;
    private ArrayList<Movie> movies = new ArrayList<>();
    //        private FilesList filesList;

    public MovieCollection() {
		DatabaseConnection db = Database.connection();

		ResultSet rs = db.executeQuery(new QueryBuilder().select("Extension").from("Extensions").build());

		try {
			while(rs.next())
				this.exts.add(rs.getString("Extension"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



        try {
			rs = db.executeQuery(new QueryBuilder().select("Directory").from("Settings").build());
            while (rs.next()) {this.dir.add(rs.getString("Extension"));}
			// rs.next();
			// this.dir = rs.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(MovieCollection.class.getName()).log(Level.SEVERE, null, ex);
        }
		db.close();
	}

    public void print() {
        for (int i = 0; i < movies.size(); i++) {
            System.out.println("movies[" + i + "]");
            System.out.println(movies.get(i).toString());
            System.out.println("");
        }
    }

    public void refreshMovies() {
        DatabaseConnection db = Database.connection();

		exts.clear();
		ResultSet rs = db
				.executeQuery(new QueryBuilder().select("Extension").from("Extensions").build());
		try {
			while (rs.next()) {
				this.exts.add(rs.getString("Extension"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        movies.clear();

        if(dir != null && !dir.isEmpty()){
            for (String c_dir : dir) {
                movies = refreshMoviesInDir(c_dir, this.exts, movies, db);
            }
        }

		db.close();
    }

    private ArrayList<Movie> refreshMoviesInDir(String dir, ArrayList<String> exts, ArrayList<Movie> movies, DatabaseConnection db){
        ResultSet rs;
        ResultSet rs2;
        Movie m = null;
        FilesList filesList = new FilesList(dir, exts);
        // FindMultipleFiles filesList = new FindMultipleFiles(dir, exts);
        List<String> paths = new ArrayList<String>();
        List<String> names = new ArrayList<String>();
        try {
            paths = filesList.getPaths();
            names = filesList.getNames();
            if(paths.size() != names.size()) throw new Exception("Different number of names and paths");
        //TODO when add my lib make it IOexeption
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Can't find or open this directory!");
        }

        ArrayList<String> filepathsInDB = new ArrayList<String>();
		rs = db.executeQuery(new QueryBuilder().select("Filepath").from("Filepaths").build());

        String path = "";
        try {
			while (rs.next()) {
			    filepathsInDB.add(rs.getString("Filepath"));
                path = rs.getString("Filepath");
                if(!exts.contains(DirFiles.GetExt(path)) || !paths.contains(path)){
                    String titleToDelete = "";
                    rs2 = db.executeQuery(new QueryBuilder().select("Title").from("Filepaths")
                            .where("Filepath = '" + path + "'").build());
                    try {
                        rs2.next();
                        titleToDelete = rs2.getString(1);

                        db.executeUpdate(
                            new QueryBuilder().deleteFrom("Movies").where("Title = '" + titleToDelete + "'").build());
                        db.executeUpdate(new QueryBuilder().deleteFrom("Filepaths")
                            .where("Title = '" + titleToDelete + "'").build());

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        String c_path = "";
        String c_name = "";
        for (int i = 0; i < names.size(); i++) {
            c_path = "";
            c_name = "";
            if (!filepathsInDB.contains(paths.get(i))) {
                try {
                    c_path = paths.get(i);
                    c_name = names.get(i);
                    m = insertMovie(c_name);
                    m.setDirectory(c_path);
                    m.setFilename(c_name);
                    movies.add(m);

                    db.executeUpdate(new QueryBuilder().insertInto("Filepaths").columns("Filepath", "Title").values(c_path, m.getTitle()).build());
                    db.executeUpdate(new QueryBuilder().insertInto("Scraped").columns("Filepath", "API_Done").values(c_path, false).build());

                } catch (IOException | InterruptedException | SQLException | NullPointerException e) { //Make proper exceptions
                    System.out.println("Movie is null");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Movies.get(i) IndexOutOfBoundsException");
                }
            }
        }

        return movies;
    }

    public void load() throws SQLException {
        DatabaseConnection db = Database.connection();
		movies.clear();

        ArrayList<String[]> infoList = DBMethods.getMovies();

        for (int i = 0; i < infoList.size(); i++) {
            Movie m = new Movie();

            String[] info = infoList.get(i);

            String[] temp = new String[DBMethods.getDBFields().length + 3];
            for (int j = 0; j < temp.length - 2; j++) {
                if (j < 5) {
                    temp[j] = info[j];
                }
                if (j == 5) {
                    temp[j] = MovieUtils.arrayToList(DBMethods.getCategories(info[14]));
                }
                if (j > 5) {
                    temp[j] = info[j - 1];
                }
            }

            try {
				ResultSet rs = db.executeQuery(new QueryBuilder().select("Filepath").from("Filepaths").where("Title = '" + temp[0] + "'").build());
				rs.next();
				temp[16] = rs.getString(1);
//                System.out.println(temp[16]);
                temp[17] = Files.DirFiles.GetName(temp[16]);
            } catch (IndexOutOfBoundsException | SQLException e) {
                temp[16] = "";
                temp[17] = "";
            }
            m.addInfo(temp);
            movies.add(m);
        }

		db.close();
    }

    public Movie insertMovie(String title) throws IOException, InterruptedException, SQLException {
        DatabaseConnection db = Database.connection();
		Movie m;

		boolean movieTitleExists = false, moviePathExists = false;

		ResultSet rs = db.executeQuery("SELECT Title FROM Movies WHERE Title = '" + title + "'");
		movieTitleExists = (rs.next());

		rs = db.executeQuery("SELECT Title FROM Filepaths WHERE Title = '" + title + "'");
		moviePathExists = (rs.next());

        if (movieTitleExists && moviePathExists) {
            System.out.println("Movie already exists");
            GUIMethods.dialogError("Movie already exists");
            return null;
        } else if (movieTitleExists) {
			db.executeUpdate(new QueryBuilder().deleteFrom("Movies").where("Title = '" + title + "'").build());
        }

        m = new Movie(title);
		db.executeUpdate(new QueryBuilder().insertInto("Movies").columns("Title").values(title).build());
		db.close();

        return m;
    }

    public void deleteMovie(Movie m) throws SQLException {
        String title = m.getTitle();
        String imdbid = m.getImdbID();

		DatabaseConnection db = Database.connection();
		ResultSet rs = null;

        if (!"".equals(imdbid) && imdbid != null) {
			rs = db.executeQuery(new QueryBuilder().select("Image_Directory").from("Images").where("IMDb_ID = '" + imdbid + "'").build());
			rs.next();
			ImagesUtils.delete(rs.getString(1)); //Delete image from directory

			db.executeUpdate(new QueryBuilder().deleteFrom("Category_Matching").where("IMDb_ID = '" + imdbid + "'").build());
			db.executeUpdate(new QueryBuilder().deleteFrom("Images").where("IMDb_ID = '" + imdbid + "'").build());
        }

		db.executeUpdate(new QueryBuilder().deleteFrom("Movies").where("Title = '" + title + "'").build());
		db.executeUpdate(new QueryBuilder().deleteFrom("Filepaths").where("Filepath = '" + m.getDirectory() + "'").build());
		db.executeUpdate(new QueryBuilder().deleteFrom("Scraped").where("Filepath = '" + m.getDirectory() + "'").build());

        movies.remove(m);

		db.close();
        System.out.println("Movie removed completely");
    }

    public void addExtension(String ext) {
        exts.add(ext);
        //DirFiles.setExtList(exts);
    }

    public void removeExtension(String ext) {
        exts.remove(ext);
        //DirFiles.setExtList(exts);
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public ArrayList<String> getDir() {
        return dir;
    }

    public void setDir(ArrayList<String> dir) {
        this.dir = dir;
    }

    public ArrayList<String> getExts() {
        return exts;
    }

    public void setExts(ArrayList<String> exts) {
        this.exts = exts;
    }

    @Override
    public String toString() {
        return """
            Movies{
                """ + movies + "\n" + '}';
            }

        private class MovieUtils{
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
        }
    }
