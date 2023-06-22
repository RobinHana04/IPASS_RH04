package org.backend.webservices;

import org.backend.requests.VacationRental;
import org.backend.security.Username;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.AbstractMap;
import java.util.List;

@Path("username")
public class UsernameResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurrentUsername() {
        VacationRental vr = VacationRental.getVacationRental();
        List<Username> CurrentUsername = vr.getUsernamesVR();
        if(CurrentUsername.isEmpty()) {
            var error = new AbstractMap.SimpleEntry<>("error", "Er zijn geen huidige usernames beschikbaar");
            return Response.status(204).entity(error).build();
        }
    return Response.ok(CurrentUsername).build();

    }
}
