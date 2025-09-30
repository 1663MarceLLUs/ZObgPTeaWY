// 代码生成时间: 2025-09-30 21:45:09
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/wealth")
public class WealthManagementTool {

    // Calculates the compound interest
    @GET
    @Path("/compound/{principal}/{rate}/{time}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response calculateCompoundInterest(@PathParam("principal") double principal,
                                           @PathParam("rate") double rate,
                                           @PathParam("time") double time) {
        try {
            // Validate input parameters
            if (principal <= 0 || rate < 0 || time < 0) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Invalid input parameters").build();
            }

            // Calculate compound interest formula: A = P(1 + r/n)^(nt)
            double amount = principal * Math.pow((1 + rate / 100), time);

            // Return the calculated amount
            return Response.ok("Compound Interest: " + amount).build();
        } catch (Exception e) {
            // Handle any unexpected errors
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error calculating compound interest").build();
        }
    }

    // Calculates the simple interest
    @GET
    @Path("/simple/{principal}/{rate}/{time}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response calculateSimpleInterest(@PathParam("principal") double principal,
                                          @PathParam("rate") double rate,
                                          @PathParam("time") double time) {
        try {
            // Validate input parameters
            if (principal <= 0 || rate < 0 || time < 0) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Invalid input parameters").build();
            }

            // Calculate simple interest formula: SI = P * r * t
            double interest = principal * rate * time;

            // Return the calculated interest
            return Response.ok("Simple Interest: " + interest).build();
        } catch (Exception e) {
            // Handle any unexpected errors
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error calculating simple interest").build();
        }
    }
}
