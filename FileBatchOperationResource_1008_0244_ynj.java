// 代码生成时间: 2025-10-08 02:44:23
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// REST resource class
@Path("/files")
public class FileBatchOperationResource {

    // API endpoint to list all files in a directory
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listFiles() {
        // Define the directory to list files from
        String directoryPath = "/path/to/your/directory";
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        // Check if directory is valid and files are listed
        if (files == null || files.length == 0) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No files found in the directory.").build();
        }

        // Convert File array to a list of file names
        List<String> fileNames = new ArrayList<>();
        for (File file : files) {
            fileNames.add(file.getName());
        }

        // Return the list of file names as JSON
        return Response.ok(fileNames).build();
    }

    // API endpoint to delete files matching a pattern in a directory
    @GET
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteFiles(@QueryParam("pattern") String pattern) {
        // Define the directory to delete files from
        String directoryPath = "/path/to/your/directory";
        File directory = new File(directoryPath);

        // Use a path matcher to find files matching the pattern
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*{pattern}*");
        try {
            List<String> deletedFiles = Files.walk(Paths.get(directoryPath))
                    .filter(Files::isRegularFile)
                    .filter(path -> matcher.matches(path.getFileName()))
                    .map(path -> {
                        try {
                            Files.delete(path);
                            return path.getFileName().toString();
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    })
                    .collect(Collectors.toList());

            // Return the list of deleted file names as JSON
            return Response.ok(deletedFiles).build();
        } catch (IOException | UncheckedIOException e) {
            // Handle IO exceptions
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error deleting files: " + e.getMessage()).build();
        }
    }
}
