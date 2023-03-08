package main;

import API.API;
import static API.API.searchMovie;
import Database.DBMethods;
import info.movito.themoviedbapi.TmdbAccount;
import info.movito.themoviedbapi.TmdbAccount.MovieListResultsPage;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.TmdbSearch;
import info.movito.themoviedbapi.TmdbSearch.MultiListResultsPage;
import info.movito.themoviedbapi.model.Genre;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.Multi;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.people.PersonCast;
import info.movito.themoviedbapi.model.people.PersonCrew;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

//import java.util.regex;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;



public class MovieManager {

        public static String[] JSON = {
                "{\"Title\":\"Fight Club\",\"Year\":\"1999\",\"Rated\":\"R\",\"Released\":\"15 Oct 1999\",\"Runtime\":\"139 min\",\"Genre\":\"Drama\",\"Director\":\"David Fincher\",\"Writer\":\"Chuck Palahniuk, Jim Uhls\",\"Actors\":\"Brad Pitt, Edward Norton, Meat Loaf\",\"Plot\":\"An insomniac office worker and a devil-may-care soap maker form an underground fight club that evolves into much more.\",\"Language\":\"English\",\"Country\":\"Germany, United States\",\"Awards\":\"Nominated for 1 Oscar. 11 wins & 38 nominations total\",\"Poster\":\"https:\\/\\/m.media-amazon.com\\/images\\/M\\/MV5BNDIzNDU0YzEtYzE5Ni00ZjlkLTk5ZjgtNjM3NWE4YzA3Nzk3XkEyXkFqcGdeQXVyMjUzOTY1NTc@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"8.8\\/10\"},{\"Source\":\"Rotten Tomatoes\",\"Value\":\"79%\"},{\"Source\":\"Metacritic\",\"Value\":\"66\\/100\"}],\"Metascore\":\"66\",\"imdbRating\":\"8.8\",\"imdbVotes\":\"2,096,810\",\"imdbID\":\"tt0137523\",\"Type\":\"movie\",\"DVD\":\"14 Oct 2003\",\"BoxOffice\":\"$37,030,102\",\"Production\":\"N\\/A\",\"Website\":\"N\\/A\",\"Response\":\"True\"}",
                "{\"Title\":\"V for Vendetta\",\"Year\":\"2005\",\"Rated\":\"R\",\"Released\":\"17 Mar 2006\",\"Runtime\":\"132 min\",\"Genre\":\"Action, Drama, Sci-Fi\",\"Director\":\"James McTeigue\",\"Writer\":\"Lilly Wachowski, Lana Wachowski, David Lloyd\",\"Actors\":\"Hugo Weaving, Natalie Portman, Rupert Graves\",\"Plot\":\"In a future British dystopian society, a shadowy freedom fighter, known only by the alias of 'V', plots to overthrow the tyrannical government - with the help of a young woman.\",\"Language\":\"English\",\"Country\":\"United States, United Kingdom, Germany\",\"Awards\":\"7 wins & 29 nominations\",\"Poster\":\"https:\\/\\/m.media-amazon.com\\/images\\/M\\/MV5BOTI5ODc3NzExNV5BMl5BanBnXkFtZTcwNzYxNzQzMw@@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"8.2\\/10\"},{\"Source\":\"Rotten Tomatoes\",\"Value\":\"73%\"},{\"Source\":\"Metacritic\",\"Value\":\"62\\/100\"}],\"Metascore\":\"62\",\"imdbRating\":\"8.2\",\"imdbVotes\":\"1,112,280\",\"imdbID\":\"tt0434409\",\"Type\":\"movie\",\"DVD\":\"01 Aug 2006\",\"BoxOffice\":\"$70,511,035\",\"Production\":\"N\\/A\",\"Website\":\"N\\/A\",\"Response\":\"True\"}",
                "{\"Title\":\"Motherless Brooklyn\",\"Year\":\"2019\",\"Rated\":\"R\",\"Released\":\"01 Nov 2019\",\"Runtime\":\"144 min\",\"Genre\":\"Crime, Drama, Mystery\",\"Director\":\"Edward Norton\",\"Writer\":\"Edward Norton, Jonathan Lethem\",\"Actors\":\"Edward Norton, Gugu Mbatha-Raw, Alec Baldwin\",\"Plot\":\"In 1950s New York, a lonely private detective afflicted with Tourette's Syndrome ventures to solve the murder of his mentor and only friend.\",\"Language\":\"English, French\",\"Country\":\"United States\",\"Awards\":\"2 wins & 15 nominations\",\"Poster\":\"https:\\/\\/m.media-amazon.com\\/images\\/M\\/MV5BNzQ0Mjk1YjItNWI1Ny00NWE2LWFlYTAtYjViY2YzMTVlOGVmXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"6.8\\/10\"},{\"Source\":\"Rotten Tomatoes\",\"Value\":\"64%\"},{\"Source\":\"Metacritic\",\"Value\":\"60\\/100\"}],\"Metascore\":\"60\",\"imdbRating\":\"6.8\",\"imdbVotes\":\"57,493\",\"imdbID\":\"tt0385887\",\"Type\":\"movie\",\"DVD\":\"01 Nov 2019\",\"BoxOffice\":\"$9,277,736\",\"Production\":\"N\\/A\",\"Website\":\"N\\/A\",\"Response\":\"True\"}"
        };

