package net.rhuanrocha.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import net.rhuanrocha.business.AuthorBusiness;
import net.rhuanrocha.entities.Author;

import java.net.URI;

@Path("authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {

    @Inject
    private AuthorBusiness authorBusiness;

    @GET
    public Response find(@QueryParam("name") String name){
        if(name != null && !name.isEmpty()){
            return Response
                    .ok(authorBusiness.findByName(name))
                    .build();
        }
        return Response.ok(authorBusiness.findAll()).build();
    }

    @POST
    public Response save(Author author){
        author.setId(null);
        authorBusiness.save(author);
        return Response
                .created(URI.create(String.format("authors/%d",author.getId())))
                .build();
    }
}
