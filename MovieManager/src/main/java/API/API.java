package API;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.sql.SQLException;
import Database.DBMethods;
import Database.Database;
import Exceptions.MovieNotFoundException;
import Files.ImagesUtils;
import GUI.GUIMethods;
import Utils.Utils;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.regex.Pattern;
import kdesp73.madb.Condition;
import main.Movie;
import main.MovieCollection;

public class API {

        private String title;
        HttpRequest request;
        HttpResponse<String> response;

        public API() throws IOException, InterruptedException, SQLException {
        }

        public void setTitle(String title) {
                this.title = setupString(title);
        }

        public String getTitle() {
                return title;
        }

        //Methods
        private String GET(String title) throws IOException, InterruptedException, SQLException {
                setTitle(title);
                this.title = ApiUtils.prepare_string(title);
                System.out.println("Title: " + this.title);
                //String api_key = FileUtils.read("api_key.txt", System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") + "/data/");
                String api_key = ApiUtils.getKey("https://users.iee.ihu.gr/~iee2021035/api_key.txt");

                try {
                        request = HttpRequest.newBuilder()
                                .uri(URI.create("https://api.themoviedb.org/3/search/multi?api_key=" + api_key + "&language=en-US&query=" + getTitle().replaceAll(" ", "%20") + "&include_adult=false"))
                                .method("GET", HttpRequest.BodyPublishers.noBody())
                                .build();

                        response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                } catch (ConnectException e) {
                        String error = "{\"message\":\"No Internet Connection\"}";
                        return error;
                }

                System.out.println("Response Status: " + response.statusCode());

                if (response.statusCode() == 401) {
                        System.out.println("Invalid key");
                        GUIMethods.dialogError("There is an invalid key in the database ");
                }

                try {
                        Dictionary<String, String> info = ApiUtils.infoToMap(title, api_key, response.body()); 
                
                        
                
                
                
                
                return "{"
                        + " \"Title\":\""     +     info.get("Title")
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
                        + "\"}";

                } catch (ConnectException e) {
                        String error = "{\"message\":\"No Internet Connection\"}";
                        return error;
                } catch (NullPointerException e){return "";}
        }

        public void scrape(MovieCollection m) throws IOException, InterruptedException, SQLException {
                ArrayList<Movie> movies = m.getMovies();
                for (int i = 0; i < movies.size(); i++) {
                        //System.out.println(movies.get(i).toString());

                        //Movie parsed = Utils.parseJSON(GET(movies.get(i).getTitle()));
                        if (movies.get(i).getImdbID() == null || movies.get(i).getImdbID() == "") {
                                String old_title = movies.get(i).getTitle();
                                System.out.println("OLD titile: " + old_title);
                                String json = "";
                                try {
                                        json = GET(old_title);
                                } catch (MovieNotFoundException | IndexOutOfBoundsException e) {
                                        continue;
                                }

                                //System.out.println(json);
                                Movie parsed = Utils.parseMovieJSON(json);
                                movies.set(i, parsed);
                                Database.db().UPDATE("Scraped", "API_Done", true, new Condition("Filepath", movies.get(i).getDirectory()));
                                Database.db().DELETE("Movies", "Title", old_title);
                                DBMethods.insertMovie(movies.get(i));
                                String imdb_id = movies.get(i).getImdbID();
                                if(imdb_id != null) ImagesUtils.imageToDatabase(imdb_id);
                        }

                }
        }

        private String setupString(String s) {
                s = s.replaceAll(Pattern.quote("."), " ");
                s = s.replaceAll(" ", "%20");
                return s;
        }
}
