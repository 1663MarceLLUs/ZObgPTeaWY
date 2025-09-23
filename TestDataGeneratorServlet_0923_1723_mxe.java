// 代码生成时间: 2025-09-23 17:23:29
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Path("/testData")
public class TestDataGeneratorServlet {

    // Generate random test data
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateTestData() {
        Map<String, Object> testData = new HashMap<>();
        // Adding a random number
        testData.put("randomNumber", generateRandomNumber());
        // Adding a random string
        testData.put("randomString", generateRandomString());
        // Adding a random boolean
        testData.put("randomBoolean", generateRandomBoolean());
        
        return Response.ok(testData).build();
    }

    // Helper method to generate a random number
    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(100); // Generate a number between 0 and 99
    }

    // Helper method to generate a random string
    private String generateRandomString() {
        Random random = new Random();
        return "String" + random.nextInt(100); // Append a number to a base string
    }

    // Helper method to generate a random boolean
    private boolean generateRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean(); // Generate true or false randomly
    }
}
