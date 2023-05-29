package org.backend.webservices;

import org.backend.domain.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.AbstractMap;
import java.util.List;

@Path("homes")
public class HomeResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHomes() {
        VacationRental vr = VacationRental.getVacationRental();
        List<Vakantiehuis> vh = vr.getAllVakantieHuizen();
        if (vh.isEmpty()) {
            var error = new AbstractMap.SimpleEntry<>("error", "Vakantiehuizen zijn leeg. Vul de lijst met data");
            return Response.status(409).entity(error).build();
        } else {
            return Response.ok(vh).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{name}")
    public Response getSingleHome(@PathParam("name") String name) {
        VacationRental vr = VacationRental.getVacationRental();
        Vakantiehuis selectedHome = vr.getVakantieHuisBijNaam(name);
        if (selectedHome == null) {
            var error = new AbstractMap.SimpleEntry<>("error", "Vakantiehuis kon niet gevonden worden");
            return Response.status(409).entity(error).build();
        } else {
            return Response.ok(selectedHome).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addHome(VakantiehuisRequest vRq) {
        VacationRental vr = VacationRental.getVacationRental();
        Vakantiehuis selectedHome = vr.getVakantieHuisBijNaam(vRq.naam);

        if (selectedHome == null) {
            Vakantiehuis v1 = new Vakantiehuis(vRq.naam, vRq.adres, vRq.woonOppervlakte, vRq.status);
            Vakantiehuis.addHuis(v1);
        } else {
            var error = new AbstractMap.SimpleEntry<>("error", "Vakantiehuis kon niet gemaakt worden");
            return Response.status(409).entity(error).build();
        }
    return Response.ok(vr.getVakantieHuisBijNaam(vRq.naam)).build();
    }
}
