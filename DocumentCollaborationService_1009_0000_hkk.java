// 代码生成时间: 2025-10-09 00:00:30
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/documents")
public class DocumentCollaborationService {

    // In-memory storage for document data
    private final Map<String, String> documents = new HashMap<>();

    // POST endpoint to create a new document
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDocument(String documentJson) {
        try {
            // Parse the JSON to extract the document ID and content
            // This is a simplified example and assumes documentJson is a JSON with an ID and content
            // In a real-world scenario, you would use a JSON parsing library like Jackson or Gson
            String[] documentParts = documentJson.split(":", 3);
            String documentId = documentParts[0].split(""")[1];
            String documentContent = documentParts[1].split(":")[1].replace(""", "").replace("\
", "\
");

            if (documents.containsKey(documentId)) {
                return Response.status(Response.Status.CONFLICT).entity("Document already exists.").build();
            }

            documents.put(documentId, documentContent);
            return Response.status(Response.Status.CREATED).entity(documentJson).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error creating document: " + e.getMessage()).build();
        }
    }

    // GET endpoint to retrieve a document
    @GET
    @Path("/{documentId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getDocument(@PathParam("documentId") String documentId) {
        String documentContent = documents.get(documentId);
        if (documentContent == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Document not found.").build();
        }
        return Response.ok(documentContent).build();
    }

    // PUT endpoint to update an existing document
    @PUT
    @Path("/{documentId}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateDocument(@PathParam("documentId\) String documentId, String documentContent) {
        if (!documents.containsKey(documentId)) {
            return Response.status(Response.Status.NOT_FOUND).entity("Document not found.").build();
        }
        documents.put(documentId, documentContent);
        return Response.ok(documentContent).build();
    }

    // DELETE endpoint to delete a document
    @DELETE
    @Path("/{documentId}")
    public Response deleteDocument(@PathParam("documentId\) String documentId) {
        if (!documents.containsKey(documentId)) {
            return Response.status(Response.Status.NOT_FOUND).entity("Document not found.").build();
        }
        documents.remove(documentId);
        return Response.ok().build();
    }
}
