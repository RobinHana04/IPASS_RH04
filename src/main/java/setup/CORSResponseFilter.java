package setup;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

@Provider
public class CORSResponseFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        MultivaluedMap<String, Object> headers = responseContext.getHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        headers.add("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization");

        // Add any additional headers that your application requires
        headers.add("Access-Control-Allow-Headers", "X-Custom-Header1, X-Custom-Header2");

        // Remove any duplicate values for the Access-Control-Allow-Origin header
        headers.remove("Access-Control-Allow-Origin");
        headers.add("Access-Control-Allow-Origin", "*");
    }
}



