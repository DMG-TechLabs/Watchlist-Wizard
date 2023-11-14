package API;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

import kdesp73.databridge.helpers.QueryBuilder;

public class API {
        private static Logger logger = Logs.createLoger("API");

        private Dictionary<String, Movie> movies_info = new Hashtable<String, Movie>();
        private String api_key = "";
        private HttpResponse<String> response;

        /**
         * Api constractor. If it is unable to load the api key throws an error
         * 
         * @throws MalformedURLException
         * @throws IOException
         */
        public API() throws MalformedURLException, IOException, UnknownHostException {
                this.api_key = ApiUtils.getKey("https://users.iee.ihu.gr/~iee2021035/api_key.txt");
                logger.log(Level.INFO, "Api created");
        }

        public API(String api_key) {
                this.api_key = api_key;
                logger.log(Level.INFO, "Api created");
        }

        public String search(String title) throws MalformedURLException, IOException, InterruptedException {
                String title_to_get = title;
                title_to_get = ApiUtils.prepare_string(title_to_get);
                logger.log(Level.INFO, "Search Title: " + title_to_get);

                String url = "https://api.themoviedb.org/3/search/multi?api_key=" + this.api_key
                                + "&language=en-US&query=" + setupString(title_to_get) + "&include_adult=false";
                response = ApiUtils.http_get(url);

                if (response.statusCode() == 401)
                        throw new InvalidKeyException("Invalid key");
                return response.body();
        }

        public ArrayList<HashMap<String, String>> getSearchResults(String title) {
                ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

                try {
                        String s = search(title);
                        for (String sp : s.split(Pattern.quote("},{")))
                                list.add(Utils.JsonToHashMap(sp));

                } catch (IOException | InterruptedException e) {
                        // TODO Auto-generated catch block
                        logger.log(Level.WARNING, e.toString());
                }
                return list;
        }

        // Methods
        public String GET(String title) throws IOException, InterruptedException, SQLException, ConnectException,
                        NullPointerException, InvalidKeyException {
                logger.log(Level.INFO, "Getting info for: " + title);
                Dictionary<String, String> info = ApiUtils.infoToMap(title, this.api_key, search(title));

                return "{"
                                + "    \"Title\":\"" + info.get("Title")
                                + "\" ,\"Year\":\"" + info.get("Year")
                                + "\" ,\"Rated\":\"" + info.get("Rated")
                                + "\" ,\"Released\":\"" + info.get("Released")
                                + "\" ,\"Runtime\":\"" + info.get("Runtime")
                                + "\" ,\"Genre\":\"" + info.get("Genre")
                                + "\" ,\"Director\":\"" + info.get("Director")
                                + "\" ,\"Writer\":\"" + info.get("Writer")
                                + "\" ,\"Actors\":\"" + info.get("Actors")
                                + "\" ,\"Plot\":\"" + info.get("Plot")
                                + "\" ,\"Language\":\"" + info.get("Language")
                                + "\" ,\"Country\":\"" + info.get("Country")
                                + "\" ,\"Awards\":\"" + info.get("Awards")
                                + "\" ,\"Poster\":\"" + info.get("Poster")
                                + "\" ,\"Type\":\"" + info.get("Type")
                                + "\" ,\"imdbRating\":\"" + info.get("imdbRating")
                                + "\" ,\"imdbID\":\"" + info.get("imdbID")
                                + "\" }";
        }

        public void saveToDatabase() throws SQLException {
                // System.out.println(json);
                logger.log(Level.INFO, "Saving info to database");
                for (String title : Collections.list(movies_info.keys())) {
                        logger.log(Level.FINE, "Saving: "+title);
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
                        if (imdb_id != null)
                                ImagesUtils.imageToDatabase(imdb_id);
                }
        }

        public void scrapeAndSave(MovieCollection m) throws SQLException, IOException, InterruptedException {
                scrape(m);
                saveToDatabase();
        }

        public ArrayList<Movie> scrape(MovieCollection m) throws IOException, InterruptedException, SQLException {
                logger.log(Level.INFO, "Scarpping movies");
                ArrayList<Movie> movies = m.getMovies();

                for (int i = 0; i < movies.size(); i++) {
                        // System.out.println(movies.get(i).toString());
                        // Movie parsed = Utils.parseJSON(GET(movies.get(i).getTitle()));
                        if (movies.get(i).getImdbID() == null) {
                                String title = movies.get(i).getTitle();
                                String json = "";
                                try {
                                        json = GET(title);

                                        Movie parsed = Utils.parseMovieJSON(json);
                                        movies.set(i, parsed);
                                        movies_info.put(title, parsed);
                                } catch (MovieNotFoundException | IndexOutOfBoundsException | NullPointerException e) {
                                        continue;
                                } catch (InvalidKeyException e) {
                                        logger.log(Level.WARNING, "There is an invalid key in the database");
                                        logger.log(Level.FINER, e.toString());
                                        GUIMethods.dialogError("There is an invalid key in the database ");
                                        return m.getMovies();
                                } catch (ConnectException e) {
                                        logger.log(Level.WARNING, "No Internet Connection");
                                        logger.log(Level.FINER, e.toString());
                                        return m.getMovies();
                                }
                        }
                }
                logger.log(Level.FINER, "Movies list:" + movies);
                saveToDatabase();
                return movies;
        }

        private String setupString(String s) {
                s = s.replaceAll(Pattern.quote("."), " ");
                s = s.replaceAll(" ", "%20");
                return s;
        }