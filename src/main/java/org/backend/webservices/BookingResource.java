package org.backend.webservices;

import org.backend.domain.Boeking;
import org.backend.requests.BoekingRequest;
import org.backend.requests.VacationRental;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.AbstractMap;
import java.util.ArrayList;
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

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBooking(BoekingRequest bkr) {
        VacationRental vr = VacationRental.getVacationRental();
        List<Boeking> ab = vr.getBoekingenVR();
        if (ab == null) {
            var error = new AbstractMap.SimpleEntry<>("error", "Boekingen lijst is null");
            return Response.status(409).entity(error).build();
        } else {
            Boeking b1 = new Boeking(bkr.huurder, bkr.vakantiehuis, bkr.datumVan, bkr.datumTot);
            vr.addBoekingenVR(b1);
        }
        return Response.ok(ab).build();
    }

    @DELETE
    @Path("{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteBooking(@PathParam("name") String naam)  {
        VacationRental vr = VacationRental.getVacationRental();
        List<Boeking> removedBoeking = new ArrayList<>();
        List<Boeking> alleBoekingen = vr.getBoekingenVR();
        if(naam == null) {
            var error = new AbstractMap.SimpleEntry<>("error", "Naam van vakantiehuis klopt niet, of is null");
            return Response.status(409).entity(error).build();
        } else {
            for(Boeking b : alleBoekingen) {
                if (b.getVakantiehuis().getName().equals(naam)) {
                    alleBoekingen.remove(b);
                    removedBoeking.add(b);
                }
            }
        }
        return Response.ok(removedBoeking).build();
    }

}

