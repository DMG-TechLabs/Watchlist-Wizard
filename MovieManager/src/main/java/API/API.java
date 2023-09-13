package API;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.sql.SQLException;
import Database.DBMethods;
import Database.Database;
import Exceptions.MovieNotFoundException;
import Files.ImagesUtils;
import Utils.Utils;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

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
			JOptionPane.showMessageDialog(null, "There is an invalid key in the database", "Error",
					JOptionPane.ERROR_MESSAGE, null);
			// GUIMethods.dialogError("There is an invalid key in the database ");
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

		String release_dates = response.body();

		String overview = table.get("overview").toString();
		overview = overview.replaceAll(Pattern.quote("'"), "''");
		String director = ApiUtils.find_in_api(credids, "job", "Director").replaceAll(Pattern.quote("'"), "''");
		String writer = ApiUtils.find_in_api(credids, "job", "Screenplay").replaceAll(Pattern.quote("'"), "''");
		// System.out.println("\n\n\nDirecting: "+director);
		// System.out.println("\n\n\nWriting: "+writer);
		// Random rand = new Random();

		FinalJson = "{"
				+ " \"Title\":\"" + ApiUtils.some_error_handling(table.get("title").toString().replaceAll("'s", "")) //
				+ "\" ,\"Year\":\""
				+ ApiUtils.some_error_handling(table.get("release_date").toString().split(Pattern.quote("-"))[0])
				+ "\" ,\"Rated\":\"" + ApiUtils.find_rated(release_dates)
				+ "\" ,\"Released\":\"" + ApiUtils.some_error_handling(table.get("release_date"))
				+ "\" ,\"Runtime\":\"" + ApiUtils.some_error_handling(table.get("runtime")) + "min"
				+ "\" ,\"Genre\":\"" + genre_names
				+ "\" ,\"Director\":\"" + director
				+ "\" ,\"Writer\":\"" + writer
				+ "\" ,\"Actors\":\"" + (ApiUtils.find_actors(credids).replaceAll("\"", "\"\"")).replaceAll("'", "''")
				+ "\" ,\"Plot\":\"" + ApiUtils.some_error_handling(overview.replaceAll(Pattern.quote("\""), ""))
				+ "\" ,\"Language\":\"" + ApiUtils.some_error_handling(table.get("original_language"))
				+ "\" ,\"Country\":\"" + ApiUtils.some_error_handling(table.get("iso_3166_1"))// (table.get("origin_country").toString()).replace(Pattern.quote(",id"),"")//
				+ "\" ,\"Awards\":\"" + ""
				+ "\" ,\"Poster\":\"" + ApiUtils.some_error_handling(table.get("poster_path"))
				+ "\" ,\"Type\":\"" + media_type
				+ "\" ,\"imdbRating\":\"" + ApiUtils.some_error_handling(table.get("vote_average"))
				+ "\" ,\"imdbID\":\"" + ApiUtils.some_error_handling(table.get("imdb_id"))
				+ "\"}";
		return FinalJson;
	}

	public void scrape(MovieCollection m) throws IOException, InterruptedException, SQLException {
		ArrayList<Movie> movies = m.getMovies();
		for (int i = 0; i < movies.size(); i++) {
			// System.out.println(movies.get(i).toString());

        private String setupString(String s) {
                s = s.replaceAll(Pattern.quote("."), " ");
                s = s.replaceAll(" ", "%20");
                return s;
        }
}
