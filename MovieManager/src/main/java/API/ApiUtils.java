package API;

import java.io.IOException;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

import Database.Database;
import Exceptions.MovieNotFoundException;
import Utils.JsonUtils;
import Utils.Utils;
import kdesp73.madb.Condition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.InputStream;
import java.util.Scanner;
import java.net.URI;
import java.net.http.*;
import java.sql.SQLException;

public class ApiUtils {

    /**
     * 
     * @param s
     * @return
     */
    public static String setupUrlString(String s) {
        s = s.replaceAll(Pattern.quote("."), " ");
        s = s.replaceAll(" ", "%20");
        return s;
    }

    /**
     * 
     * @param url
     * @return
     * @throws ConnectException
     * @throws InterruptedException
     * @throws IOException
     */
    public static HttpResponse<String> http_get(String url) throws ConnectException, InterruptedException, IOException{
        HttpResponse<String> response;
        HttpRequest request;

        request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
        
        response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        API.logger.logging("trace", "Url: "+url);
        API.logger.logging("trace", "Responce: "+response.statusCode());
        return response;
    }

    /**
     * 
     * @param title String
     * @param api_key String
     * @param movies_lists String
     * @throws ConnectException
     * @throws InterruptedException
     * @throws IOException
     * @throws SQLException
     * @return Dictionary<String, String> {
     *         Title
     *         ,Year
     *         ,Rated
     *         ,Released
     *         ,Runtime
     *         ,Genre
     *         ,Director
     *         ,Writer
     *         ,Actors
     *         ,Plot
     *         ,Language
     *         ,Country
     *         ,Awards
     *         ,Poster
     *         ,Type
     *         ,imdbRating
     *         ,imdbID
     *         }
     */
    public static Dictionary<String, String> infoToMap(String title, String api_key, String movies_lists) throws ConnectException, InterruptedException, IOException, SQLException {
        HttpResponse<String> response;

        String url = "";
        Dictionary<String, String> table = Utils.JsonToDictionary(movies_lists);

        // throws NullPointerException{
        String total_results = table.get("total_results").toString();
        if (total_results.equals("0")) {
            throw new MovieNotFoundException("Api was unable to find info for the video:" + title);
        }
        // }

        String media_type = "";

        // throws NullPointerException{
        media_type = table.get("media_type").toString();
        // }

        String genre = table.get("genre_ids").toString();
        String genre_names = "";
        String current_gen;
        // for (String gen : genre.split(",")) {
        //     // System.out.println("Genre_id: " + gen);
        //     current_gen = (String) Database.db().SELECT("Categories", "Category", new Condition("TMDB_id", gen));
        //     genre_names = genre_names + current_gen + ",";
        // }

        // Get Movie from movie id
        // if (media_type == "movie"){url =
        // "https://api.themoviedb.org/3/movie/"+table.get("id")+"?api_key="+api_key+"&language=en-US";}
        // else{url =
        // "https://api.themoviedb.org/3/tv/"+table.get("id")+"?api_key="+api_key+"&language=en-US";}
        url = "https://api.themoviedb.org/3/movie/" + table.get("id") + "?api_key=" + api_key + "&language=en-US";
        response = ApiUtils.http_get(url);
        // Change table for movies list to movie info
        table = Utils.JsonToDictionary(response.body());

        // Get movie credits
        url = "https://api.themoviedb.org/3/movie/" + table.get("id") + "/credits?api_key=" + api_key + "&language=en-US";
        response = ApiUtils.http_get(url);
        String credids = response.body();

        // Get movie release date
        url = "https://api.themoviedb.org/3/movie/" + table.get("id") + "/release_dates?api_key=" + api_key;
        response = ApiUtils.http_get(url);

        String release_dates = response.body();
        System.out.println("Response Status: " + response.statusCode());

        // Seperate info
        String overview = table.get("overview").toString().replaceAll(Pattern.quote("'"), "''");
        // System.out.println(credids);
        ArrayList<String> credits_list = new ArrayList<String>(Arrays.asList("crew","0","original_name")); //ApiUtils.find_in_api(credids, "job", "Director")
        ArrayList<String> actors_list = new ArrayList<String>(Arrays.asList("cast","0","original_name")); //ApiUtils.find_actors(credids).replaceAll("\"", "\"\"")
        ArrayList<String> rated_list = new ArrayList<String>(Arrays.asList("results","0", "release_dates", "0","certification")); //ApiUtils.find_actors(credids).replaceAll("\"", "\"\"")
        ArrayList<String> rated_based_on_list = new ArrayList<String>(Arrays.asList("results","0","iso_3166_1")); //ApiUtils.find_actors(credids).replaceAll("\"", "\"\"")

        String director = "";
        String writer = "";
        String actors = "";
        String rated = "";
        
        try{
            director = JsonUtils.getMultipleValues(credids, credits_list, "job", "Director", 1).get(0).replaceAll(Pattern.quote("'"), "''");
            writer   = JsonUtils.getMultipleValues(credids, credits_list, "job", "Screenplay", 1).get(0).replaceAll(Pattern.quote("'"), "''");
            actors   = ApiUtils.find_actors(credids, actors_list, "order", "0", 1).replaceAll("'", "''");
            rated    = JsonUtils.getMultipleValues(release_dates, rated_list, rated_based_on_list, "US", 1).get(0);
        }catch(Exception e){
            e.printStackTrace();
        }
        // String rated = ApiUtils.find_rated(release_dates);

        Dictionary<String, String> info_table = new Hashtable<String, String>(17);

        info_table.put("Title",      ApiUtils.some_error_handling(table.get("title").toString().replaceAll("'s", "")));
        info_table.put("Year",       ApiUtils.some_error_handling(table.get("release_date").toString().split(Pattern.quote("-"))[0]));
        info_table.put("Rated",      ApiUtils.some_error_handling(rated));
        info_table.put("Released",   ApiUtils.some_error_handling(table.get("release_date")));
        info_table.put("Runtime",    ApiUtils.some_error_handling(table.get("runtime")) + "min");
        info_table.put("Genre",      ApiUtils.some_error_handling(genre_names));
        info_table.put("Director",   ApiUtils.some_error_handling(director));
        info_table.put("Writer",     ApiUtils.some_error_handling(writer));
        info_table.put("Actors",     ApiUtils.some_error_handling(actors));
        info_table.put("Plot",       ApiUtils.some_error_handling(overview.replaceAll(Pattern.quote("\""), "")));
        info_table.put("Language",   ApiUtils.some_error_handling(table.get("original_language")));
        info_table.put("Country",    ApiUtils.some_error_handling(table.get("iso_3166_1")));
        info_table.put("Awards",     "");
        info_table.put("Poster",     ApiUtils.some_error_handling(table.get("poster_path")));
        info_table.put("Type",       media_type);
        info_table.put("imdbRating", ApiUtils.some_error_handling(table.get("vote_average")));
        info_table.put("imdbID",     ApiUtils.some_error_handling(table.get("imdb_id")));

        API.logger.logging("", "info for "+title+" : "+info_table.toString());

        return info_table;
    }

