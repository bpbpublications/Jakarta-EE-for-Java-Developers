package net.rhuanrocha.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import net.rhuanrocha.customconstraint.Even;

@Path("even")
public class EvenResource {

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response print( @Even(message = "{net.rhuanrochar.pair.message}") @FormParam("number") Long number){
        return Response
                .ok(number)
                .build();
    }
}
