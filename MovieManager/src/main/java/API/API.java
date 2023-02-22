package API;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.sql.SQLException;
import Database.DBMethods;
import Database.Database;
import Exceptions.MovieNotFoundException;
import GUI.GUIMethods;
import Utils.Utils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.regex.Pattern;
import kdesp73.madb.Condition;
//mport org.json.simple.JSONValue;
//import org.json.*;

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
        public String GET(String title) throws IOException, InterruptedException, SQLException {
                setTitle(title);
                System.out.println("Title: " + title);
                String api_key = FileUtils.read("api_key.txt", System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") + "/data/");
                String FinalJson;
                try {
                        request = HttpRequest.newBuilder()
                                .uri(URI.create("https://api.themoviedb.org/3/search/multi?api_key=" + api_key + "&language=en-US&query=" + getTitle() + "&include_adult=false"))
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

                Dictionary table = Utils.JsonToDictionary(response.body());
                try {
                        String total_results = table.get("total_results").toString();
                        if (total_results.equals("0")) {
                                throw new MovieNotFoundException("Api was unable to find info for the video:" + title);
                        }
                } catch (NullPointerException e) {
                }
                System.out.println("API multi search table: " + table);
                String media_type = "";
                try {
                        media_type = table.get("media_type").toString();
                } catch (NullPointerException e) {
                        return "";
                }
                String genre = table.get("genre_ids").toString();
                System.out.println("\nMedia Type & Genre: " + media_type + "\n" + genre);
                String genre_names = "";
                String current_gen;
                for (String gen : genre.split(",")) {
                        System.out.println("Genre_id: " + gen);
                        current_gen = (String) Database.db().SELECT("Categories", "Category", new Condition("TMDB_id", gen));
                        genre_names = genre_names + current_gen + ",";
                }
                try {
                        String url = "https://api.themoviedb.org/3/movie/" + table.get("id") + "?api_key=" + api_key + "&language=en-US";
                        //if (media_type == "movie"){url = "https://api.themoviedb.org/3/movie/"+table.get("id")+"?api_key="+api_key+"&language=en-US";}
                        //else{url = "https://api.themoviedb.org/3/tv/"+table.get("id")+"?api_key="+api_key+"&language=en-US";}
                        request = HttpRequest.newBuilder()
                                .uri(URI.create(url))
                                .method("GET", HttpRequest.BodyPublishers.noBody())
                                .build();

                        response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                } catch (ConnectException e) {
                        String error = "{\"message\":\"No Internet Connection\"}";
                        return error;
                }

                table = Utils.JsonToDictionary(response.body());

                FinalJson = "{"
                        + " \"Title\":\"" + table.get("title") //
                        + "\" ,\"Year\":\"" + table.get("release_date").toString().split(Pattern.quote("-"))[0] //
                        + "\" ,\"Rated\":\"" + table.get("id")
                        + "\" ,\"Released\":\"" + table.get("release_date")//
                        + "\" ,\"Runtime\":\"" + table.get("runtime")//
                        + "\" ,\"Genre\":\"" + genre_names//
                        + "\" ,\"Director\":\"" + table.get("id")
                        + "\" ,\"Writer\":\"" + table.get("id")
                        + "\" ,\"Actors\":\"" + table.get("id")
                        + "\" ,\"Plot\":\"" + table.get("overview")//
                        + "\" ,\"Language\":\"" + table.get("original_language")//
                        + "\" ,\"Country\":\"" + table.get("iso_3166_1")//(table.get("origin_country").toString()).replace(Pattern.quote(",id"),"")//
                        + "\" ,\"Awards\":\"" + table.get("id")
                        + "\" ,\"Poster\":\"" + table.get("poster_path")//
                        + "\" ,\"Type\":\"" + media_type//
                        + "\" ,\"imdbRating\":\"" + table.get("vote_average")//
                        + "\" ,\"imdbID\":\"" + table.get("imdb_id")//
                        + "\"}";
                return FinalJson;
        }

        public void scrape(MovieCollection m) throws IOException, InterruptedException, SQLException {
                ArrayList<Movie> movies = m.getMovies();
                for (int i = 0; i < movies.size(); i++) {
                        //System.out.println(movies.get(i).toString());

                        try {
                                if ((Boolean) Database.db().SELECT("Scraped", "API_Done", new Condition("Filepath", movies.get(i).getDirectory()))) {
                                        continue;
                                }
                        } catch (IndexOutOfBoundsException e) {
                                continue;
                        }

                        //Movie parsed = Utils.parseJSON(GET(movies.get(i).getTitle()));
                        if (movies.get(i).getImdbID() == null) {
                                String old_title = movies.get(i).getTitle();
                                //System.out.println(old_title);
                                String json = "";
                                try {
                                        json = GET(movies.get(i).getTitle());
                                } catch (MovieNotFoundException e) {
                                        continue;
                                }

                                System.out.println(json);
                                Movie parsed = Utils.parseMovieJSON(json);
                                movies.set(i, parsed);
                                Database.db().UPDATE("Scraped", "API_Done", true, new Condition("Filepath", movies.get(i).getDirectory()));
                                Database.db().DELETE("Movies", "Title", old_title);
                                DBMethods.insertMovie(movies.get(i));
                        }

                }

        }

        private String setupString(String s) {
                s = s.replaceAll(Pattern.quote("."), " ");
                s = s.replaceAll(" ", "%20");
                return s;
        }

        private class FileUtils {

                public static String read(String name, String dir) {
                        BufferedReader reader;
                        String data = "";

                        try {
                                reader = new BufferedReader(new FileReader(dir + name));
                                String line = reader.readLine();

                                while (line != null) {
                                        data = data + line + "\n";
                                        line = reader.readLine();
                                }

                                reader.close();
                                return data.trim();
                        } catch (IOException e) {
                        }
                        return null;
                }
        }
}