    /**
     * 
     * @param str
     * @return
     */
    public static String prepare_string(String str) {
        String temp = str;
        String[] to_replace_with_space = {
            ".",
            "_"
        };

        String[] to_replace_with_nothing = {
            "1080p",
            "720p",
            "BluRay",
            "Bluray",
            "BRRip",
            "WEBRip",
            "x264",
            "H264",
            "AAC-RARBG",
            "-[YTS AM]",
            "-[YTS LS]",
            "-[YTS LT]",
            "-[YTS AG]",
            "[YTS AM]",
            "HomeMB|DD5|GalaxyRG",
            "BrRip",
            "BOKUTOX",
            "mkv-muxed",
            "( )+",
            "264",
            "YIFY",
            " [0-9][0-9][0-9][0-9]"
        };

        for (String s: to_replace_with_space){
            temp = temp.replaceAll(Pattern.quote(s), "");
        }
        for (String s: to_replace_with_nothing){
            temp = temp.replaceAll(Pattern.quote(s), "");
        }

        return temp;
    }

    /**
     * 
     * @param obj
     * @return
     */
    public static String some_error_handling(Object obj) {
        if (obj == null)
            return "";

        return obj.toString();
    }

    /**
     * 
     * @param json
     * @return
     */
    public static String find_actors(String json, ArrayList<String> list, String based_on, String equals_based_on, int pos_element) throws Exception {
        ArrayList<String> names = JsonUtils.getMultipleValues(json, list, based_on, equals_based_on, pos_element);
        String namesString = "";
        
        for (String name : names) {
            namesString += name + ",";
        }
        return StringUtils.substring(namesString, 0, namesString.length() - 1);
    }

    /**
     * 
     * @param json
     * @return
     */
    public static String find_rated(String json) {
        String[] jsonlist;// = new String[json.split(",").length-1];
        // ArrayList<String> names = new ArrayList<>();
        String rated = "";
        String temp;
        jsonlist = json.split(",");

        for (int i = 0; i < jsonlist.length; i++) {

            if (jsonlist[i].contains("iso_3166_1") && jsonlist[i].contains("US")) {
                // System.out.println("yes");
                // System.out.println(jsonlist[i]);
                // System.out.println(jsonlist[i + 1]);
                temp = jsonlist[i + 1];
                // temp = temp.replaceAll("( )", "");
                temp = StringUtils.substring(temp, 35, temp.length() - 1);
                rated = temp;
            }
        }
        return rated;
        // if(rated.length()<1) return "s";
        // else return StringUtils.substring(rated, 0, rated.length()-1);
    }

    /**
     * 
     * @param name
     * @param dir
     * @return
     */
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

    /**
     * 
     * @param urlString
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public static String getKey(String urlString) throws MalformedURLException, IOException {
        URL url = new URL(urlString);
        InputStream inputStream = url.openStream();

        Scanner s = new Scanner(inputStream).useDelimiter("\\A");
        String key = s.hasNext() ? s.next() : "";
        s.close();
        return key;
    }
}