        public static Dictionary JsonToDictionary(String input){
            
            ArrayList<String> list = new ArrayList<String>();
            //String fs = input.replaceAll(Pattern.quote("{"),"");
            String[] pros = new String[2];
            Dictionary table = new Hashtable();
            String item1,item2;
            String s = input.replaceAll(Pattern.quote("\"results\":[{"), "");
            s = s.replaceAll(Pattern.quote("{"), "");
            s = s.replaceAll(Pattern.quote("}"), "");
            
            for(String sp:s.split("\",\"|[e],\"|\\],\"|,\"")){//  |[0-9],\"|\":\\[
                list.add(sp);
                //for (String sp1:sp.split(Pattern.quote(",\""))){}
                try{
                    sp = sp.replaceAll(Pattern.quote("\""), "");
                    sp = sp.replaceAll(Pattern.quote("["), "");
                    sp = sp.replaceAll(Pattern.quote("]"), "");
                    sp = sp.replaceAll(Pattern.quote("{"), "");
                    sp = sp.replaceAll(Pattern.quote("}"), "");
                    pros = sp.split(Pattern.quote(":"));
                    
                    item1 = pros[0];
                    item2 = pros[1];
             
                    //if (pros[0] == "") 
                    //if(item2.contains("{[")) continue;
                    table.put(item1,item2);
                }catch(Exception ex){
                    //continue;
                    table.put(sp,"");
                }
            }
            
            return table;
        }
        
        public static boolean isStringOnlyAlphabet(String str)
        {

            return ((str != null) && (!str.equals(""))
                    && (str.matches("\"[a-zA-Z]+\":\\Q{[\\E")));
        }
        
        public static List<MovieDb> searchMovie(String movieName, int year, String language, boolean adult, int page){
            String api_key = FileUtils.read("api_key.txt", System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") + "/data/");
            TmdbSearch search = new TmdbApi(api_key).getSearch();
            MovieResultsPage movies = search.searchMovie(movieName, year, language, adult, page);
            List<MovieDb> movieList = movies.getResults();
            return movieList;
        }
        
        public static List<MovieDb> searchMovie(String api_key, String movieName, String language, boolean adult, int page){
            
            TmdbSearch search = new TmdbApi(api_key).getSearch();
            MovieResultsPage movies = search.searchMovie(movieName, null, language, adult, page);
            List<MovieDb> movieList = movies.getResults();
            return movieList;
        }
        
        public static void main(String[] args) throws SQLException {
                //DBMethods.formatDatabase();
                String api_key = FileUtils.read("api_key.txt", System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") + "/data/");
                String title = "Cop Secret";
                String FinalJson;
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
                        + "\" ,\"Year\":\"" + (movie.getReleaseDate().split("-"))[0]
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
                System.out.println(FinalJson);
        }
}

 class FileUtils {

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