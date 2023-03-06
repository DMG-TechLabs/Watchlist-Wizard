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
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbSearch;
import info.movito.themoviedbapi.model.Genre;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.people.PersonCast;
import info.movito.themoviedbapi.model.people.PersonCrew;
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.regex.Pattern;
import kdesp73.madb.Condition;
//mport org.json.simple.JSONValue;
//import org.json.*;

import main.Movie;
import main.MovieCollection;
import static main.MovieManager.searchMovie;

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
        
        public static List<MovieDb> searchMovie(String api_key, String movieName, String language, boolean adult, int page){
            TmdbSearch search = new TmdbApi(api_key).getSearch();
            MovieResultsPage movies = search.searchMovie(movieName, null, language, adult, page);
            List<MovieDb> movieList = movies.getResults();
            return movieList;
        }

        //Methods
        public String GET(String title) throws IOException, InterruptedException, SQLException {
                setTitle(title);
                System.out.println("Title: " + title);
                String api_key = FileUtils.read("api_key.txt", System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") + "/data/");
                
                String FinalJson;
                /*
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
                */
                
                System.out.println("Title for search: "+title.replaceAll(Pattern.quote("."), " "));
                List<MovieDb> movieList = searchMovie(api_key, title.replaceAll(Pattern.quote("."), " "), "en", false, 1);
                MovieDb movie = movieList.get(0);
                System.out.println(movieList);
                List<Genre> genres = movie.getGenres();
                String genre = "";
                if(genres != null){
                    for(int i=0;i<genres.size();i++){
                        if(i!=genres.size()-1) genre += genres.get(i).getName()+",";
                        else genre += genres.get(i).getName();
                    }
                }
                
                List<PersonCast> casts = movie.getCast();
                String cast = "";
                if(genres != null){
                    for(int i=0;i<casts.size();i++){
                        if(i!=casts.size()-2) cast += casts.get(i).getName()+",";
                        else cast += casts.get(i).getName();
                    }
                }
                
                List<PersonCrew> crew = movie.getCrew();
                String writer = "";
                String director = "";
                
                if(crew != null){
                    for(int i=0;i<crew.size();i++){
                        if(crew.get(i).getDepartment() != "Writing") continue;
                        if(i!=crew.size()-2) writer += crew.get(i).getName()+",";
                        else writer += crew.get(i).getName();
                    }

                    for(int i=0;i<crew.size();i++){
                        if(crew.get(i).getDepartment() != "Directing") continue;
                        if(i!=crew.size()-2) director += crew.get(i).getName()+",";
                        else director += crew.get(i).getName();
                    }
                }
                
                String rated = "";
                if(movie.getReleases() != null) rated = movie.getReleases().get(0).getReleaseDates().get(0).getCertification();
                
                String country = "";
                if(movie.getProductionCountries() != null) country = movie.getProductionCountries().get(0).getName();
                
                FinalJson = "{"
                        + " \"Title\":\"" + movie.getTitle() //
                        + "\" ,\"Year\":\"" + (movie.getReleaseDate().split("-"))[0]//table.get("release_date").toString().split(Pattern.quote("-"))[0] //
                        + "\" ,\"Rated\":\"" + rated
                        + "\" ,\"Released\":\"" + movie.getReleaseDate()
                        + "\" ,\"Runtime\":\"" + movie.getRuntime()
                        + "\" ,\"Genre\":\"" + genre
                        + "\" ,\"Director\":\"" + director
                        + "\" ,\"Writer\":\"" + writer
                        + "\" ,\"Actors\":\"" + cast
                        + "\" ,\"Plot\":\"" + movie.getOverview()
                        + "\" ,\"Language\":\"" + movie.getOriginalLanguage()
                        + "\" ,\"Country\":\"" + country
                        + "\" ,\"Awards\":\"" + ""
                        + "\" ,\"Poster\":\"" + movie.getPosterPath()
                        + "\" ,\"Type\":\"" + movie.getMediaType()
                        + "\" ,\"imdbRating\":\"" + movie.getVoteAverage()
                        + "\" ,\"imdbID\":\"" + movie.getImdbID()
                        + "\"}";
                return FinalJson;
        }

        public void scrape(MovieCollection m) throws IOException, InterruptedException, SQLException {
                ArrayList<Movie> movies = m.getMovies();
                for (int i = 0; i < movies.size(); i++) {
                        //System.out.println(movies.get(i).toString());

                        //Movie parsed = Utils.parseJSON(GET(movies.get(i).getTitle()));
                        if (movies.get(i).getImdbID() == null) {
                                String old_title = movies.get(i).getTitle();
                                //System.out.println(old_title);
                                String json = "";
                                try {
                                        json = GET(old_title);
                                } catch (MovieNotFoundException | IndexOutOfBoundsException e) {
                                        continue;
                                }

                                //System.out.println(json);
                                Movie parsed = Utils.parseMovieJSON(json);
                                movies.set(i, parsed);
                                //Database.db().UPDATE("Scraped", "API_Done", true, new Condition("Filepath", movies.get(i).getDirectory()));
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