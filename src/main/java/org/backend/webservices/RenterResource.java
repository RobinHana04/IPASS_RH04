package org.backend.webservices;

import org.backend.domain.Boeking;
import org.backend.requests.VacationRental;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

@Path("/renter")
public class RenterResource {
    @GET
    @Path("/{renter}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookingsForRenter(@PathParam("renter") String renter) {
        VacationRental vr = VacationRental.getVacationRental();
        List<Boeking> bookings = new ArrayList<>();
        System.out.println("BOEKINGEN: " + vr.getBoekingenVR());

        for (Boeking boeking : vr.getBoekingenVR()) {
            if (boeking.getHuurder().getGebruikersnaam().equals(renter)) {
                bookings.add(boeking);
            }
        }

        if (bookings.isEmpty()) {
            var error = new AbstractMap.SimpleEntry<>("error", "Er zijn geen boekingen voor de opgegeven huurder");
            return Response.status(409).entity(error).build();
        } else {
            return Response.ok(bookings).build();
        }
    }
}
