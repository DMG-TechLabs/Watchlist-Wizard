/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Files;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

/**
 *
 * @author kosta
 */
public class ImagesUtils {
    public static void SaveImg(String path, String url) throws IOException{
        //url = url.replaceAll(Pattern.quote("\\"), "");
        //url = url.replaceAll(Pattern.quote("300"), "700");
        //String ImageName = FilesList.GetOnlyPath(path, name)+name+".jpg";
        String ImageName = path.substring(0, path.length()-4)+".jpg";
        //System.out.println(ImageName);
        if(url.contains("media-amazon")){
            url = url.replaceAll(Pattern.quote("\\"), "");
            url = url.replaceAll(Pattern.quote("300"), "700");
        }else{
            url = "https://image.tmdb.org/t/p/original/"+url;
        }
        
        try(InputStream in = new URL(url).openStream()){
            Files.copy(in, Paths.get(ImageName));
        }
    }
}
