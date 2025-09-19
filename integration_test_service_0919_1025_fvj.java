// 代码生成时间: 2025-09-19 10:25:46
 * Provides a simple Integration Test Service using JERSEY framework.
 * This service simulates an API endpoint and demonstrates how to write integration tests in Java.
 */
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class IntegrationTestService extends JerseyTest {

    @Path("/test")
    public static class TestResource {

        @GET
        @Produces(MediaType.TEXT_PLAIN)
        public String getTestMessage() {
            return "Hello from the test resource!";
        }
    }

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(TestResource.class);
    }

    @Test
    public void testGetTestMessage() {
        Response response = target("test").request().get();
        assertEquals(200, response.getStatus());
        String expected = "Hello from the test resource!";
        assertEquals(expected, response.readEntity(String.class));
    }
}
