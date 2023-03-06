package Utils;

import GUI.GUIMethods;
import GUI.Theme;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.regex.Pattern;
import main.Movie;
import main.MovieCollection;
import org.json.simple.JSONObject;

public class Utils {

        public static Dictionary JsonToDictionary(String input){

            String[] pros = new String[2];
            Dictionary table = new Hashtable();
            String item1,item2;
            //System.out.println("Utiles\n\n");
            String s = input.replaceAll("\"[a-zA-Z]+\":\\Q[{\\E", "");
            s = s.replaceAll(Pattern.quote("{"), "");
            s = s.replaceAll(Pattern.quote("}"), "");
            //System.out.println(s);
            
            for(String sp:s.split("\",\"|[e],\"|\\],\"|,\"")){//  |[0-9],\"|\":\\[
                
                try{
                    sp = sp.replaceAll(Pattern.quote("\""), "");
                    sp = sp.replaceAll(Pattern.quote("["), "");
                    sp = sp.replaceAll(Pattern.quote("]"), "");
                    sp = sp.replaceAll(Pattern.quote("{"), "");
                    sp = sp.replaceAll(Pattern.quote("}"), "");
                    pros = sp.split(Pattern.quote(":"));
                    
                    item1 = pros[0];
                    item2 = pros[1];
             
                    table.put(item1,item2);
                }catch(Exception ex){
                    //continue;
                    table.put(sp,"");
                }
            }
            
            return table;
        }
    
        public static Movie parseMovieJSON(String JsonString) {
                return new Gson().fromJson(JsonString, Movie.class);
        }

        public static Theme parseThemeJSON(String JsonString) {
//                System.out.println("Parsing Json: " + JsonString);
                JsonString = JsonString.replaceAll(",", ", ");

                Theme newTheme = new Theme();

                //Changing values from hex string to Color object
                newTheme.setBackground(GUIMethods.hexToColor(getJsonValue(JsonString, "bg").replaceAll("\"", "")));
                newTheme.setForeground(GUIMethods.hexToColor(getJsonValue(JsonString, "fg").replaceAll("\"", "")));
                newTheme.setList(GUIMethods.hexToColor(getJsonValue(JsonString, "list").replaceAll("\"", "")));
                newTheme.setListFocus(GUIMethods.hexToColor(getJsonValue(JsonString, "listFocus").replaceAll("\"", "")));
                newTheme.setListForeground(GUIMethods.hexToColor(getJsonValue(JsonString, "listFg").replaceAll("\"", "")));
                newTheme.setButton(GUIMethods.hexToColor(getJsonValue(JsonString, "button").replaceAll("\"", "")));
                newTheme.setButtonForeground(GUIMethods.hexToColor(getJsonValue(JsonString, "buttonFg").replaceAll("\"", "")));
                newTheme.setButtonHover(GUIMethods.hexToColor(getJsonValue(JsonString, "buttonHover").replaceAll("\"", "")));
                newTheme.setToolbar(GUIMethods.hexToColor(getJsonValue(JsonString, "toolbar").replaceAll("\"", "")));
                newTheme.setTextBox(GUIMethods.hexToColor(getJsonValue(JsonString, "textBox").replaceAll("\"", "")));
                newTheme.setTextBoxForeground(GUIMethods.hexToColor(getJsonValue(JsonString, "textBoxFg").replaceAll("\"", "")));
                newTheme.setSecBackground(GUIMethods.hexToColor(getJsonValue(JsonString, "secBg").replaceAll("\"", "")));
                newTheme.setJson(JsonString);
                
//                System.out.println("Process completed successfully\nTheme name: " + newTheme.getName());

                return newTheme;
        }

        public static String getJsonValue(String JsonString, String tag) {
                tag = "\"" + tag + "\"";
                int tagIndex = JsonString.indexOf(tag);
//                System.out.println("Tag index: " + tagIndex);
                if (tagIndex == -1) {
                        return null;
                }

                int begin = tagIndex + tag.length() + 2; // "+2" For space and comma
                int end = JsonString.indexOf(',', begin);

                if (end == -1) {
                        end = JsonString.indexOf('}', begin);
                }

                String value = JsonString.substring(begin, end);
                return value.replaceAll("\\]", "").replaceAll("\\[", "").replaceAll("\\}", "").replaceAll("\n", "").strip();
        }

        public static String themeToJson(Theme theme) {
                JSONObject jsonString = new JSONObject();
                
//                jsonString.put("name", theme.getName());
                jsonString.put("bg", GUIMethods.rgbToHex(theme.getBackground()));
                jsonString.put("fg", GUIMethods.rgbToHex(theme.getForeground()));
                jsonString.put("list", GUIMethods.rgbToHex(theme.getList()));
                jsonString.put("listFocus", GUIMethods.rgbToHex(theme.getListFocus()));
                jsonString.put("listFg", GUIMethods.rgbToHex(theme.getListForeground()));
                jsonString.put("button", GUIMethods.rgbToHex(theme.getButton()));
                jsonString.put("buttonFg", GUIMethods.rgbToHex(theme.getButtonForeground()));
                jsonString.put("buttonHover", GUIMethods.rgbToHex(theme.getButtonHover()));
                jsonString.put("textBox", GUIMethods.rgbToHex(theme.getTextBox()));
                jsonString.put("textBoxFg", GUIMethods.rgbToHex(theme.getTextBoxForeground()));
                jsonString.put("toolbar", GUIMethods.rgbToHex(theme.getToolbar()));
                jsonString.put("secBg", GUIMethods.rgbToHex(theme.getSecBackground()));

                return jsonString.toString();
        }

        public static ArrayList<String> searchMovie(MovieCollection movies, String title) {
                ArrayList<Movie> m = movies.getMovies();
                ArrayList<String> ids = new ArrayList<>();

                for (int i = 0; i < m.size(); i++) {
                        if (m.get(i).getTitle().contains(title)) {
                                ids.add(m.get(i).getImdbID());
                        }
                }
                return ids;
        }

        public static ArrayList<Movie> sortTitle(ArrayList<Movie> m) {
                Collections.sort(m);
                return m;
        }

        public static Movie[] bubbleSort(Movie[] m) {
                for (int i = 0; i < m.length; i++) {
                        for (int j = i + 1; j < m.length; j++) {
                                if (m[j].getTitle().compareTo(m[i].getTitle()) < 0) {
                                        Movie temp = m[j];
                                        m[j] = m[i];
                                        m[i] = temp;
                                }
                        }
                }
                return m;
        } //end bubble sort

        public static int serialSearch(ArrayList<Movie> s, String value) {
                for (int i = 0; i < s.size(); i++) {
                        if (s.get(i).getImdbID().equals(value)) {
                                return i;
                        }
                }
                return -1;
        } //end serial search

        public static int search(ArrayList<String> s, String value) {
                for (int i = 0; i < s.size(); i++) {
                        if (s.get(i).equals(value)) {
                                return i;
                        }
                }
                return -1;
        } //end serial search

        public static void deleteMovie(String id, ArrayList<Movie> m) {
                int index = serialSearch(m, id);
                if (index != -1) {
                        m.remove(index);
                }
        }

        public static void deleteMovieTitle(String title, ArrayList<Movie> m) {
                int index = serialSearch(m, title);
                if (index != -1) {
                        m.remove(index);
                }
        }

}
