package API;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;

import main.Movie;
import main.MovieCollection;

public interface ApiInterface {

    public String search(String title) throws MalformedURLException, IOException, InterruptedException;

     /**
      * Scrape the movies in Moviellection and store the info to database
      * @param m MovieCollection
      * @return ArrayList<Movie>
      * @throws IOException
      * @throws InterruptedException
      * @throws SQLException
      */
    public ArrayList<Movie> scrape(MovieCollection m) throws IOException, InterruptedException, SQLException;

    /**
     * 
     * @throws SQLException
     */
    public void saveToDatabase() throws SQLException;

    /**
     * Scrape the movies  
     * @param m
     * @throws IOException
     * @throws InterruptedException
     * @throws SQLException
     */
    public void scrapeAndSave(MovieCollection m) throws IOException, InterruptedException, SQLException;
}
