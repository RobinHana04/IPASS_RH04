package org.backend.webservices;

import org.backend.domain.Vakantiehuis;
import org.backend.domain.Verhuurder;
import org.backend.requests.VacationRental;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.AbstractMap;
import java.util.List;

@Path("/rentee")
public class RenteeResource {
    @GET
    @Path("/{rentee}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHomesForRentee(@PathParam("rentee") String rentee) {
        VacationRental vr = VacationRental.getVacationRental();
        Verhuurder selectedVerhuurder = vr.getVerhuurderBijNaam(rentee);
        List<Vakantiehuis> alleHuizenVanVerhuurder = selectedVerhuurder.getAlleHuizen();
        if (alleHuizenVanVerhuurder.isEmpty()) {
            var error = new AbstractMap.SimpleEntry<>("error", "De verhuurder heeft geen vakantiehuizen op zijn naam");
            return Response.status(409).entity(error).build();
        } else {
            return Response.ok(alleHuizenVanVerhuurder).build();
        }
    }

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
}
