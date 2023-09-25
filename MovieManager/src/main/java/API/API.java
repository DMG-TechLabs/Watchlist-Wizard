package API;

import java.io.IOException;
import java.net.http.*;
import java.sql.SQLException;
import Exceptions.InvalidKeyException;
import Exceptions.MovieNotFoundException;
import Files.ImagesUtils;
import GUI.GUIMethods;
import Utils.Logs;
import Utils.Utils;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.regex.Pattern;

import Database.DBMethods;
import Database.Database;
import main.Movie;
import main.MovieCollection;
import kdesp73.databridge.*;
import kdesp73.databridge.helpers.QueryBuilder;


public class API {
        public static Logs logger = new Logs("API");

        private Dictionary<String, Movie> movies_info = new Hashtable<String, Movie>();
        private String api_key = "";
        private HttpResponse<String> response;

        /**
         * Api constractor. If it is unable to load the api key throws an error
         * @throws MalformedURLException
         * @throws IOException
         */
        public API() throws MalformedURLException, IOException, UnknownHostException {
                this.api_key = ApiUtils.getKey("https://users.iee.ihu.gr/~iee2021035/api_key.txt");
        }

        public API(String api_key) {
                this.api_key = api_key;
        }

        public String search(String title) throws MalformedURLException, IOException, InterruptedException {
                String title_to_get = title;
                title_to_get = ApiUtils.prepare_string(title_to_get);
                System.out.println("Search Title: " + title_to_get);

                String url = "https://api.themoviedb.org/3/search/multi?api_key=" + this.api_key + "&language=en-US&query=" + setupString(title_to_get) + "&include_adult=false";
                response = ApiUtils.http_get(url);

                System.out.println("Response Status: " + response.statusCode());
                if (response.statusCode() == 401) throw new InvalidKeyException("Invalid key");
                // System.out.println(url);
                // System.out.println(response.body());
                return response.body();
        }

        public ArrayList<HashMap<String, String>> getSearchResults(String title){
                ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

                try {
                        String s = search(title);
                        for(String sp:s.split(Pattern.quote("},{"))) list.add(HashMap.class.cast(Utils.JsonToDictionary(sp)));

                } catch (IOException | InterruptedException e) {
                        // TODO Auto-generated catch block
                        logger.logging("warning", "WARNING: "+e.toString());
                }
                return list;
        }


        //Methods
        public String GET(String title) throws IOException, InterruptedException, SQLException, ConnectException, NullPointerException, InvalidKeyException {
                logger.logging("info", "Getting info for: "+title);
                Dictionary<String, String> info = ApiUtils.infoToMap(title, this.api_key, search(title));

                return "{"
                        + "    \"Title\":\"" +      info.get("Title")
                        + "\" ,\"Year\":\"" +       info.get("Year")
                        + "\" ,\"Rated\":\"" +      info.get("Rated")
                        + "\" ,\"Released\":\"" +   info.get("Released")
                        + "\" ,\"Runtime\":\"" +    info.get("Runtime")
                        + "\" ,\"Genre\":\"" +      info.get("Genre")
                        + "\" ,\"Director\":\"" +   info.get("Director")
                        + "\" ,\"Writer\":\"" +     info.get("Writer")
                        + "\" ,\"Actors\":\"" +     info.get("Actors")
                        + "\" ,\"Plot\":\"" +       info.get("Plot")
                        + "\" ,\"Language\":\"" +   info.get("Language")
                        + "\" ,\"Country\":\"" +    info.get("Country")
                        + "\" ,\"Awards\":\"" +     info.get("Awards")
                        + "\" ,\"Poster\":\"" +     info.get("Poster")
                        + "\" ,\"Type\":\"" +       info.get("Type")
                        + "\" ,\"imdbRating\":\"" + info.get("imdbRating")
                        + "\" ,\"imdbID\":\"" +     info.get("imdbID")
                        + "\" }";
        }


        public void saveToDatabase() throws SQLException{
                //System.out.println(json);
                for (String title : Collections.list(movies_info.keys())) {
                        Movie movie = movies_info.get(title);

						QueryBuilder builder = new QueryBuilder();

						String updateQuery = builder.update("Scraped")
							.set("API_Done", true)
							.where("Filepath = " + movie.getDirectory())
							.build();


						Database.connection().executeUpdate(updateQuery);



						String deleteQuery = builder.deleteFrom("Movies")
							.where("Title = '" + title + "'")
							.build();


						Database.connection().executeUpdate(deleteQuery);


						DBMethods.insertMovie(movie);
                        String imdb_id = movie.getImdbID();
                        if(imdb_id != null) ImagesUtils.imageToDatabase(imdb_id);
                }
        }

        public void scrapeAndSave(MovieCollection m) throws SQLException, IOException, InterruptedException {
                scrape(m);
                saveToDatabase();
        }

        public ArrayList<Movie> scrape(MovieCollection m) throws IOException, InterruptedException, SQLException {
                ArrayList<Movie> movies = m.getMovies();

                for (int i = 0; i < movies.size(); i++) {
                        //System.out.println(movies.get(i).toString());
                        //Movie parsed = Utils.parseJSON(GET(movies.get(i).getTitle()));
                        if (movies.get(i).getImdbID() == null) {
                                String title = movies.get(i).getTitle();
                                System.out.println("Titile: " + title);
                                String json = "";
                                try {
                                        json = GET(title);

                                        Movie parsed = Utils.parseMovieJSON(json);
                                        movies.set(i, parsed);
                                        movies_info.put(title, parsed);
                                } catch (MovieNotFoundException | IndexOutOfBoundsException | NullPointerException e) {
                                        continue;
                                } catch (InvalidKeyException e) {
                                        System.err.println(e.toString());
                                        GUIMethods.dialogError("There is an invalid key in the database ");
                                        return m.getMovies();
                                } catch (ConnectException e) {
                                        System.err.println("No Internet Connection");
                                        return m.getMovies();
                                }
                        }
                }
                System.out.println("movies list:"+movies);
                saveToDatabase();
                return movies;
        }

        private String setupString(String s) {
                s = s.replaceAll(Pattern.quote("."), " ");
                s = s.replaceAll(" ", "%20");
                return s;
        }
}
