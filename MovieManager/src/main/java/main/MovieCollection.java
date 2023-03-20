package main;

import Database.Database;
import Database.DBMethods;
import Files.DirFiles;
import Files.FilesList;
import GUI.GUIMethods;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kdesp73.madb.Condition;

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
                
                ArrayList<Object> objectList = new ArrayList<>();

                try {
                        objectList = Database.db().SELECT("Extensions", "Extension");
                } catch (SQLException ex) {
                        Logger.getLogger(MovieCollection.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                for (Object obj : objectList) {
                        this.exts.add((String)obj);
                }
                
                try {
                        this.dir = (String) Database.db().SELECT("Settings", "Directory").get(0);
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
                
                ArrayList<Object> objectList = new ArrayList<>();
                try {
                         objectList = Database.db().SELECT("Extensions", "Extension");
                } catch (SQLException ex) {
                        Logger.getLogger(MovieCollection.class.getName()).log(Level.SEVERE, null, ex);
                        return;
                } catch (Exception ex){
                    System.out.println("");
                }
                
                for (Object obj : objectList) {
                        this.exts.add((String)obj);
                }
                
                objectList.clear();
                
                movies.clear();
                Movie m = null;
                FilesList filesList = new FilesList(dir, exts);
                List<String> names = filesList.getNames();
                List<String> paths = filesList.getPaths();
                
                ArrayList<String> filepathsInDB = new ArrayList<>();
                try {
                        objectList = Database.db().SELECT("Filepaths", "Filepath");
                } catch (SQLException e1) {
                        System.out.println("Movies exists in Database");
                }
                
                for (Object obj : objectList) {
                        filepathsInDB.add((String)obj);
                }
                
                for (int i=0; i < filepathsInDB.size(); i++){
                    if(!exts.contains(DirFiles.GetExt(filepathsInDB.get(i))) || !paths.contains(filepathsInDB.get(i))){
                        try {
                            
                            String titleToDelete = (String) Database.db().SELECT("Filepaths", "Title", new Condition("Filepath", filepathsInDB.get(i)));
                            
                            Database.db().DELETE("Movies", "Title", titleToDelete);
                            Database.db().DELETE("Filepaths", "Title", titleToDelete);
                        } catch (SQLException ex) {
                            Logger.getLogger(MovieCollection.class.getName()).log(Level.SEVERE, null, ex);
                        }
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

                                Database.db().INSERT("Filepaths", new String[]{"Filepath", "Title"}, new String[]{paths.get(i), m.getTitle()});
                                Database.db().INSERT("Scraped", new String[]{"Filepath", "API_Done"}, new Object[]{movies.get(i).getDirectory(), false});

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
                                        temp[j] = MovieUtils.arrayToList(DBMethods.getCategories(Database.db().getStatement(), info[14]));
                                }
                                if (j > 5) {
                                        temp[j] = info[j - 1];
                                }
                        }

                        try {
                                temp[16] = (String) Database.db().SELECT("Filepaths", "Filepath", new Condition("Title", temp[0]));
                                System.out.println(temp[16]);
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

                if (Database.db().valueExists("Movies", "Title", title) && Database.db().valueExists("Filepaths", "Title", title)) {
                        System.out.println("Movie already exists");
                        GUIMethods.dialogError("Movie already exists");
                        return null;
                } else if (Database.db().valueExists("Movies", "Title", title)) {
                        Database.db().DELETE("Movies", "Title", title);
                }

                m = new Movie(title);
                //movies.add(m);
                try {
                        Database.db().INSERT("Movies", "Title", title);
                } catch (SQLException | NullPointerException ex) {
                        Logger.getLogger(MovieCollection.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("Movies insert error");
                        return null;
                }

                return m;
        }

        public void deleteMovie(Movie m) throws SQLException {
                String title = m.getTitle();
                String imdbid = m.getImdbID();

                Database.db().DELETE("Movies", "Title", title);

                if (!"".equals(imdbid) && imdbid != null) {
                        Database.db().DELETE("Category_Matching", "IMDb_ID", imdbid);
                }

                Database.db().DELETE("Filepaths", "Filepath", m.getDirectory());
                Database.db().DELETE("Scraped", "Filepath", m.getDirectory());

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
