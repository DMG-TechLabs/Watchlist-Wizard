/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Files;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author kosta
 */
public class FilesList {
    private List<String> path = new ArrayList<>();
    private List<String> names = new ArrayList<>();
    
    public FilesList(String dir) {
        DirFiles paths = new DirFiles(dir);
        try {
            names = paths.GetNames(path);
            path = paths.FindFiles();
        } catch (IOException ex) {
            names = null;
            path = null;
            System.out.println("The directory was not found");
        }
    }
    
    public FilesList(String dir, ArrayList<String> exts){
        DirFiles paths = new DirFiles(dir, exts);
        try {
            if(dir != "" || dir != null || paths != null){
            path = paths.FindFiles();
            names = paths.GetNames(path);}
        } catch (IOException | NullPointerException ex) {
            System.out.println("The directory was not found");
        }
    }
    
    

    public List<String> getPaths() {
        return path;
    }

    public List<String> getNames() {
        return names;
    }
    
    public String getPath(int index) {
        return path.get(index);
    }

    public String getName(int index) {
        return names.get(index);
    }
    
    public int length() {
        return names.size();
    }
}
