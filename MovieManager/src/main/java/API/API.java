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
import java.io.InputStream;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.regex.Pattern;
import kdesp73.madb.Condition;
//mport org.json.simple.JSONValue;
//import org.json.*;
import java.util.Random;
import java.util.Scanner;
import main.Movie;
import main.MovieCollection;
//import static main.MovieManager.searchMovie;

import org.apache.commons.lang3.StringUtils;

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
                this.title = title.replaceAll(Pattern.quote("."), " ");
                this.title = this.title.replaceAll(Pattern.quote("_"), " ");
                this.title = this.title.replaceAll(Pattern.quote("1080p"), "");
                this.title = this.title.replaceAll(Pattern.quote("720p"), "");
                this.title = this.title.replaceAll(Pattern.quote("BluRay"), "");
                this.title = this.title.replaceAll(Pattern.quote("Bluray"), "");
                this.title = this.title.replaceAll(Pattern.quote("BRRip"), "");
                this.title = this.title.replaceAll(Pattern.quote("WEBRip"), "");
                this.title = this.title.replaceAll(Pattern.quote("x264"), "");
                this.title = this.title.replaceAll(Pattern.quote("H264"), "");
                this.title = this.title.replaceAll(Pattern.quote("AAC-RARBG"), "");
                //this.title = this.title.replaceAll("-[YTS \\w\\w]", "");
                this.title = this.title.replaceAll(Pattern.quote("-[YTS AM]"), "");
                this.title = this.title.replaceAll(Pattern.quote("-[YTS LS]"), "");
                this.title = this.title.replaceAll(Pattern.quote("-[YTS LT]"), "");
                this.title = this.title.replaceAll(Pattern.quote("-[YTS AG"), "");
                this.title = this.title.replaceAll(Pattern.quote("[YTS AM]"), "");
                this.title = this.title.replaceAll("HomeMB|DD5|GalaxyRG", "");
                this.title = this.title.replaceAll(Pattern.quote("BrRip"), "");
                this.title = this.title.replaceAll(Pattern.quote("BOKUTOX"), "");
                this.title = this.title.replaceAll(Pattern.quote("mkv-muxed"), "");
                this.title = this.title.replaceAll("( )+", " ");
                this.title = this.title.replaceAll(Pattern.quote("264"), "");
                this.title = this.title.replaceAll(Pattern.quote("YIFY"), "");
                this.title = this.title.replaceAll(" [0-9][0-9][0-9][0-9]", "");
                System.out.println("Title: " + this.title);
                //String api_key = FileUtils.read("api_key.txt", System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") + "/data/");
                String api_key = ApiUtils.getKey("https://users.iee.ihu.gr/~iee2021035/api_key.txt");

                String FinalJson;

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

                Dictionary table = Utils.JsonToDictionary(response.body());
                try {
                        String total_results = table.get("total_results").toString();
                        if (total_results.equals("0")) {
                                throw new MovieNotFoundException("Api was unable to find info for the video:" + title);
                        }
                } catch (NullPointerException e) {
                }
                //System.out.println("API multi search table: " + table);
                String media_type = "";
                try {
                        media_type = table.get("media_type").toString();
                } catch (NullPointerException e) {
                        return "";
                }
                String genre = table.get("genre_ids").toString();
                //System.out.println("\nMedia Type & Genre: " + media_type + "\n" + genre);
                String genre_names = "";
                String current_gen;
                for (String gen : genre.split(",")) {
                        //System.out.println("Genre_id: " + gen);
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

                /*
                System.out.println("Title for search: "+this.title);
                List<MovieDb> movieList = searchMovie(api_key, this.title, "en", false, 1);
                MovieDb movie = movieList.get(0);
                System.out.println(movieList);
                System.out.println("API title: "+movie.getTitle());
                String overview = movie.getOverview().replaceAll(Pattern.quote("'"), "");
                String debug_plot = overview.replaceAll(Pattern.quote("\""), "");
                System.out.println(debug_plot);
                List<Genre> genres = movie.getGenres();
                String genre = "";
                if(genres != null){
                    for(int i=0;i<genres.size()-1;i++){
                        if(i!=genres.size()-1) genre += genres.get(i).getName()+",";
                        else genre += genres.get(i).getName();
                    }
                }
                
                List<PersonCast> casts = movie.getCast();
                String cast = "";
                if(casts != null){
                    for(int i=0;i<casts.size()-1;i++){
                        if(i!=casts.size()-2) cast += casts.get(i).getName()+",";
                        else cast += casts.get(i).getName();
                    }
                }
                
                List<PersonCrew> crew = movie.getCrew();
                String writer = "";
                String director = "";
                
                if(crew != null){
                    for(int i=0;i<crew.size()-1;i++){
                        if(crew.get(i).getDepartment() != "Writing") continue;
                        if(i!=crew.size()-2) writer += crew.get(i).getName()+",";
                        else writer += crew.get(i).getName();
                    }

                    for(int i=0;i<crew.size()-1;i++){
                        if(crew.get(i).getDepartment() != "Directing") continue;
                        if(i!=crew.size()-2) director += crew.get(i).getName()+",";
                        else director += crew.get(i).getName();
                    }
                }
                
                String rated = "";
                if(movie.getReleases() != null) rated =(String) movie.getReleases().get(0).getReleaseDates().get(0).getCertification();
                
                String country = "";
                if(movie.getProductionCountries() != null) country = movie.getProductionCountries().get(0).getName();
                 */
                String overview = table.get("overview").toString();
                overview = overview.replaceAll(Pattern.quote("'"), "");
                //Random rand = new Random(); 
                FinalJson = "{"
                        + " \"Title\":\"" + table.get("title").toString().replaceAll("'s", "") //
                        + "\" ,\"Year\":\"" + table.get("release_date").toString().split(Pattern.quote("-"))[0] //
                        + "\" ,\"Rated\":\"" + table.get("id")
                        + "\" ,\"Released\":\"" + table.get("release_date")
                        + "\" ,\"Runtime\":\"" + table.get("runtime")
                        + "\" ,\"Genre\":\"" + genre_names
                        + "\" ,\"Director\":\"" + table.get("id")
                        + "\" ,\"Writer\":\"" + table.get("id")
                        + "\" ,\"Actors\":\"" + table.get("id")
                        + "\" ,\"Plot\":\"" + StringUtils.substring(overview.replaceAll(Pattern.quote("\""), ""), 0, 254) //StringUtils.substring(table.get("overview").replaceAll(Pattern.quote("\""), ""), 0, 254)
                        + "\" ,\"Language\":\"" + table.get("original_language")
                        + "\" ,\"Country\":\"" + table.get("iso_3166_1")//(table.get("origin_country").toString()).replace(Pattern.quote(",id"),"")//
                        + "\" ,\"Awards\":\"" + table.get("id")
                        + "\" ,\"Poster\":\"" + table.get("poster_path")
                        + "\" ,\"Type\":\"" + media_type
                        + "\" ,\"imdbRating\":\"" + table.get("vote_average")
                        + "\" ,\"imdbID\":\"" + table.get("imdb_id")
                        + "\"}";
                return FinalJson;
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
                        }

                }

        }

        private String setupString(String s) {
                s = s.replaceAll(Pattern.quote("."), " ");
                s = s.replaceAll(" ", "%20");
                return s;
        }

        private class ApiUtils {

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

                public static String getKey(String urlString) throws MalformedURLException, IOException {
                        URL url = new URL(urlString);
                        InputStream inputStream = url.openStream();

                        Scanner s = new Scanner(inputStream).useDelimiter("\\A");
                        return s.hasNext() ? s.next() : "";
                }
        }
}
