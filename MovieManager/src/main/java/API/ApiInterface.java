package API;

import java.io.IOException;
import java.sql.SQLException;

import main.MovieCollection;

public interface ApiInterface {

    /**
     * Scrape the movies in Moviellection and store the info to database
     * @param m MovieCollection
     * @throws IOException
     * @throws InterruptedException
     * @throws SQLException
     */
    public void scrape(MovieCollection m) throws IOException, InterruptedException, SQLException;
}
