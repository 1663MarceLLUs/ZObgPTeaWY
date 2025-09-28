// 代码生成时间: 2025-09-29 00:02:58
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class AccessControlApplication extends Application {
    // Define allowed roles
    private static final Set<String> ALLOWED_ROLES = new HashSet<>(Arrays.asList("ADMIN", "USER"));

    @Override
    public Set<Class<?>> getClasses() {
        // Register resources and providers
        return new HashSet<>(Arrays.asList(
            AccessControlResource.class,
            AuthenticationFilter.class
        ));
    }
}

// The AccessControlResource resource class
class AccessControlResource {
    // A secured endpoint requiring an ADMIN role
    // The @RolesAllowed annotation enforces access control
    @javax.annotation.security.RolesAllowed("ADMIN")
    public String accessControlledResource() {
        return "Access granted to ADMIN role";
    }

    // A secured endpoint allowing both ADMIN and USER roles
    @javax.annotation.security.RolesAllowed({"ADMIN", "USER"})
    public String restrictedResource() {
        return "Access granted to ADMIN and USER roles";
    }
}

// The AuthenticationFilter class
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Provider
@PreMatching
public class AuthenticationFilter implements ContainerRequestFilter {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_TOKEN_PREFIX = "Bearer ";
    private static final Set<String> VALID_TOKENS = new HashSet<>(Arrays.asList("validToken1", "validToken2"));

    @Context
    private UriInfo uriInfo;

    @Context
    private SecurityContext securityContext;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authHeader = requestContext.getHeaderString(AUTHORIZATION_HEADER);
        String token = extractToken(authHeader);

        if (token != null && VALID_TOKENS.contains(token)) {
            // Set the security context with a custom principal
            securityContext.setPrincipal(new Principal() {
                @Override
                public String getName() {
                    // Return a placeholder user name for simplicity
                    return "user";
                }
            });
            requestContext.setSecurityContext(securityContext);
        } else {
            // Unauthorized access attempt
            requestContext.abortWith(javax.ws.rs.core.Response.status(401).entity("Unauthorized").build());
        }
    }

    private String extractToken(String authHeader) {
        if (authHeader != null && authHeader.startsWith(BEARER_TOKEN_PREFIX)) {
            return authHeader.substring(BEARER_TOKEN_PREFIX.length());
        }
        return null;
    }
}
