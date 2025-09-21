// 代码生成时间: 2025-09-22 05:38:14
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.net.URL;

@Path("/url")
public class ValidUrlService {

    /**
     * Validates the provided URL.
     *
     * @param urlString The URL to be validated.
     * @return A response indicating the validity of the URL.
     */
    @GET
    @Path("/validate")
    public Response validateUrl(@QueryParam("url") String urlString) {
        try {
            // Attempt to create a URL object to validate the format.
            URL url = new URL(urlString);
            // Validate the URL protocol.
            if (!url.getProtocol().equals("http") && !url.getProtocol().equals("https")) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Invalid URL protocol. Only HTTP and HTTPS are allowed.").build();
            }
            // Additional validation can be added here (e.g., checking if the URL is reachable).
            return Response.ok("URL is valid and uses a supported protocol.").build();
        } catch (Exception e) {
            // Handle any exceptions that occur during URL validation.
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid URL format.").build();
        }
    }
}
