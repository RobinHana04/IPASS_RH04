package setup;

import org.backend.security.AuthenticationResource;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("restservices")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("org.backend.webservices");
        packages("org.backend.security");
        register(CORSResponseFilter.class);
        register(AuthenticationResource.class);
    }
}
