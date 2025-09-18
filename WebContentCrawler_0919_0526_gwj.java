// 代码生成时间: 2025-09-19 05:26:22
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Path("/crawler")
public class WebContentCrawler {
    
    // Fetch web content from the given URL
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String fetchWebContent(@QueryParam("url") String urlString) {
        if (urlString == null || urlString.isEmpty()) {
            throw new IllegalArgumentException("URL cannot be null or empty");
        }
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("Failed to fetch web content. HTTP error code: " + responseCode);
            }

            // Read the content from the input stream
            String content = new String(connection.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: Unable to fetch web content.";
        }
    }
}
