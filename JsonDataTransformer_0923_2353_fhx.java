// 代码生成时间: 2025-09-23 23:53:01
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONArray;
import java.util.Iterator;

@Path("/jsonTransformer")
public class JsonDataTransformer {

    // Converts a JSON object to a JSON array by wrapping it inside an array.
    @POST
    @Path("/toArray")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response convertJsonToJSONArray(JSONObject json) {
        try {
            // Wrap the JSON object inside a JSONArray.
            JSONArray array = new JSONArray();
            array.put(json);
            return Response.ok(array.toString()).build();
        } catch (JSONException e) {
            // Handle JSON parsing error.
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error parsing JSON: " + e.getMessage()).build();
        }
    }

    // Converts a JSON array to a JSON object by extracting the first element.
    @POST
    @Path("/toObject")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response convertJsonToArray(JSONObject json) {
        try {
            // Extract the first element of the JSON array.
            JSONArray array = new JSONArray(json.toString());
            if (array.length() > 0) {
                return Response.ok(array.get(0).toString()).build();
            } else {
                // Handle empty JSON array.
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Empty JSON array provided.").build();
            }
        } catch (JSONException e) {
            // Handle JSON parsing error.
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error parsing JSON: " + e.getMessage()).build();
        }
    }

    // Converts a JSON array to a JSON object by iterating through the array.
    @POST
    @Path("/toObjectFromArray")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response convertJsonToArrayToJSONObject(JSONArray jsonArray) {
        try {
            // Create a new JSON object to store the results.
            JSONObject result = new JSONObject();
            // Iterate through the JSON array and add each element as a key-value pair.
            Iterator<String> keys = jsonArray.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                Object value = jsonArray.get(key);
                result.put(key, value);
            }
            return Response.ok(result.toString()).build();
        } catch (JSONException e) {
            // Handle JSON parsing error.
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error parsing JSON: " + e.getMessage()).build();
        }
    }
}
