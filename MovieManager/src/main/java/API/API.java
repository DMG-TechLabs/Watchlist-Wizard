package API;

import java.io.IOException;
import java.net.http.*;
import java.sql.SQLException;
import Database.DBMethods;
import Database.Database;
import Exceptions.InvalidKeyException;
import Exceptions.MovieNotFoundException;
import Files.ImagesUtils;
<<<<<<< HEAD
=======
import GUI.GUIMethods;
import Utils.Logs;
>>>>>>> b606cdd (Add logs)
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

import javax.swing.JOptionPane;

import kdesp73.madb.Condition;
import main.Movie;
import main.MovieCollection;



public class API {
        public static Logs logger = new Logs("API");

<<<<<<< HEAD
	private String title;
	HttpRequest request;
	HttpResponse<String> response;

<<<<<<< HEAD
	public API() throws IOException, InterruptedException, SQLException {
	}

	public void setTitle(String title) {
		this.title = setupString(title);
	}

	public String getTitle() {
		return title;
	}
=======
        public API(){}

        private void setTitle(String title) {
                this.title = setupString(title);
=======
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
>>>>>>> 8c699d9 (Api rework (fix, clean up, code seperation))
        }

        public API(String api_key) {
                this.api_key = api_key;
        }

        public String search(String title) throws MalformedURLException, IOException, InterruptedException {
                String title_to_get = title;
                title_to_get = ApiUtils.prepare_string(title_to_get);
                System.out.println("Title: " + title_to_get);

                String url = "https://api.themoviedb.org/3/search/multi?api_key=" + this.api_key + "&language=en-US&query=" + setupString(title_to_get) + "&include_adult=false";
                response = ApiUtils.http_get(url);

                System.out.println("Response Status: " + response.statusCode());
                if (response.statusCode() == 401) throw new InvalidKeyException("Invalid key");

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
>>>>>>> 10dbb63 (Clean Api)


        //Methods
        public String GET(String title) throws IOException, InterruptedException, SQLException, ConnectException, NullPointerException, InvalidKeyException {
<<<<<<< HEAD

<<<<<<< HEAD
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
                
=======
                Dictionary<String, String> info = ApiUtils.infoToMap(title, this.api_key, search(title)); 
>>>>>>> 8c699d9 (Api rework (fix, clean up, code seperation))
                        
=======
                logger.logging("info", "Getting info for: "+title);
                Dictionary<String, String> info = ApiUtils.infoToMap(title, this.api_key, search(title)); 

>>>>>>> b606cdd (Add logs)
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

<<<<<<< HEAD
<<<<<<< HEAD
		String release_dates = response.body();
=======
        //TODO seperate scarepe info function and save function 
        public void scrape(MovieCollection m) throws IOException, InterruptedException, SQLException {
=======

        public void saveToDatabase() throws SQLException{
                //System.out.println(json);
                for (String title : Collections.list(movies_info.keys())) {
                        Movie movie = movies_info.get(title);
                        Database.db().UPDATE("Scraped", "API_Done", true, new Condition("Filepath", movie.getDirectory()));
                        Database.db().DELETE("Movies", "Title", title);
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
>>>>>>> 8c699d9 (Api rework (fix, clean up, code seperation))
                ArrayList<Movie> movies = m.getMovies();

                for (int i = 0; i < movies.size(); i++) {
                        //System.out.println(movies.get(i).toString());
<<<<<<< HEAD
>>>>>>> 10dbb63 (Clean Api)

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
=======
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
                return movies;
        }
>>>>>>> 8c699d9 (Api rework (fix, clean up, code seperation))

        private String setupString(String s) {
                s = s.replaceAll(Pattern.quote("."), " ");
                s = s.replaceAll(" ", "%20");
                return s;
        }
}
