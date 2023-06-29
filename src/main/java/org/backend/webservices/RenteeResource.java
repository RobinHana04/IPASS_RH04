package org.backend.webservices;

import org.backend.domain.Vakantiehuis;
import org.backend.domain.Verhuurder;
import org.backend.requests.HuurderRequest;
import org.backend.requests.VacationRental;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

@Path("/rentee")
public class RenteeResource {
    @GET
    @Path("/search/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSingleRentee(@PathParam("name") String name) {
        VacationRental vr = VacationRental.getVacationRental();
        Verhuurder selectedVerhuurder = vr.getVerhuurderBijNaam(name);

        if (selectedVerhuurder == null) {
            var error = new AbstractMap.SimpleEntry<>("error", "Deze verhuurder bestaat niet");
            return Response.status(409).entity(error).build();
        }
        return Response.ok(selectedVerhuurder).build();
    }

    @GET
    @Path("/allHomes/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHomesOfRentee(@PathParam("name") String name) {
        VacationRental vr = VacationRental.getVacationRental();
        List<Vakantiehuis> alleHuizen = vr.getVakantiehuizenVR();
        List<Vakantiehuis> huizenVanVerhuurder = new ArrayList<>();

        if (alleHuizen.isEmpty()) {
            var error = new AbstractMap.SimpleEntry<>("error", "Deze verhuurder bestaat niet");
            return Response.status(409).entity(error).build();
        }

        for(Vakantiehuis vh : alleHuizen) {
            if (vh.getVerhuurder().getGebruikersnaam().equals(name)) {
                huizenVanVerhuurder.add(vh);
            }
        }
        return Response.ok(huizenVanVerhuurder).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRentee(HuurderRequest hRq) {
        VacationRental vr = VacationRental.getVacationRental();
       Verhuurder selectedVerhuurder = vr.getVerhuurderBijNaam(hRq.gebruikersnaam);
        if (selectedVerhuurder == null) {
            Verhuurder vh1 = new Verhuurder(hRq.gebruikersnaam);
            Verhuurder.addVerhuurder(vh1);
            vr.addVerhuurderVR(vh1);
        } else {
            var error = new AbstractMap.SimpleEntry<>("error", "Verhuurder bestaat al of kon niet gemaakt worden.");
            return Response.status(409).entity(error)
                    .header("Access-Control-Allow-Origin", "*").build();
        }
        return Response.ok(vr.getHuurderBijNaam(hRq.gebruikersnaam)).header("Access-Control-Allow-Origin", "*") // Add the CORS header to allow requests from all origins
                .build();
    }

}
