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
public class IMDBAPIWrapper {
    private static final String API_KEY = "k_9g3b3duz";
    private static final String LANGUAGE = "en";
    private static final String API_BASE = "https://imdb-api.com/" + LANGUAGE + "/API";

    private HttpClient httpClient = null;

    /**
     * Retrieves the top 250 rated movies from IMDB
     *
     * @return JSON containing all top 250 rated movies from IMDB
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    public String getTop250Movies() throws URISyntaxException, IOException, InterruptedException {

        String requestURI = getURI("Top250Movies");
        HttpClient client = getHttpClient();
        HttpRequest request = buildGetRequest(requestURI);

        HttpResponse.BodyHandler bodyHandler = HttpResponse.BodyHandlers.ofString();

        HttpResponse response = client.send(request,  bodyHandler );
        String json = response.body().toString();

        return json;
    }

    /**
     * Build GET request based on URI
     * @param uri
     * @return
     * @throws URISyntaxException
     */
    private static HttpRequest buildGetRequest(String uri) throws URISyntaxException {
        return HttpRequest
                .newBuilder()
                .GET()
                .uri(new URI(uri))
                .build();
    }

    /**
     * Get the HttpClient for this wrapper
     * @return
     */
    private HttpClient getHttpClient() {
        if(httpClient == null) {
            httpClient = HttpClient
                    .newBuilder()
                    .build();
        }
        return httpClient;
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

    /**
     * Main executing class
     * @param args
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        IMDBAPIWrapper wrapper = new IMDBAPIWrapper();
        String json = wrapper.getTop250Movies();
        System.out.println(json);
    }
}
