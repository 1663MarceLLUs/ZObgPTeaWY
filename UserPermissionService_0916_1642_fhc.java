// 代码生成时间: 2025-09-16 16:42:12
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;

@Path("/user-permissions")
public class UserPermissionService {

    private static final Map<String, Map<String, Boolean>> userPermissions = new HashMap<>();

    static {
        // Initialize with some dummy data
        Map<String, Boolean> adminPermissions = new HashMap<>();
        adminPermissions.put("read", true);
        adminPermissions.put("write", true);
        adminPermissions.put("delete", true);
        userPermissions.put("admin", adminPermissions);

        Map<String, Boolean> userPermissions = new HashMap<>();
        userPermissions.put("read", true);
        userPermissions.put("write", false);
        userPermissions.put("delete", false);
        userPermissions.put("admin", false);
        userPermissions.put("guest", userPermissions);
    }

    /**
     * Get user permissions
     *
     * @param username
     * @return
     */
    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserPermissions(@PathParam("username") String username) {
        if (!userPermissions.containsKey(username)) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
        }

        Map<String, Boolean> permissions = userPermissions.get(username);
        return Response.ok(permissions).build();
    }

    /**
     * Add a new user with permissions
     *
     * @param username
     * @param permissions
     * @return
     */
    @POST
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUserPermissions(@PathParam("username\) String username, Map<String, Boolean> permissions) {
        if (userPermissions.containsKey(username)) {
            return Response.status(Response.Status.CONFLICT).entity("User already exists").build();
        }

        userPermissions.put(username, permissions);
        return Response.ok("User permissions added").build();
    }

    /**
     * Update user permissions
     *
     * @param username
     * @param permissions
     * @return
     */
    @POST
    @Path("/{username}/update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUserPermissions(@PathParam("username\) String username, Map<String, Boolean> permissions) {
        if (!userPermissions.containsKey(username)) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
        }

        userPermissions.put(username, permissions);
        return Response.ok("User permissions updated").build();
    }
}
