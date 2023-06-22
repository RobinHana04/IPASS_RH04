package org.backend.webservices;

import org.backend.domain.Boeking;
import org.backend.domain.Huurder;
import org.backend.requests.HuurderRequest;
import org.backend.requests.VacationRental;

import javax.ws.rs.*;
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

    @GET
    @Path("/search/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSingleRenter(@PathParam("name") String name) {
        VacationRental vr = VacationRental.getVacationRental();
        Huurder selectedHuurder = vr.getHuurderBijNaam(name);

        if (selectedHuurder == null) {
            var error = new AbstractMap.SimpleEntry<>("error", "Deze huurder bestaat niet");
            return Response.status(409).entity(error).build();
        }
        return Response.ok(selectedHuurder).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRenter(HuurderRequest hRq) {
        VacationRental vr = VacationRental.getVacationRental();
        Huurder selectedHuurder = vr.getHuurderBijNaam(hRq.gebruikersnaam);
        if (selectedHuurder == null) {
            Huurder h1 = new Huurder(hRq.gebruikersnaam);
            Huurder.addHuurder(h1);
            vr.addHuurdersVR(h1);
        } else {
            var error = new AbstractMap.SimpleEntry<>("error", "Huurder bestaat al of kon niet gemaakt worden.");
            return Response.status(409).entity(error)
                    .header("Access-Control-Allow-Origin", "*").build();
        }
    return Response.ok(vr.getHuurderBijNaam(hRq.gebruikersnaam)).header("Access-Control-Allow-Origin", "*") // Add the CORS header to allow requests from all origins
            .build();
    }
}
