package setup;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.backend.security.AuthenticationResource;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("restservices")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("org.backend.webservices");
        packages("org.backend.security");
        register(CORSResponseFilter.class);
        register(AuthenticationResource.class);
        register(RolesAllowedDynamicFeature.class);
    }
}
