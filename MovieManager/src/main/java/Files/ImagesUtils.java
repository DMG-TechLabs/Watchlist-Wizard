/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Files;

import Database.Database;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import kdesp73.madb.Condition;

/**
 *
 * @author kosta
 */
public class ImagesUtils {
        public static String SaveImg(String path, String url) throws IOException {
                //url = url.replaceAll(Pattern.quote("\\"), "");
                //url = url.replaceAll(Pattern.quote("300"), "700");
                //String ImageName = FilesList.GetOnlyPath(path, name)+name+".jpg";
                String ImageName = generateRandomString(10) + ".jpg";
                System.out.println(ImageName);
                if (url.contains("media-amazon")) {
                        url = url.replaceAll(Pattern.quote("\\"), "");
                        url = url.replaceAll(Pattern.quote("300"), "229");
                } else {
                        url = "https://image.tmdb.org/t/p/original/" + url;
                }

                try ( InputStream in = new URL(url).openStream()) {
                        Files.copy(in, Paths.get(path + "\\"+ ImageName));
                }
                
                return ImageName;
        }
        
        public static void imageToDatabase(String imdbID) throws SQLException{
                String path = (String) Database.db().SELECT("Settings", "Directory").get(0);
                String url = (String) Database.db().SELECT("Movies", "Poster_URL", new Condition("IMDb_ID", imdbID));
                //String imdbID = (String) Database.db().SELECT("Movies", "IMDb_ID", new Condition("Title", title));
                
                System.out.println("Path: " + path);
                System.out.println("URL: " + url);
                System.out.println("IMDb ID: " + imdbID);
                
                String imageName = "";
                
                try {
                        imageName = SaveImg(path, url);
                } catch (IOException ex) {
                        Logger.getLogger(ImagesUtils.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                Database.db().INSERT("Images", new String[]{"Image_Directory", "IMDb_ID"}, new String[]{path +"\\"+ imageName, imdbID});
        }
        
        public static String matchImage(String imdbID) throws SQLException{
                //String imdbID = (String) Database.db().SELECT("Movies", "IMDb_ID", new Condition("Title", title));
                
                String imgDir = (String) Database.db().SELECT("Images", "Image_Directory", new Condition("IMDb_ID", imdbID));
                
                return imgDir;
        }
        
        
        private static String generateRandomString(int length){
                String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
                String s = "";
                for (int i = 0; i < length; i++) {
                        s = s + characters.charAt(new Random().nextInt(characters.length()));
                }
                
                return s;
        }
}
