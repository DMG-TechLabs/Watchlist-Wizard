package Files;

import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

public class FilesListTest {
    @Test
    public void testGetName() {
        /*
        String dir = System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") +  "/data/";
        System.out.println(dir);
        FilesList files = new FilesList(dir);
        Assert.assertNotNull(files.getName(0));
         */
    }

    @Test
    public void testGetNames() {
        String dir = System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") +  "/data/";
        FilesList files = new FilesList(dir);
        Assert.assertNotNull(files.getNames());
    }

    @Test
    public void testGetPath() {
        /*
        String dir = System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") +  "/data/";
        FilesList files = new FilesList(dir);
        Assert.assertNotNull(files.getPath(0));
         */
    }

    @Test
    public void testGetPaths() {
        String dir = System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") +  "/data/";
        FilesList files = new FilesList(dir);
        
        Assert.assertNotNull(files.getPaths());

    }
}
