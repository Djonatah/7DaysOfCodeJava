import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * IMDB Wrapper class
 *
 * #7DaysOfCode challenge
 */
public class IMDBWrapper {
    private static final String API_KEY = "k_9g3b3duz";
    private static final String LANGUAGE = "en";
    private static final String API_BASE = "https://imdb-api.com/" + LANGUAGE + "/API";

    /**
     * Retrieve the top 250 rated movies from IMDB
     * @return JSON containing all 250 top rated movies
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    public String getTop250Movies() throws URISyntaxException, IOException, InterruptedException {

        HttpClient client = HttpClient
                .newBuilder()
                .build();

        String uri = getURI("Top250Movies");

        HttpRequest request = HttpRequest
                .newBuilder()
                .GET()
                .uri(new URI(uri))
                .build();

        HttpResponse.BodyHandler bodyHandler = HttpResponse.BodyHandlers.ofString();

        HttpResponse response = client.send(request,  bodyHandler );
        String json = response.body().toString();

        return json;
    }

    /**
     * Build URI based on targed api method/call
     * @param method
     * @return
     */
    private static String getURI(String method){
        String endpoint = API_BASE + "/" + method + "/" + API_KEY;
        return endpoint;
    }
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        IMDBWrapper wrapper = new IMDBWrapper();
        String json = wrapper.getTop250Movies();
        System.out.println(json);
    }
}
