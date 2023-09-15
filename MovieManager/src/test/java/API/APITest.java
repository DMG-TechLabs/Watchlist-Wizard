package API;

import org.junit.Test;

public class APITest {
    @Test
    public void testGET() {
        /*String expected_value = "{ \"Title\":\"V for Vendetta\" ,\"Year\":\"2006\" ,\"Rated\":\"R\" ,\"Released\":\"2006-02-23\" ,\"Runtime\":\"132\" ,\"Genre\":\"Action,Thriller,Sci-Fi,\" ,\"Director\":\"Mike Figgis\" ,\"Writer\":\"Mike Figgis\" ,\"Actors\":\"Nicolas Cage,Elisabeth Shue,Julian Sands\" ,\"Plot\":\"In a world in which Great Britain has become a fascist state, a masked vigilante known only as “V” conducts guerrilla warfare against the oppressive British government. When V rescues a young woman from the secret police, he finds in her an ally with who\" ,\"Language\":\"en\" ,\"Country\":\"US\" ,\"Awards\":\"\" ,\"Poster\":\"/1avD1JeaRiJX5M4ahPdZPypGoGN.jpg\" ,\"Type\":\"movie\" ,\"imdbRating\":\"7.899\" ,\"imdbID\":\"tt0434409\"}";
        String json = "";
        try{
            API api = new API();
            json = api.GET("V for Vendetta");
        } catch(Exception e){
            json = "";
        }
        Assert.assertEquals("API test for title: ", expected_value, json);*/
    }
}
