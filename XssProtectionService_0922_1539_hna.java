// 代码生成时间: 2025-09-22 15:39:39
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.owasp.encoder.Encode;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * XssProtectionService provides a basic endpoint to demonstrate XSS protection in a Jersey application.
 */
@Path("/xss")
public class XssProtectionService {

    /**
     * Returns a response that demonstrates XSS protection.
     *
     * @param userInput The user input that may contain XSS attack payloads.
     * @return A Response object with the proper content type and payload.
     */
    @GET
    @Path("protect")
    @Produces(MediaType.TEXT_HTML)
    public Response demonstrateXssProtection(@QueryParam("input") String userInput) {
        if (userInput == null) {
            userInput = "";
        }
        try {
            // Decode user input to handle URL encoded payloads
            String decodedInput = URLDecoder.decode(userInput, StandardCharsets.UTF_8.name());

            // Encode the decoded input to prevent XSS attacks
            String safeInput = Encode.forHtmlContent(decodedInput);

            // Create a response with the encoded user input
            String response = "<html><body>" +
                             "<p>You entered: " + safeInput + "</p>" +
                             "</body></html>";

            return Response.ok(response).build();
        } catch (UnsupportedEncodingException e) {
            // Log the error and return a 500 internal server error response
            // Note: Proper error logging should be implemented in production code
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error processing your request.").build();
        }
    }
}
