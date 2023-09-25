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
import kdesp73.databridge.helpers.QueryBuilder;
import kdesp73.databridge.helpers.ResultProcessor;
import kdesp73.databridge.helpers.ResultRow;

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

    private String dir;
    private ArrayList<Movie> movies = new ArrayList<>();
    //        private FilesList filesList;

    public MovieCollection() {
        //                filesList = new FilesList(dir, exts);

		ResultProcessor rp = new ResultProcessor();
		ResultSet rs = Database.connection()
				.executeQuery(new QueryBuilder().select("Extension").from("Extensions").build());

		List<ResultRow> table = rp.toList(rs);

		for (ResultRow row : table) {
			this.exts.add(row.get("Extension"));
		}


        try {
			rs = Database.connection().executeQuery(new QueryBuilder().select("Directory").from("Settings").build());
			rs.next();
			this.dir = rs.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(MovieCollection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void print() {
        for (int i = 0; i < movies.size(); i++) {
            System.out.println("movies[" + i + "]");
            System.out.println(movies.get(i).toString());
            System.out.println("");
        }
    }

    public void refreshMovies() {
        exts.clear();
		ResultProcessor rp = new ResultProcessor();
		ResultSet rs = Database.connection()
				.executeQuery(new QueryBuilder().select("Extension").from("Extensions").build());

		List<ResultRow> table = rp.toList(rs);

		for (ResultRow row : table) {
			this.exts.add(row.get("Extension"));
		}


        movies.clear();
        Movie m = null;
        FilesList filesList = new FilesList(dir, exts);
        // FindMultipleFiles filesList = new FindMultipleFiles(dir, exts);
        List<String> paths = new ArrayList<String>();
        List<String> names = new ArrayList<String>();
        try {
            paths = filesList.getPaths();
            names = filesList.getNames();
        //TODO when add my lib make it IOexeption
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Can't find or open this directory!");
        }
        // List<String> names = filesList.getNames();
        // List<String> paths = filesList.getPaths();

        ArrayList<String> filepathsInDB = new ArrayList<>();
		rs = Database.connection().executeQuery(new QueryBuilder().select("Filepath").from("Filepaths").build());
		try {
			rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		table = rp.toList(rs);

        for (ResultRow row : table) {
            filepathsInDB.add(row.get("Filepath"));
        }

        for (int i=0; i < filepathsInDB.size(); i++){
            if(!exts.contains(DirFiles.GetExt(filepathsInDB.get(i))) || !paths.contains(filepathsInDB.get(i))){
				String titleToDelete = "";
				rs = Database.connection().executeQuery(new QueryBuilder().select("Title").from("Filepaths")
						.where("Filepath = '" + filepathsInDB.get(i) + "'").build());
				try {
					rs.next();
					// TODO Auto-generated catch block
					titleToDelete = rs.getString(1);
				} catch (SQLException e) {
					e.printStackTrace();
				}

				Database.connection().executeUpdate(
						new QueryBuilder().deleteFrom("Movies").where("Title = '" + titleToDelete + "'").build());
				Database.connection().executeUpdate(new QueryBuilder().deleteFrom("Filepaths")
						.where("Title = '" + titleToDelete + "'").build());

				}
        }

        for (int i = 0; i < names.size(); i++) {
            if (filepathsInDB.contains(paths.get(i))) {
                continue;
            }
            try {
                m = insertMovie(names.get(i));
                m.setDirectory(paths.get(i));
                m.setFilename(names.get(i));
                movies.add(m);

				Database.connection().executeUpdate(new QueryBuilder().insertInto("Filepaths").columns("Filepath", "Title").values(paths.get(i), m.getTitle()).build());
				Database.connection().executeUpdate(new QueryBuilder().insertInto("Scraped").columns("Filepath", "API_Done").values(movies.get(i).getDirectory(), false).build());

            } catch (IOException | InterruptedException | SQLException | NullPointerException e) { //Make proper exceptions
                System.out.println("Movie is null");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Movies.get(i) IndexOutOfBoundsException");
            }
        }


    }

    public void load() throws SQLException {
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
				ResultSet rs = Database.connection().executeQuery(new QueryBuilder().select("Filepath").from("Filepaths").where("Title = '" + temp[0] + "'").build());
				rs.next();
				temp[16] = rs.getString(1);
//                System.out.println(temp[16]);
                temp[17] = Files.DirFiles.GetName(temp[16]);
            } catch (IndexOutOfBoundsException e) {
                temp[16] = "";
                temp[17] = "";
            }
            m.addInfo(temp);
            movies.add(m);
        }

    }

    public Movie insertMovie(String title) throws IOException, InterruptedException, SQLException {
        Movie m;

		boolean movieTitleExists = false, moviePathExists = false;

		ResultSet rs = Database.connection().executeQuery("SELECT Title FROM Movies WHERE Title = '" + title + "'");
		movieTitleExists = (rs.next());

		rs = Database.connection().executeQuery("SELECT Title FROM Filepaths WHERE Title = '" + title + "'");
		moviePathExists = (rs.next());

        if (movieTitleExists && moviePathExists) {
            System.out.println("Movie already exists");
            GUIMethods.dialogError("Movie already exists");
            return null;
        } else if (movieTitleExists) {
			Database.connection().executeUpdate(new QueryBuilder().deleteFrom("Movies").where("Title = '" + title + "'").build());
        }

        m = new Movie(title);
		Database.connection().executeUpdate(new QueryBuilder().insertInto("Movies").columns("Title").values(title).build());

        return m;
    }

    public void deleteMovie(Movie m) throws SQLException {
        String title = m.getTitle();
        String imdbid = m.getImdbID();

		ResultSet rs = null;

        if (!"".equals(imdbid) && imdbid != null) {
			rs = Database.connection().executeQuery(new QueryBuilder().select("Image_Directory").from("Images").where("IMDb_ID = '" + imdbid + "'").build());
			rs.next();
			ImagesUtils.delete(rs.getString(1)); //Delete image from directory

			Database.connection().executeUpdate(new QueryBuilder().deleteFrom("Category_Matching").where("IMDb_ID = '" + imdbid + "'").build());
			Database.connection().executeUpdate(new QueryBuilder().deleteFrom("Images").where("IMDb_ID = '" + imdbid + "'").build());
        }

		Database.connection().executeUpdate(new QueryBuilder().deleteFrom("Movies").where("Title = '" + title + "'").build());
		Database.connection().executeUpdate(new QueryBuilder().deleteFrom("Filepaths").where("Filepath = '" + m.getDirectory() + "'").build());
		Database.connection().executeUpdate(new QueryBuilder().deleteFrom("Scraped").where("Filepath = '" + m.getDirectory() + "'").build());

        movies.remove(m);

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

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
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
