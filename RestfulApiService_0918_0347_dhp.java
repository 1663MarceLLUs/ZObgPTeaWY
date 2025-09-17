// 代码生成时间: 2025-09-18 03:47:36
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/api")
public class RestfulApiService {

    // A simple in-memory data store
    private Map<Integer, String> dataStore = new HashMap<>();
    private static int counter = 0;

    // Initialize the data store with some data
    public RestfulApiService() {
        dataStore.put(1, "Resource 1");
        dataStore.put(2, "Resource 2");
    }

    /**
     * Handles HTTP GET requests for a list of resources.
     * @return A JSON array of all resources.
     */
    @GET
    @Path("/resources")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResources() {
        return Response.ok(dataStore.values()).build();
    }

    /**
     * Handles HTTP GET requests for a single resource by ID.
     * @param id The ID of the resource.
     * @return A JSON representation of the resource.
     */
    @GET
    @Path("/resources/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResourceById(@PathParam("id") int id) {
        String resource = dataStore.get(id);
        if (resource == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(resource).build();
    }

    /**
     * Handles HTTP POST requests to create a new resource.
     * @param resource The new resource to be created.
     * @return A JSON representation of the created resource along with a 201 status.
     */
    @POST
    @Path("/resources")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createResource(String resource) {
        int id = ++counter;
        dataStore.put(id, resource);
        return Response.status(Response.Status.CREATED).entity(resource).build();
    }

    /**
     * Handles HTTP PUT requests to update an existing resource.
     * @param id The ID of the resource to be updated.
     * @param resource The updated resource.
     * @return A JSON representation of the updated resource.
     */
    @PUT
    @Path("/resources/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateResource(@PathParam("id") int id, String resource) {
        if (!dataStore.containsKey(id)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        dataStore.put(id, resource);
        return Response.ok(resource).build();
    }

    /**
     * Handles HTTP DELETE requests to delete a resource by ID.
     * @param id The ID of the resource to be deleted.
     * @return A 204 status if the deletion is successful.
     */
    @DELETE
    @Path("/resources/{id}")
    public Response deleteResource(@PathParam("id") int id) {
        if (!dataStore.containsKey(id)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        dataStore.remove(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}