package org.backend.webservices;

import org.backend.domain.Boeking;
import org.backend.requests.VacationRental;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.AbstractMap;
import java.util.List;

@Path("/booking")
public class BookingResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBookings() {
        VacationRental vr = VacationRental.getVacationRental();
        List<Boeking> ab = vr.getBoekingenVR();
        if (ab.isEmpty()) {
            var error = new AbstractMap.SimpleEntry<>("error", "Er zijn geen boekingen, vul de lijst met data");
            return Response.status(409).entity(error).build();
        } else {
            return Response.ok(ab).build();
        }
    }
}

