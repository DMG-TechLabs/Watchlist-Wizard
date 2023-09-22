package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import API.API;
import Database.DBMethods;
//import java.util.regex;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.regex.Pattern;

import Utils.Utils;
import kdesp73.themeLib.Theme;
import kdesp73.themeLib.ThemeCollection;
import kdesp73.themeLib.YamlFile;
import Database.Database;

public class MovieManager {

	public static String[] JSON = {
			"{\"Title\":\"Fight Club\",\"Year\":\"1999\",\"Rated\":\"R\",\"Released\":\"15 Oct 1999\",\"Runtime\":\"139 min\",\"Genre\":\"Drama\",\"Director\":\"David Fincher\",\"Writer\":\"Chuck Palahniuk, Jim Uhls\",\"Actors\":\"Brad Pitt, Edward Norton, Meat Loaf\",\"Plot\":\"An insomniac office worker and a devil-may-care soap maker form an underground fight club that evolves into much more.\",\"Language\":\"English\",\"Country\":\"Germany, United States\",\"Awards\":\"Nominated for 1 Oscar. 11 wins & 38 nominations total\",\"Poster\":\"https:\\/\\/m.media-amazon.com\\/images\\/M\\/MV5BNDIzNDU0YzEtYzE5Ni00ZjlkLTk5ZjgtNjM3NWE4YzA3Nzk3XkEyXkFqcGdeQXVyMjUzOTY1NTc@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"8.8\\/10\"},{\"Source\":\"Rotten Tomatoes\",\"Value\":\"79%\"},{\"Source\":\"Metacritic\",\"Value\":\"66\\/100\"}],\"Metascore\":\"66\",\"imdbRating\":\"8.8\",\"imdbVotes\":\"2,096,810\",\"imdbID\":\"tt0137523\",\"Type\":\"movie\",\"DVD\":\"14 Oct 2003\",\"BoxOffice\":\"$37,030,102\",\"Production\":\"N\\/A\",\"Website\":\"N\\/A\",\"Response\":\"True\"}",
			"{\"Title\":\"V for Vendetta\",\"Year\":\"2005\",\"Rated\":\"R\",\"Released\":\"17 Mar 2006\",\"Runtime\":\"132 min\",\"Genre\":\"Action, Drama, Sci-Fi\",\"Director\":\"James McTeigue\",\"Writer\":\"Lilly Wachowski, Lana Wachowski, David Lloyd\",\"Actors\":\"Hugo Weaving, Natalie Portman, Rupert Graves\",\"Plot\":\"In a future British dystopian society, a shadowy freedom fighter, known only by the alias of 'V', plots to overthrow the tyrannical government - with the help of a young woman.\",\"Language\":\"English\",\"Country\":\"United States, United Kingdom, Germany\",\"Awards\":\"7 wins & 29 nominations\",\"Poster\":\"https:\\/\\/m.media-amazon.com\\/images\\/M\\/MV5BOTI5ODc3NzExNV5BMl5BanBnXkFtZTcwNzYxNzQzMw@@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"8.2\\/10\"},{\"Source\":\"Rotten Tomatoes\",\"Value\":\"73%\"},{\"Source\":\"Metacritic\",\"Value\":\"62\\/100\"}],\"Metascore\":\"62\",\"imdbRating\":\"8.2\",\"imdbVotes\":\"1,112,280\",\"imdbID\":\"tt0434409\",\"Type\":\"movie\",\"DVD\":\"01 Aug 2006\",\"BoxOffice\":\"$70,511,035\",\"Production\":\"N\\/A\",\"Website\":\"N\\/A\",\"Response\":\"True\"}",
			"{\"Title\":\"Motherless Brooklyn\",\"Year\":\"2019\",\"Rated\":\"R\",\"Released\":\"01 Nov 2019\",\"Runtime\":\"144 min\",\"Genre\":\"Crime, Drama, Mystery\",\"Director\":\"Edward Norton\",\"Writer\":\"Edward Norton, Jonathan Lethem\",\"Actors\":\"Edward Norton, Gugu Mbatha-Raw, Alec Baldwin\",\"Plot\":\"In 1950s New York, a lonely private detective afflicted with Tourette's Syndrome ventures to solve the murder of his mentor and only friend.\",\"Language\":\"English, French\",\"Country\":\"United States\",\"Awards\":\"2 wins & 15 nominations\",\"Poster\":\"https:\\/\\/m.media-amazon.com\\/images\\/M\\/MV5BNzQ0Mjk1YjItNWI1Ny00NWE2LWFlYTAtYjViY2YzMTVlOGVmXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"6.8\\/10\"},{\"Source\":\"Rotten Tomatoes\",\"Value\":\"64%\"},{\"Source\":\"Metacritic\",\"Value\":\"60\\/100\"}],\"Metascore\":\"60\",\"imdbRating\":\"6.8\",\"imdbVotes\":\"57,493\",\"imdbID\":\"tt0385887\",\"Type\":\"movie\",\"DVD\":\"01 Nov 2019\",\"BoxOffice\":\"$9,277,736\",\"Production\":\"N\\/A\",\"Website\":\"N\\/A\",\"Response\":\"True\"}"
	};

	// public static Dictionary JsonToDictionary(String input) {

	// 	return ((str != null) && (!str.equals(""))
	// 			&& (str.matches("\"[a-zA-Z]+\":\\Q{[\\E")));
	// }

	public static void main(String[] args) throws SQLException {
		// System.out.println("asd iudjd aisudj 2000".replaceAll("
		// [0-9][0-9][0-9][0-9]", ""));
		try {
			System.out.println("ssssss");
			API a = new API("");
			System.out.println("ssssss");
			a.GET("Avengers");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * API api;
		 * try {
		 * api = new API();
		 * System.out.println(api.GET("V for vendeta"));
		 *
		 * } catch (IOException | InterruptedException e) {
		 * // TODO Auto-generated catch block
		 * e.printStackTrace();
		 * }
		 */
		// ImagesUtils.imageToDatabase("tt0434409");

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
