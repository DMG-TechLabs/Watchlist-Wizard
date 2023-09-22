package Files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class DirFiles {

        private ArrayList<String> extList = new ArrayList<>();
        private ArrayList<String> pathsList = new ArrayList<>();
        private ArrayList<String> NamesList = new ArrayList<>();
        private String directoryName = "";

        public DirFiles(String directory) {
                
                this.directoryName = directory;
                //extList.add("qwerty123456");
        }

        public DirFiles(String directory, ArrayList<String> exts) {
                this(directory);
                this.extList = exts;
                /*
                for (int i = 0; i < exts.size(); i++) {
                        extList.add(exts.get(i));

                }
                */
        }

        public List<String> getExtList() {
                return extList;
        }

        public List<String> getPathsList() {
                return pathsList;
        }

        public String getDirectoryName() {
                return directoryName;
        }

        public List<String> getNamesList() {
                return NamesList;
        }

        public void setExtList(ArrayList<String> extList) {
                System.gc();
                this.extList = extList;
        }

        public void setDirectoryName(String directoryName) {
                this.directoryName = directoryName;
        }

        public List<String> FindFiles() throws IOException {
                //if (this.directoryName == "") return pathsList;
                try ( Stream<Path> paths = Files.walk(Path.of(this.directoryName), 10)) {
                        List<String> pathlist = paths
                                .map(path -> Files.isDirectory(path) ? path.toString() + '/' : path.toString())
                                .collect(Collectors.toList());

                        for (String path : pathlist) {
                                if (path.contains(".") && this.extList.contains(path.substring(path.length() - 3))) {
                                        this.pathsList.add(path);
                                        //System.out.println(path);
                                }
                                //System.out.println(path);
                        }

                } catch (IOException e) {
                }
                return pathsList;
        }

        public List<String> GetNames(List<String> pathsList) {
                for (String path : pathsList) {
                        String[] final_path = new String[50];
                        String final_name;
                        if (System.getProperty("os.name").equals("Linux")){
                                final_path = path.split(Pattern.quote("/"));
                        } else{
                                final_path = path.split(Pattern.quote("\\"));
                        }
                        final_name = final_path[final_path.length - 1];
                        final_name = final_name.substring(0, final_name.length() - 4);
                        NamesList.add(final_name);
                }

                return NamesList;
        }

        public static String GetName(String pathsName) {

                String[] final_path = new String[50];
                String final_name;
                
                if(pathsName == null) {
                        pathsName = "";
                }
                
                if (System.getProperty("os.name").equals("Linux")){
                        final_path = pathsName.split(Pattern.quote("/"));
                } else{
                        final_path = pathsName.split(Pattern.quote("\\"));
                }
                final_name = final_path[final_path.length - 1];
                //final_name = (final_name.split(Pattern.quote(".")))[0];
                final_name = final_name.substring(0, final_name.length() - 4);

                return final_name;
        }
        
        public static String GetExt(String pathsName) {

                String[] final_path = new String[50];
                String[] ext = new String[50];
                String final_name;

                
                if (System.getProperty("os.name").equals("Linux")){
                        final_path = pathsName.split(Pattern.quote("/"));
                } else{
                        final_path = pathsName.split(Pattern.quote("\\"));
                }

                final_name = final_path[final_path.length - 1];
                ext = final_name.split(Pattern.quote("."));
                final_name = ext[ext.length - 1];

                return final_name;
        }
        
}
