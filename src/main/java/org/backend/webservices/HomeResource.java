package org.backend.webservices;

import org.backend.domain.Vakantiehuis;
import org.backend.requests.VacationRental;
import org.backend.requests.VakantiehuisRequest;

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
            Vakantiehuis v1 = new Vakantiehuis(vRq.naam, vRq.adres, vRq.woonOppervlakte, vRq.status, vRq.image);
            Vakantiehuis.addHuis(v1);
            vr.addVakantiehuizenVR(v1);
        } else {
            var error = new AbstractMap.SimpleEntry<>("error", "Vakantiehuis kon niet gemaakt worden");
            return Response.status(409).entity(error)
                    .header("Access-Control-Allow-Origin", "*") // Add the CORS header to allow requests from all origins
                    .build();
        }

        return Response.ok(vr.getVakantieHuisBijNaam(vRq.naam))
                .header("Access-Control-Allow-Origin", "*") // Add the CORS header to allow requests from all origins
                .build();
    }

    @DELETE
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteHome(@PathParam("name") String naam) {
        VacationRental vr = VacationRental.getVacationRental();
        Vakantiehuis removedVh = Vakantiehuis.removeVakantiehuis(naam);
        if(removedVh == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            vr.getVakantiehuizenVR().remove(removedVh);
            return Response.ok(removedVh).build();
        }
    }

}
